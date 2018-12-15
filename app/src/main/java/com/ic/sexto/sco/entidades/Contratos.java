package com.ic.sexto.sco.entidades;

public class Contratos {

        private String id_contrato;

        public Contratos(){

        }

        public Contratos(String id_contrato){

            this.setId_contrato(id_contrato);

        }

    public String getId_contrato() {
        return id_contrato;
    }

    public void setId_contrato(String id_contrato) {
        this.id_contrato = id_contrato;
    }
}

