package com.api.thiago.apithiago.repository;

import com.api.thiago.apithiago.domain.Produto;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    Produto findById(long id);

}
