package com.api.thiago.apithiago.resource;

import com.api.thiago.apithiago.domain.Produto;
import com.api.thiago.apithiago.repository.ProdutoRepository;
import com.api.thiago.apithiago.service.ProdutoService;
import com.fasterxml.jackson.annotation.JsonProperty;
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

    @Autowired
    private ProdutoService service;

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
        return service.findById(id);
    }

    @DeleteMapping("/produtos/{id}")
    @ApiOperation(value="Deleta um produto")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable("id") long id, Produto produto){
        produto = service.findById(id);
        service.delete(id, produto);
    }

//    @PutMapping("/produtos")
//    @ApiOperation(value="Edita um produto")
//    public Produto editar(@RequestBody Produto produto){
//        return repo.save(produto);
//    }

    @PatchMapping("/produtos/{id}")
    @ApiOperation(value="Edita valor de um produto")
    public Produto editarValor(@PathVariable("id") long id, @RequestBody Produto produto){

        return service.editarValor(id, produto.getValor());
    }

//    @PatchMapping("/produtos/{id}")
//    @ApiOperation(value="Edita o valor através de parâmetro")
//    public Produto editarValor(@PathVariable("id") long id, @RequestParam("valor") Float valor){
//        Produto produto = new Produto();
//        produto = repo.findById(id);
//        produto.setValor(valor);
//        return repo.save(produto);
//    }

    @PutMapping("/produtos/{id}")
    @ApiOperation(value="Edita um produto")
    public Produto editar2 (@PathVariable("id") long id, @RequestBody Produto produto){
        service.atualizar(id, produto);
        return produto;
    }
}
