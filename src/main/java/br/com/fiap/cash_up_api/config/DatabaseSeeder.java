package br.com.fiap.cash_up_api.config;

import java.util.List;

import br.com.fiap.cash_up_api.models.Category;
import br.com.fiap.cash_up_api.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
public class DatabaseSeeder {

    @Autowired
    private CategoryRepository categoryRepository;

    @PostConstruct
    public void categorySeed() {
        categoryRepository.saveAll(
                List.of(
                        Category.builder().name("Educação").icon("Book").build(),
                        Category.builder().name("Lazer").icon("Dices").build(),
                        Category.builder().name("Saúde").icon("Heart").build()));
    }

}
