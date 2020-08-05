package co.com.taskmanagement.infrastructure.transaction;

import co.com.taskmanagement.domain.transaction.TransactionType;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class TransactionRequestDTO implements Serializable {

  private String type;
  private Double amount;

}
