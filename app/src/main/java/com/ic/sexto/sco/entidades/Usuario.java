package com.ic.sexto.sco.entidades;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

public class Usuario {

    private String usuarios_id_usuarios;
    private String descripcion;
    private String contratos_id_contrato;
    private String nombre_archivo;
    private String fecha;
    private String dato;
    private Bitmap imagen;
    private String rutaImagen;
    private int id_galeria;
    private int id_foto;
    private String nombre_archivo_imagen;
    private String ruta_imagen;
    private int galeria_usuario_id_galeria;


    public String getRutaImagen() {
        return rutaImagen;
    }

    public void setRutaImagen(String rutaImagen) {
        this.rutaImagen = rutaImagen;
    }

    public String getDato() {
        return dato;
    }

    public void setDato(String dato) {
        this.dato = dato;

        try {
            byte[] byteCode = Base64.decode(dato, Base64.DEFAULT);
            //this.imagen= BitmapFactory.decodeByteArray(byteCode,0,byteCode.length);

            int alto = 100;//alto en pixeles
            int ancho = 150;//ancho en pixeles

            Bitmap foto = BitmapFactory.decodeByteArray(byteCode, 0, byteCode.length);
            this.imagen = Bitmap.createScaledBitmap(foto, alto, ancho, true);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Bitmap getImagen() {
        return imagen;
    }

    public void setImagen(Bitmap imagen) {
        this.imagen = imagen;
    }

    public String getUsuarios_id_usuarios() {
        return usuarios_id_usuarios;
    }

    public void setUsuarios_id_usuarios(String usuarios_id_usuarios) {
        this.usuarios_id_usuarios = usuarios_id_usuarios;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getContratos_id_contrato() {
        return contratos_id_contrato;
    }

    public void setContratos_id_contrato(String contratos_id_contrato) {
        this.contratos_id_contrato = contratos_id_contrato;
    }

    public String getNombre_archivo() {
        return nombre_archivo;
    }

    public void setNombre_archivo(String nombre_archivo) {
        this.nombre_archivo = nombre_archivo;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getId_galeria() {
        return id_galeria;
    }

    public void setId_galeria(int id_galeria) {
        this.id_galeria = id_galeria;
    }

    public int getId_foto() {
        return id_foto;
    }

    public void setId_foto(int id_foto) {
        this.id_foto = id_foto;
    }

    public String getNombre_archivo_imagen() {
        return nombre_archivo_imagen;
    }

    public void setNombre_archivo_imagen(String nombre_archivo_imagen) {
        this.nombre_archivo_imagen = nombre_archivo_imagen;
    }

    public String getRuta_imagen() {
        return ruta_imagen;
    }

    public void setRuta_imagen(String ruta_imagen) {
        this.ruta_imagen = ruta_imagen;
    }

    public int getGaleria_usuario_id_galeria() {
        return galeria_usuario_id_galeria;
    }

    public void setGaleria_usuario_id_galeria(int galeria_usuario_id_galeria) {
        this.galeria_usuario_id_galeria = galeria_usuario_id_galeria;
    }
}
