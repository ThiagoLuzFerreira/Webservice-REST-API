package com.api.thiago.apithiago.resource;

import com.api.thiago.apithiago.domain.Produto;
import com.api.thiago.apithiago.repository.ProdutoRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/apithiago")
@Api(value="API REST Produtos")
@CrossOrigin(origins = "*")
public class ProdutoResource {

    @Autowired
    private ProdutoRepository repo;

    @PostMapping("/produtos")
    @ApiOperation(value="Salva um produto")
    @ResponseStatus(HttpStatus.CREATED)
    public Produto salvar(@RequestBody Produto produto){
        return repo.save(produto);
    }

    @GetMapping("/produtos")
    @ApiOperation(value="Retorna lista de produtos")
    public List<Produto> listar(){
        return repo.findAll();
    }

    @GetMapping("/produtos/{id}")
    @ApiOperation(value="Busca um produto único")
    public Produto listarPorId(@PathVariable("id") long id){
        return repo.findById(id);
    }

    @DeleteMapping("/produtos/{id}")
    @ApiOperation(value="Deleta um produto")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable("id") long id, Produto produto){
        produto = repo.findById(id);
        repo.delete(produto);
    }

    @PutMapping("/produtos")
    @ApiOperation(value="Edita um produto")
    public Produto editar(@RequestBody Produto produto){
        return repo.save(produto);
    }
}
