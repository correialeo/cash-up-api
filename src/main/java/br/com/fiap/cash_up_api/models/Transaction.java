package br.com.fiap.cash_up_api.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Required field")
    @Size(min=5, max = 255, message = "Description must be five characters")
    private String description;
    @Positive(message = "Value must be greater than zero")
    private BigDecimal amount;
    @PastOrPresent(message = "Date cannot be in the future")
    private LocalDate date;
    @NotNull(message = "Required field")
    @Enumerated(EnumType.STRING)
    private ETransactionType type;
    @NotNull(message = "RequiredField")
    @ManyToOne
    private Category category;
}
