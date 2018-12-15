package com.ic.sexto.sco.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ic.sexto.sco.entidades.Usuario;

import java.util.List;

import co.ic.sexto.sco.R;

public class UsuariosImagenAdapter extends RecyclerView.Adapter<UsuariosImagenAdapter.UsuariosHolder>{

    List<Usuario> listaUsuarios;

    public UsuariosImagenAdapter(List<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    @Override
    public UsuariosHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View vista= LayoutInflater.from(parent.getContext()).inflate(R.layout.usuarios_list_image,parent,false);
        RecyclerView.LayoutParams layoutParams=new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        vista.setLayoutParams(layoutParams);
        return new UsuariosHolder(vista);
    }

    @Override
    public void onBindViewHolder(UsuariosHolder holder, int position) {
        holder.txtnombre.setText(listaUsuarios.get(position).getNombre_archivo().toString());
        holder.txtcontrato.setText(listaUsuarios.get(position).getContratos_id_contrato().toString());
        holder.txtfecha.setText(listaUsuarios.get(position).getFecha().toString());
        holder.txtdescripcion.setText(listaUsuarios.get(position).getDescripcion().toString());


        if (listaUsuarios.get(position).getImagen()!=null){
            holder.imagen.setImageBitmap(listaUsuarios.get(position).getImagen());
        }else{
            holder.imagen.setImageResource(R.drawable.img_base);
        }
    }

    @Override
    public int getItemCount() {
        return listaUsuarios.size();
    }

    public class UsuariosHolder extends RecyclerView.ViewHolder{

        TextView txtnombre,txtcontrato,txtfecha,txtdescripcion;
        ImageView imagen;

        public UsuariosHolder(View itemView) {
            super(itemView);
            txtnombre= (TextView) itemView.findViewById(R.id.id_nombre_contenido);
            txtcontrato= (TextView) itemView.findViewById(R.id.id_contrato_contenido);
            txtfecha= (TextView) itemView.findViewById(R.id.id_fecha_contenido);
            txtdescripcion= (TextView) itemView.findViewById(R.id.id_descripcion_contenido);
            imagen=(ImageView) itemView.findViewById(R.id.idImagen);
        }
    }
}
