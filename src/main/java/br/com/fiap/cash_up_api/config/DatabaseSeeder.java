package br.com.fiap.cash_up_api.config;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import br.com.fiap.cash_up_api.models.Category;
import br.com.fiap.cash_up_api.models.ETransactionType;
import br.com.fiap.cash_up_api.models.Transaction;
import br.com.fiap.cash_up_api.repositories.CategoryRepository;
import br.com.fiap.cash_up_api.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
public class DatabaseSeeder {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @PostConstruct
    public void init() {
        var categories = List.of(
                Category.builder().name("Educação").icon("Book").build(),
                Category.builder().name("Lazer").icon("Dices").build(),
                Category.builder().name("Saúde").icon("Heart").build(),
                Category.builder().name("Alimentação").icon("Apple").build(),
                Category.builder().name("Transporte").icon("Bus").build()
        ) ;

        categoryRepository.saveAll(categories);

        var descriptions = List.of(
                "Mercado semanal",
                "Manutenção do carro",
                "Plano de internet",
                "Assinatura do streaming",
                "Farmácia",
                "Gasolina",
                "Presente de aniversário",
                "Academia",
                "Roupas novas",
                "Lazer no fim de semana",
                "Investimento mensal",
                "Seguro residencial",
                "Parcelamento do celular",
                "Curso online de programação",
                "Serviço de jardinagem",
                "Pagamento da escola",
                "Consulta médica",
                "Revisão da moto",
                "Conta de água",
                "Pagamento do cartão"
        );

        var transactions = new ArrayList< Transaction >();
        for (int i = 0; i<50; i++){
            transactions.add(
            Transaction.builder()
                    .description(descriptions.get(new Random().nextInt(descriptions.size())))
                    .amount(BigDecimal.valueOf(new Random().nextDouble() * 500))
                    .date(LocalDate.now().minusDays(new Random().nextInt(30)))
                    .type(ETransactionType.EXPENSE)
                    .category(categories.get(new Random().nextInt(categories.size())))
                    .build());

        }
        transactionRepository.saveAll(transactions);
    }


}
