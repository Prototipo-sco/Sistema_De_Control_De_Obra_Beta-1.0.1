package com.ic.sexto.sco.fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.TransactionTooLargeException;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import com.ic.sexto.sco.MainActivity;
import com.ic.sexto.sco.app.AppConfig;
import com.ic.sexto.sco.app.AppController;
import com.ic.sexto.sco.helper.SQLiteHandler;
import com.ic.sexto.sco.helper.SessionManager;

import co.ic.sexto.sco.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = UserRegistro.class.getSimpleName();

    private EditText Usuario, Contraseña;
    private Button acceder, registrar;
    private TextView olvidar;
    private ProgressDialog pDialog;
    private SessionManager session;
    private SQLiteHandler db;

    public static final String SCORE_KEY = "SCORE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Usuario = (EditText) findViewById(R.id.etusuario);
        Contraseña = (EditText) findViewById(R.id.etpass);
        acceder = (Button) findViewById(R.id.button);
        registrar = (Button) findViewById(R.id.btn_registro);
        olvidar = (TextView) findViewById(R.id.txt_olvidar);


        // Progress dialog
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        // SQLite database handler
        db = new SQLiteHandler(getApplicationContext());

        // Session manager
        session = new SessionManager(getApplicationContext());

        // Check if user is already logged in or not
        if (session.isLoggedIn()) {
            // User is already logged in. Take him to main activity
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }

        // Login button Click Event
        acceder.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {

                String usuario = Usuario.getText().toString().trim();
                String contraseña = Contraseña.getText().toString().trim();

                if (validarCampos.isEmpyt(usuario) == false) {

                    if (validarCampos.isEmpyt(contraseña) == false) {

                        checkLogin(usuario, contraseña);

                    } else {

                        Toast.makeText(LoginActivity.this,"Campo Contraseña Vacio", Toast.LENGTH_SHORT).show();
                    }

                } else {

                    Toast.makeText(LoginActivity.this,"Campo Usuario Vacio", Toast.LENGTH_SHORT).show();
                }
            }
        });

        registrar.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {

                Uri uri = Uri.parse("https://sistema-control-de-obra.000webhostapp.com/registro_arq.php");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);

            }
        });

        olvidar.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {

                Uri uri = Uri.parse("https://sistema-de-control-de-obra.000webhostapp.com/restablecer_arq.php");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);

            }
        });


    }

    /**
     * function to verify login details in mysql db
     */
    private void checkLogin(final String usuario, final String contraseña) {
        // Tag used to cancel the request
        String tag_string_req = "req_login";

        pDialog.setMessage("Comprobando ...");
        showDialog();

        StringRequest strReq = new StringRequest(Method.POST, AppConfig.URL_LOGIN, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Login Response: " + response.toString());
                hideDialog();

                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean error = jObj.getBoolean("error");

                    // Check for error node in json
                    if (!error) {
                        // user successfully logged in
                        // Create login session
                        session.setLogin(true);

                        // Now store the user in SQLite
                        String uid = jObj.getString("uid");

                        JSONObject user = jObj.getJSONObject("user");
                        String name = user.getString("name");
                        String email = user.getString("email");
                        String created_at = user.getString("created_at");

                        // Inserting row in users table
                        db.addUser(name, email, uid, created_at);



                        // Launch main activity
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();

                    } else {
                        // Error in login. Get the error message
                        String errorMsg = jObj.getString("error_msg");
                        Toast.makeText(getApplicationContext(), errorMsg, Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    // JSON error
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Json error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Login Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                hideDialog();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put("email", usuario);
                params.put("password", contraseña);

                return params;
            }
        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();

    }
}