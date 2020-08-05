package co.com.taskmanagement.domain.transaction;

import java.math.BigDecimal;
import java.util.List;

public interface TransactionInputPort {

  Transaction getTransactionById(Long transactionId);
  List<Transaction> getAllTransactions();
  Transaction createTransaction(Transaction transaction);
  BigDecimal getTotalAmount();
}
