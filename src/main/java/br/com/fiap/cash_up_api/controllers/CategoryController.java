package br.com.fiap.cash_up_api.controllers;

import br.com.fiap.cash_up_api.models.Category;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class CategoryController {

    private List<Category> repository = new ArrayList<>();

    @GetMapping("/category")
    public List<Category> index(){
        return repository;
    }

    @PostMapping("/category")
//    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Category> create(@RequestBody Category category){
        repository.add(category);
        return ResponseEntity.status(201).body(category);
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<Optional<Category>> show(@PathVariable Long id){

        // optional -> pode existir ou nao
        Optional<Category> cat = repository.stream()
                .filter(x -> x.getId().equals(id))
                .findFirst();

        if (!cat.isPresent()) return ResponseEntity.status(404).build();

        return ResponseEntity.status(200).body(cat);
    }

    @DeleteMapping("/category/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable Long id){
        repository.removeIf(category -> category.getId().equals(id));
        return ResponseEntity.status(204).build();
    }
}
