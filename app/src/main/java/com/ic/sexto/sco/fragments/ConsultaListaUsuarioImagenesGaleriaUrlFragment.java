package com.ic.sexto.sco.fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.ic.sexto.sco.adapter.UsuariosImagenUrlAdapter;
import com.ic.sexto.sco.adapter.UsuariosImagenesGaleriaUrlAdapter;
import com.ic.sexto.sco.entidades.Usuario;
import com.ic.sexto.sco.entidades.VolleySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import co.ic.sexto.sco.R;

public class ConsultaListaUsuarioImagenesGaleriaUrlFragment extends Fragment implements Response.Listener<JSONObject>, Response.ErrorListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    RecyclerView recyclerUsuarios;
    ArrayList<Usuario> listaUsuarios;

    ProgressDialog dialog;

    // RequestQueue request;
    JsonObjectRequest jsonObjectRequest;


    public ConsultaListaUsuarioImagenesGaleriaUrlFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ConsultaListaUsuarioImagenUrlFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ConsultaListaUsuarioImagenesGaleriaUrlFragment newInstance(String param1, String param2) {
        ConsultaListaUsuarioImagenesGaleriaUrlFragment fragment = new ConsultaListaUsuarioImagenesGaleriaUrlFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View vista = inflater.inflate(R.layout.fragment_consulta_lista_usuario_imagenes_galeria_url, container, false);

        listaUsuarios = new ArrayList<>();


        recyclerUsuarios = (RecyclerView) vista.findViewById(R.id.idRecyclerImagen);
        recyclerUsuarios.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerUsuarios.setHasFixedSize(true);

        // request= Volley.newRequestQueue(getContext());


        cargarWebService();

        return vista;

    }

    private void cargarWebService() {
        dialog = new ProgressDialog(getContext());
        dialog.setMessage("Consultando Imagenes");
        dialog.show();

        String url = "https://sistema-de-control-de-obra.000webhostapp.com/inicio/json_Imagenes/wsJSONConsultarListaImagenesDeGaleria.php?id_galeria='1'";

        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, this, this);
        // request.add(jsonObjectRequest);
        VolleySingleton.getIntanciaVolley(getContext()).addToRequestQueue(jsonObjectRequest);
    }

    @Override
    public void onResponse(JSONObject response) {
        Usuario usuario = null;

        JSONArray json = response.optJSONArray("usuario");

        try {

            for (int i = 0; i < json.length(); i++) {
                usuario = new Usuario();
                JSONObject jsonObject = null;
                jsonObject = json.getJSONObject(i);

                usuario.setId_foto(jsonObject.optInt("id_foto"));
                usuario.setNombre_archivo_imagen(jsonObject.optString("nombre_archivo"));
                usuario.setRuta_imagen(jsonObject.optString("ruta_imagen"));
                usuario.setGaleria_usuario_id_galeria(jsonObject.optInt("galeria_usuario_id_galeria"));

                listaUsuarios.add(usuario);
            }
            dialog.hide();
            UsuariosImagenesGaleriaUrlAdapter adapter = new UsuariosImagenesGaleriaUrlAdapter(listaUsuarios, getContext());
            recyclerUsuarios.setAdapter(adapter);

        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(getContext(), "No se ha podido establecer conexión con el servidor" +
                    " " + response, Toast.LENGTH_LONG).show();
            dialog.hide();
        }
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(getContext(), "No se puede conectar " + error.toString(), Toast.LENGTH_LONG).show();
        System.out.println();
        dialog.hide();
        Log.d("ERROR: ", error.toString());
    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
