package br.com.interaje.mypersonalwallet.models;

import io.realm.RealmObject;
import io.realm.annotations.Ignore;

/**
 * Created by rayquaza on 27/07/16.
 */
public class Saldo extends RealmObject {

    private Double valor;

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }
}
