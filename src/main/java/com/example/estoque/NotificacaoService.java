package com.example.estoque;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class NotificacaoService {

    private final JavaMailSender mailSender;

    public NotificacaoService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void notificarBaixoEstoque(BaixoEstoqueEvent event) {
        Produto produto = event.getProduto();
        log.info("ATENÇÃO: Produto com baixo estoque - {} (ID: {}) - Quantidade: {}",
                produto.getNome(), produto.getId(), produto.getQuantidade());

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("mkparanho@gmail.com");
        message.setTo("mkvparanho@gmail.com");
        message.setSubject("Alerta de Baixo Estoque!");
        message.setText(
                "O produto " + produto.getNome() + " está com baixo estoque. Quantidade: " + produto.getQuantidade());

        mailSender.send(message);
    }
}