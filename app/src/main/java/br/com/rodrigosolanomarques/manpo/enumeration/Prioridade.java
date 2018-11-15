package br.com.rodrigosolanomarques.manpo.enumeration;

public enum Prioridade {
    ALTA("Alta"), BAIXA("Baixa"), MEDIA("Media");

    private String valor;

    Prioridade(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }
}
