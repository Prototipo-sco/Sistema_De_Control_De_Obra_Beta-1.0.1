package com.ic.sexto.sco;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.ic.sexto.sco.fragments.LoginActivity;

import co.ic.sexto.sco.R;

import com.ic.sexto.sco.fragments.BienvenidaFragment;
import com.ic.sexto.sco.fragments.ConsultaListaUsuarioImagenUrlFragment;
import com.ic.sexto.sco.fragments.ConsultaUsuarioUrlFragment;
import com.ic.sexto.sco.fragments.RegistrarGaleriaFragment;
import com.ic.sexto.sco.fragments.RegistrarUsuarioFragment;
import com.ic.sexto.sco.helper.SQLiteHandler;
import com.ic.sexto.sco.helper.SessionManager;
import com.ic.sexto.sco.interfaces.IFragments;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, IFragments {

    private SQLiteHandler db;
    private SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        Fragment miFragment = new BienvenidaFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.content_main, miFragment).commit();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // SqLite database handler
        db = new SQLiteHandler(getApplicationContext());

        // session manager
        session = new SessionManager(getApplicationContext());

        if (!session.isLoggedIn()) {
            logoutUser();
        }
    }

    private void logoutUser() {

        session.setLogin(false);

        db.deleteUsers();

        // Launching the login activity
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        Fragment miFragment = null;
        boolean fragmentSeleccionado = false;

        if (id == R.id.nav_inicio) {

            miFragment = new BienvenidaFragment();
            fragmentSeleccionado = true;

        } else if (id == R.id.nav_registro) {

            miFragment = new RegistrarGaleriaFragment();
            fragmentSeleccionado = true;

        } else if (id == R.id.nav_registro_imagenes) {

            miFragment = new RegistrarUsuarioFragment();
            fragmentSeleccionado = true;

        } else if (id == R.id.nav_consulta_Url) {

            miFragment = new ConsultaUsuarioUrlFragment();
            fragmentSeleccionado = true;

        } else if (id == R.id.nav_consulta_gral_img_url) {

            miFragment = new ConsultaListaUsuarioImagenUrlFragment();
            fragmentSeleccionado = true;

        } else if (id == R.id.nav_cerrar_sesion) {

            logoutUser();
        }

        if (fragmentSeleccionado == true) {
            getSupportFragmentManager().beginTransaction().replace(R.id.content_main, miFragment).commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
