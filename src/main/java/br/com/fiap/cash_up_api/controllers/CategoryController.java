package br.com.fiap.cash_up_api.controllers;

import br.com.fiap.cash_up_api.models.Category;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CategoryController {

    @GetMapping("/category")
    public List<Category> index(){
        return List.of(
                new Category(1L, "Alimentos", "/assets/food"),
                new Category(2L, "Lazer", "/assets/casino"),
                new Category(3L, "Saúde", "/assets/health"),
                new Category(4L, "Outros", "/assets/others")
        );
    }
}
