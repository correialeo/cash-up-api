package br.com.fiap.cash_up_api.controllers;

import br.com.fiap.cash_up_api.models.Category;
import br.com.fiap.cash_up_api.repositories.CategoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/category")
public class CategoryController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private CategoryRepository repository;

    @GetMapping
    public List<Category> index(){
        log.info("Buscando todas as categorias");
        return repository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Category create(@RequestBody Category category){
        log.info("Criando categoria");
        return repository.save(category);
    }

    @GetMapping("{id}")
    public Category get(@PathVariable Long id){
        log.info("Buscando categoria");
        return getCategory(id);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void destroy(@PathVariable Long id){
        log.info("Deletando categoria");
        repository.delete(getCategory(id));
    }

    @PutMapping("{id}")
    public Category update(@PathVariable Long id, @RequestBody Category category){
        log.info("Alterando categoria");
        repository.delete(getCategory(id));
        category.setId(id);
        return repository.save(category);
    }

    private Category getCategory(Long id){
        return repository.findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException
                                (HttpStatus.NOT_FOUND, "Categprya nao encontrada")
                );
    }
}
