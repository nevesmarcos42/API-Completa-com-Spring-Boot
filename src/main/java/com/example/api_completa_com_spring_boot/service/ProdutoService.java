package com.example.api_completa_com_spring_boot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.api_completa_com_spring_boot.model.Produto;
import com.example.api_completa_com_spring_boot.repository.ProdutoRepository;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public List<Produto> listarProdutos() {
        return produtoRepository.findAll();
    }

    public Optional<Produto> buscarProdId(Long id) {
        return produtoRepository.findById(id);
    }

    public Produto salvarProduto(Produto produto) {
        return produtoRepository.save(produto);
    }

    public void deletarProduto(Long id) {
        produtoRepository.deleteById(id);
    }

    /*
     * public Produto atualizarProduto(Long id, Produto produto) {
     * if (produtoRepository.existsById(id)) {
     * return produtoRepository.save(produto);
     * }
     * return null; // ou lançar uma exceção
     * }
     */
}
