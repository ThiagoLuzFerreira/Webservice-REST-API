package com.api.thiago.apithiago.service;

import com.api.thiago.apithiago.domain.Produto;
import com.api.thiago.apithiago.exception.IdInvalidoException;
import com.api.thiago.apithiago.exception.NaoExisteDaoException;
import com.api.thiago.apithiago.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
@Transactional
public class ProdutoService {

    @Autowired
    private ProdutoRepository repository;

//    @PersistenceContext
//    EntityManager em;

    public Produto editarValor(long id, Float valor){
        Produto produto = new Produto();
        produto = repository.findById(idValido(id));
        produto.setValor(valor);
        return produto;
    }

    public Produto findById(long id){
        Produto produto = repository.findById(idValido(id));
        if(produto == null){
            throw new NaoExisteDaoException("Produto não encontrado para o id " +id+".");
        }
        return produto;
    }

    public void delete(long id, Produto produto){
        try {
            produto = repository.findById(idValido(id));
            repository.delete(produto);
        } catch (EntityNotFoundException ex){
            throw new NaoExisteDaoException("Produto não encontrado para o id " +id+".");
        }
    }

    private long idValido(long id){
        if(id <= 0){
            throw new IdInvalidoException("Valor do ID inválido. Deve ser um número inteiro maior que zero");
        }
        return id;
    }

//    public void atualizar(long id, Produto produto){
//        produto.setId(id);
//        em.merge(produto);
//    }

    public void update(Produto produto) {
        repository.update(produto.getNome(), produto.getFaturado(), produto.getQuantidade(), produto.getValor(), produto.getId());
    }
}
