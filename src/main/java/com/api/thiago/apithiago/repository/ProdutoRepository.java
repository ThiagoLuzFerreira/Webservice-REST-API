package com.api.thiago.apithiago.repository;

import com.api.thiago.apithiago.domain.Faturado;
import com.api.thiago.apithiago.domain.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;


public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    Produto findById(long id);

    @Modifying
    @Query("update Produto p set p.nome = ?1, p.faturado = ?2, p.quantidade = ?3, p.valor = ?4 where p.id = ?5")
    void update(String nome, Faturado faturado, Integer quantidade, Float valor, long id);
}
