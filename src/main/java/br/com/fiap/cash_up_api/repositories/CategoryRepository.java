package br.com.fiap.cash_up_api.repositories;

import br.com.fiap.cash_up_api.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
