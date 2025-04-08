package br.com.fiap.cash_up_api.controllers;

import br.com.fiap.cash_up_api.models.Category;
import br.com.fiap.cash_up_api.repositories.CategoryRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/category")
@Slf4j
public class CategoryController {

    @Autowired
    private CategoryRepository repository;

    @GetMapping
    // @Cacheable
    @Operation(description = "Get all categories",
    tags = "category", summary = "Categories list")
    public List<Category> index(){
        log.info("Buscando todas as categorias");
        return repository.findAll();
    }

    @PostMapping
//    @CacheEvict(value = "category", allEntries = true)
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(responses = {
            @ApiResponse(responseCode = "400", description = "Validation error")
    })
    public Category create(@RequestBody @Valid Category category){
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
    public Category update(@PathVariable Long id, @RequestBody @Valid Category category){
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
