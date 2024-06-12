package com.example.estoque;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Produto {
    private Long id;
    private String nome;
    private int quantidade;
}