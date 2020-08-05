package co.com.taskmanagement.infrastructure.transaction;

import co.com.taskmanagement.domain.transaction.Transaction;
import co.com.taskmanagement.domain.transaction.TransactionType;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class TransactionAdapter {

  public static Transaction transactionEntityToTransaction(TransactionEntity transactionEntity) {
    if(transactionEntity.isEmpty()) {
      return Transaction.builder().build();
    }
    return Transaction.builder()
        .id(transactionEntity.getId())
        .amount(transactionEntity.getAmount())
        .effectiveDate(transactionEntity.getEffectiveDate())
        .transactionType(transactionEntity.getTransactionType())
        .build();
  }

  public static TransactionEntity toTransactionEntity(Transaction transaction) {
    return TransactionEntity.builder()
        .amount(transaction.getAmount())
        .effectiveDate(transaction.getEffectiveDate())
        .transactionType(transaction.getTransactionType())
        .build();
  }

  public static Transaction toTransaction(TransactionRequestDTO transactionRequestDTO) {
    return Transaction.builder()
        .amount(transactionRequestDTO.getAmount())
        .transactionType(TransactionType.valueOf(transactionRequestDTO.getType().toUpperCase()))
        .build();
  }

public static TransactionResponseDTO toTransactionResponseDTO(Transaction transaction) {
    return TransactionResponseDTO.builder()
        .id(String.valueOf(transaction.getId()))
        .amount(transaction.getAmount())
        .effectiveDate(Instant.ofEpochMilli(transaction.getEffectiveDate().getTime()))
        .transactionType(transaction.getTransactionType().name())
        .build();
  }
}
