package co.com.taskmanagement.domain.transaction;

import java.math.BigDecimal;
import java.util.List;

public interface TransactionOutputPort {
  Transaction findTransactionById(Long transactionId);
  List<Transaction> findAllTransaction();
  Transaction createTransaction(Transaction Transaction);
  BigDecimal getTotalAmount();
}
