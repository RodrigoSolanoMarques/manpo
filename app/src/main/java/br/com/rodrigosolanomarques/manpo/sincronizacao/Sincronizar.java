package br.com.rodrigosolanomarques.manpo.sincronizacao;

import java.util.List;

public class Sincronizar {

    private List<Sincronizar> lista;

    public Sincronizar(List<Sincronizar> lista) {
        this.lista = lista;
    }

    public void sincronizar() {
        for (Sincronizar item : lista) {
            item.sincronizar();
        }
    }
}
