package br.com.fiap.cash_up_api.specification;

import br.com.fiap.cash_up_api.controllers.TransactionController;
import br.com.fiap.cash_up_api.models.Transaction;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class TransationSpecification {

    public static Specification<Transaction> withFilters(TransactionController.TransactionFilter filter){
        return (root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (filter.description() != null && !filter.description().isBlank()){
                predicates.add(
                        builder.like(
                                builder.lower(root.get("description")), "%" + filter.description().toLowerCase() + "%"
                        )
                );
            }
            return predicates.get(0);
        };
    }
}
