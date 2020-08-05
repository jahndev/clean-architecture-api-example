package co.com.taskmanagement.domain.transaction;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Builder
public class Transaction {
  private Long id;
  private TransactionType transactionType;
  private Double amount;
  @Builder.Default
  private Timestamp effectiveDate = new Timestamp(System.currentTimeMillis());

  public boolean isEmpty() {
    return id == null && transactionType == null;
  }
}
