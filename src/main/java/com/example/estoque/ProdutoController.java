package com.example.estoque;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class ProdutoController {
    private final List<Produto> produtos = new ArrayList<>();
    private final ApplicationEventPublisher eventPublisher;
    private final NotificacaoService notificacaoService;

    public ProdutoController(ApplicationEventPublisher eventPublisher, NotificacaoService notificacaoService) {
        this.eventPublisher = eventPublisher;
        this.notificacaoService = notificacaoService;
        produtos.add(new Produto(1L, "Produto A", 15));
        produtos.add(new Produto(2L, "Produto B", 30));
    }

    @GetMapping("/produtos")
    public List<Produto> listarProdutos() {
        return produtos;
    }

    @PostMapping("/produtos")
    public Produto adicionarProduto(@RequestBody Produto produto) {
        produtos.add(produto);
        verificarEstoque(produto);
        return produto;
    }

    private void verificarEstoque(Produto produto) {
        if (produto.getQuantidade() < 10) {
            eventPublisher.publishEvent(new BaixoEstoqueEvent(produto));
        }
    }
}