package co.com.taskmanagement.infrastructure.transaction;

import co.com.taskmanagement.domain.transaction.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity(name = "transaction")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "creation_type")
  private TransactionType transactionType;

  @Column
  private Double amount;

  @Builder.Default
  @Column(name = "effective_date")
  private Timestamp effectiveDate = new Timestamp(System.currentTimeMillis());

  public boolean isEmpty() {
    return id == null && transactionType == null;
  }
}
