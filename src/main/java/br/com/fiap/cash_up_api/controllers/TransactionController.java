package br.com.fiap.cash_up_api.controllers;

import br.com.fiap.cash_up_api.models.Transaction;
import br.com.fiap.cash_up_api.repositories.TransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("api/transaction")
@Slf4j
public class TransactionController {

    record TransactionFilter(String description, LocalDate date, BigDecimal amount){}

    @Autowired
    private TransactionRepository repository;

    @GetMapping
    public List<Transaction> index(TransactionFilter filter)
    {
        log.info("Buscando transações com descrição {} e data {}");
        var probe = Transaction.builder()
                .description(filter.description)
                .date(filter.date)
                .amount(filter.amount)
                .build();

        var matcher = ExampleMatcher.matchingAll()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        var example = Example.of(probe, matcher);
        return repository.findAll(example);
    }


}
