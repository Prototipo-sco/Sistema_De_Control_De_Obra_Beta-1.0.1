package com.ic.sexto.sco.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ic.sexto.sco.entidades.Usuario;

import java.util.List;

import co.ic.sexto.sco.R;


public class UsuariosAdapter extends RecyclerView.Adapter<UsuariosAdapter.UsuariosHolder>{

    List<Usuario> listaUsuarios;

    public UsuariosAdapter(List<Usuario> listaUsuarios) {
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
        holder.txtDocumento.setText(listaUsuarios.get(position).getContratos_id_contrato().toString());
        holder.txtNombre.setText(listaUsuarios.get(position).getDescripcion().toString());

    }

    @Override
    public int getItemCount() {
        return listaUsuarios.size();
    }

    public class UsuariosHolder extends RecyclerView.ViewHolder{

        TextView txtDocumento,txtNombre,txtProfesion;

        public UsuariosHolder(View itemView) {
            super(itemView);
            //xtDocumento= (TextView) itemView.findViewById(R.id.txtDocumento);
            txtNombre= (TextView) itemView.findViewById(R.id.txtNombre);
            txtProfesion= (TextView) itemView.findViewById(R.id.txtProfesion);

        }
    }
}
