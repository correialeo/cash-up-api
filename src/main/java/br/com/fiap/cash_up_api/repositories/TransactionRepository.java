package br.com.fiap.cash_up_api.repositories;

import br.com.fiap.cash_up_api.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query("SELECT t FROM Transaction t")
    List<Transaction> findByDescription(String description);
}
