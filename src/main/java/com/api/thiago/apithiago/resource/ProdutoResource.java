package com.api.thiago.apithiago.resource;

import com.api.thiago.apithiago.domain.Produto;
import com.api.thiago.apithiago.repositoty.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/apithiago")
public class ProdutoResource {

    @Autowired
    private ProdutoRepository repo;

    @PostMapping("/produtos")
    @ResponseStatus(HttpStatus.CREATED)
    public Produto salvar(@RequestBody Produto produto){
        return repo.save(produto);
    }

    @GetMapping("/produtos")
    public List<Produto> listar(){
        return repo.findAll();
    }

    @GetMapping("/produtos/{id}")
    public Produto listarPorId(@PathVariable("id") long id){
        return repo.findById(id);
    }

    @DeleteMapping("/produtos/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable("id") long id, Produto produto){
        produto = repo.findById(id);
        repo.delete(produto);
    }

    @PutMapping("/produtos")
    public Produto editar(@RequestBody Produto produto){
        return repo.save(produto);
    }
}
