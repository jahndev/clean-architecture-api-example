package co.com.taskmanagement.domain.transaction;

import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor(staticName = "of")
public class TransactionUseCase implements TransactionInputPort {

  private TransactionOutputPort transactionOutputPort;

  @Override
  public Transaction getTransactionById(Long transactionId) {
    return transactionOutputPort.findTransactionById(transactionId);
  }

  @Override
  public List<Transaction> getAllTransactions() {
    return transactionOutputPort.findAllTransaction();
  }

  @Override
  public Transaction createTransaction(Transaction board) {
    return transactionOutputPort.createTransaction(board);
  }

  @Override
  public BigDecimal getTotalAmount() {
    return transactionOutputPort.getTotalAmount();
  }

}
