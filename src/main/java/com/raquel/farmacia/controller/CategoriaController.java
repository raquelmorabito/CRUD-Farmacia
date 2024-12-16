package com.raquel.farmacia.controller;

import java.util.List;
import java.util.Map;

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

import com.raquel.farmacia.Repository.CategoriaRepository;
import com.raquel.farmacia.model.Categoria; // Certifique-se de que está importando a entidade correta.

@RestController
@RequestMapping("/categorias")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CategoriaController { 

    @Autowired
    private CategoriaRepository categoriaRepository;

    // Método 1: Listar todas as categorias
    @GetMapping
    public ResponseEntity<List<Categoria>> getAll() {
        return ResponseEntity.ok(categoriaRepository.findAll());
    }

    // Método 2: Buscar categoria por ID
    @GetMapping("/{id}")
    public ResponseEntity<Categoria> getById(@PathVariable Long id) {
        return categoriaRepository.findById(id)
                .map(resposta -> ResponseEntity.ok(resposta))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Método 3: Buscar categorias por nome
    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<Categoria>> getByNome(@PathVariable String nome) {
        return ResponseEntity.ok(categoriaRepository.findAllByNomeContainingIgnoreCase(nome));
    }
    
    // Método 4: Criar uma nova categoria
    @PostMapping
    public ResponseEntity<Categoria> postCategoria(@RequestBody Categoria categoria) {
        return ResponseEntity.status(HttpStatus.CREATED).body(categoriaRepository.save(categoria));
    }

    // Método 5: Atualizar uma categoria existente
    @PutMapping
    public ResponseEntity<Categoria> putCategoria(@RequestBody Categoria categoria) {
        return categoriaRepository.findById(categoria.getId())
                .map(resposta -> ResponseEntity.status(HttpStatus.OK).body(categoriaRepository.save(categoria)))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Método 6: Deletar uma categoria por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategoria(@PathVariable Long id) {
        return categoriaRepository.findById(id).map(resposta -> {
            categoriaRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }).orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
}