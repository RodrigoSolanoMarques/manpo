package br.com.rodrigosolanomarques.manpo.enumeration;

public enum Prioridade {
    ALTA("Alta", 0), MEDIA("Media", 1), BAIXA("Baixa", 2);

    private String valor;
    private int ordem;

    Prioridade(String valor, int ordem) {
        this.valor = valor;
        this.ordem = ordem;
    }

    public String getValor() {
        return valor;
    }

    public int getOrdem() {
        return ordem;
    }
}
