package com.example.estoque;

public class BaixoEstoqueEvent {
    private final Produto produto;

    public BaixoEstoqueEvent(Produto produto) {
        this.produto = produto;
    }

    public Produto getProduto() {
        return produto;
    }
}