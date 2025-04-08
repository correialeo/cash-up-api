package br.com.fiap.cash_up_api.repositories;

import br.com.fiap.cash_up_api.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

//    List<Transaction> findByDescriptionContainingIgnoringCase(String description);
//    List<Transaction> findByDescriptionContainingIgnoringCaseAndDate(String description, LocalDate date);
//    List<Transaction> findByDate(LocalDate date);
}
