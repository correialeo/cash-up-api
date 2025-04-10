package br.com.fiap.cash_up_api.controllers;

import br.com.fiap.cash_up_api.models.Transaction;
import br.com.fiap.cash_up_api.repositories.TransactionRepository;
import br.com.fiap.cash_up_api.specification.TransationSpecification;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("api/transaction")
@Slf4j
public class TransactionController {

    public record TransactionFilter(String description, LocalDate startDate, LocalDate endDate, BigDecimal amount){}

    @Autowired
    private TransactionRepository repository;

    @GetMapping
    public List<Transaction> index(TransactionFilter filter)
    {
        Specification<Transaction> specification = TransationSpecification.withFilters(filter);
        return repository.findAll(specification);
    }


}
