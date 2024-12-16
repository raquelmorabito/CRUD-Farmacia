package com.raquel.farmacia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.http.HttpStatus; 
import org.springframework.http.ResponseEntity; 
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping; 
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.raquel.farmacia.model.Produto;
import com.raquel.farmacia.Repository.ProdutoRepository;


@RestController
@RequestMapping("/produtos")
@CrossOrigin ("*")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    // Método 1: Listar todos os produtos
    @GetMapping
    public ResponseEntity<List<Produto>> getAll() {
        return ResponseEntity.ok(produtoRepository.findAll());
    }

    // Método 2: Buscar produto por ID
    @GetMapping("/{id}")
    public ResponseEntity<Produto> getById(@PathVariable Long id) {
        return produtoRepository.findById(id)
                .map(resposta -> ResponseEntity.ok(resposta))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Método 3: Buscar produtos por nome
    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<Produto>> getByNome(@PathVariable String nome) {
        return ResponseEntity.ok(produtoRepository.findAllByNomeContainingIgnoreCase(nome));
    }

    // Método 4: Criar um novo produto
    @PostMapping
    public ResponseEntity<Produto> postProduto(@RequestBody Produto produto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoRepository.save(produto));
    }

    // Método 5: Atualizar um produto existente
    @PutMapping
    public ResponseEntity<Produto> putProduto(@RequestBody Produto produto) {
        return produtoRepository.findById(produto.getId())
                .map(resposta -> ResponseEntity.status(HttpStatus.OK).body(produtoRepository.save(produto)))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Método 6: Deletar um produto por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduto(@PathVariable Long id) {
        return produtoRepository.findById(id).map(resposta -> {
            produtoRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }).orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
}
