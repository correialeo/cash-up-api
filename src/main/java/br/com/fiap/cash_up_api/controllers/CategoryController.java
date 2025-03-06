package br.com.fiap.cash_up_api.controllers;

import br.com.fiap.cash_up_api.models.Category;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class CategoryController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private List<Category> repository = new ArrayList<>();

    @GetMapping("/category")
    public List<Category> index(){
        log.info("Buscando todas as categorias");
        return repository;
    }

    @PostMapping("/category")
    public ResponseEntity<Category> create(@RequestBody Category category){
        log.info("Criando categoria");
        repository.add(category);
        return ResponseEntity.status(201).body(category);
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<Category> get(@PathVariable Long id){
        log.info("Buscando categoria");
        return ResponseEntity.status(200).body(getCategory(id));
    }

    @DeleteMapping("/category/{id}")
    public ResponseEntity<HttpStatus> destroy(@PathVariable Long id){
        log.info("Deletando categoria");
        repository.remove(getCategory(id));
        // repository.removeIf(category -> category.getId().equals(id));
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/category/{id}")
    public ResponseEntity<Category> update(@PathVariable Long id, @RequestBody Category category){
        log.info("Alterando categoria");
        repository.remove(getCategory(id));
        category.setId(id);
        repository.add(category);
        return ResponseEntity.ok(category);
    }

    private Category getCategory(Long id){
        return repository.stream()
                .filter( x -> x.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "categoria nao encontrada"));
    }
}
