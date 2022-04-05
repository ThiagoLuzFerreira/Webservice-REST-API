package com.api.thiago.apithiago.domain;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Faturado {

    SIM("sim"), NAO("nao");

    private String tipo;

    Faturado(String tipo){
        this.tipo = tipo;
    }

    @JsonValue
    public String getTipo(){
        return tipo;
    }
}
