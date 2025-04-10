package br.com.fiap.cash_up_api.controllers;

import br.com.fiap.cash_up_api.models.Transaction;
import br.com.fiap.cash_up_api.repositories.TransactionRepository;
import br.com.fiap.cash_up_api.specification.TransationSpecification;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@RestController
@RequestMapping("api/transaction")
@Slf4j
public class TransactionController {

    public record TransactionFilter(String description, LocalDate startDate, LocalDate endDate, BigDecimal amount){}

    @Autowired
    private TransactionRepository repository;

    @GetMapping
    public Page<Transaction> index(TransactionFilter filter, @PageableDefault(size = 10, sort = "date", direction = Sort.Direction.DESC) Pageable pageable)
    {
        Specification<Transaction> specification = TransationSpecification.withFilters(filter);
        return repository.findAll(specification, pageable);
    }


}
