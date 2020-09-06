package co.com.taskmanagement.infrastructure.transaction;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.time.ZoneOffset;

import static java.time.LocalDateTime.now;

@Data
@Builder
public class TransactionResponseDTO {

    private String id;
    private String transactionType;
    private Double amount;
    @Builder.Default
    private Instant effectiveDate = now().toInstant(ZoneOffset.UTC);
}
