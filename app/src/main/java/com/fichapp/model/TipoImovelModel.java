package com.fichapp.model;

import java.io.Serializable;

/**
 * Created by Rodrigo Costa on 24/12/2017.
 */

public class TipoImovelModel implements Serializable {

    private String codigo;
    private String descricao;

    public TipoImovelModel() {

    }

    public TipoImovelModel(String codigo) {
        this.codigo = codigo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
