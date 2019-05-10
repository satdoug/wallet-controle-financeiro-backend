package com.wallet.domain.enuns;


public enum TipoTransacao {

    RECEITA(1, "Receita"),
    DESPESA(2, "Despesa");

    private int id;
    private String descricao;

    TipoTransacao(int id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public static TipoTransacao toEnum(Integer cod) {
        if (cod == null) {
            return null;
        }

        for (TipoTransacao x : TipoTransacao.values()) {
            if (cod.equals(x.getId())) {
                return x;
            }
        }
        throw new IllegalArgumentException("Id inválido: " + cod);
    }

    public int getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }
}
