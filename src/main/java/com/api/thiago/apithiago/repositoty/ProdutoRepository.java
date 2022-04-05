package com.api.thiago.apithiago.repositoty;

import com.api.thiago.apithiago.domain.Produto;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    Produto findById(long id);
}
