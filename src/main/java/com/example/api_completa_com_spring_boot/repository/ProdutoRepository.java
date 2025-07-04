package com.example.api_completa_com_spring_boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.api_completa_com_spring_boot.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
