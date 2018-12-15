package com.ic.sexto.sco.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.ic.sexto.sco.entidades.Usuario;
import com.ic.sexto.sco.entidades.VolleySingleton;
import com.ic.sexto.sco.fragments.RegistrarUsuarioFragment;

import java.util.List;

import co.ic.sexto.sco.R;

public class UsuariosImagenesGaleriaUrlAdapter extends RecyclerView.Adapter<UsuariosImagenesGaleriaUrlAdapter.UsuariosHolder> {

    List<Usuario> listaUsuarios;
    //  RequestQueue request;
    Context context;


    public UsuariosImagenesGaleriaUrlAdapter(List<Usuario> listaUsuarios, Context context) {
        this.listaUsuarios = listaUsuarios;
        this.context = context;
        //  request= Volley.newRequestQueue(context);
    }

    @Override
    public UsuariosHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.usuarios_list_imagenes_galeria, parent, false);
        RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        vista.setLayoutParams(layoutParams);
        return new UsuariosHolder(vista);
    }

    @Override
    public void onBindViewHolder(UsuariosHolder holder, int position) {

        if (listaUsuarios.get(position).getRuta_imagen() != null) {
            //
            cargarImagenWebService(listaUsuarios.get(position).getRuta_imagen(), listaUsuarios.get(position).getNombre_archivo_imagen(), holder);
        } else {
            holder.imagen.setImageResource(R.drawable.img_base);
        }
    }

    private void cargarImagenWebService(String rutaImagen, String nombre_imagen, final UsuariosHolder holder) {

        String urlImagen = "https://sistema-de-control-de-obra.000webhostapp.com/galeria/imagenes/" + rutaImagen + "/" + nombre_imagen;
        urlImagen = urlImagen.replace(" ", "%20");

        ImageRequest imageRequest = new ImageRequest(urlImagen, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {
                holder.imagen.setImageBitmap(response);
            }
        }, 0, 0, ImageView.ScaleType.CENTER, null, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Toast.makeText(context,"Error al cargar la imagen",Toast.LENGTH_SHORT).show();
            }
        });
        //request.add(imageRequest);
        VolleySingleton.getIntanciaVolley(context).addToRequestQueue(imageRequest);
    }

    @Override
    public int getItemCount() {
        return listaUsuarios.size();
    }

    public class UsuariosHolder extends RecyclerView.ViewHolder {

        ImageView imagen;


        public UsuariosHolder(View itemView) {
            super(itemView);
            imagen = (ImageView) itemView.findViewById(R.id.idImagen1);


        }
    }
}
