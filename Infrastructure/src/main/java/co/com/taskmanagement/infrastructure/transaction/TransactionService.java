package co.com.taskmanagement.infrastructure.transaction;

import co.com.taskmanagement.domain.transaction.Transaction;
import co.com.taskmanagement.domain.transaction.TransactionOutputPort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static co.com.taskmanagement.domain.transaction.TransactionType.CREDIT;

@Service
public class TransactionService implements TransactionOutputPort {

    private final TransactionRepository transactionRepository;
    private final TransactionAdapter transactionAdapter;
    private BigDecimal totalAmount = new BigDecimal(0);

    public TransactionService(TransactionRepository transactionRepository, TransactionAdapter transactionAdapter) {
        this.transactionRepository = transactionRepository;
        this.transactionAdapter = transactionAdapter;
    }

    @Override
    public Transaction findTransactionById(Long boardId) {
        TransactionEntity transactionEntity = transactionRepository.findById(boardId).orElse(TransactionEntity.builder().build());
        return transactionAdapter.transactionEntityToTransaction(transactionEntity);
    }

    @Override
    public List<Transaction> findAllTransaction() {
        Iterable<TransactionEntity> entities = transactionRepository.findAll();

        return StreamSupport.stream(
                Spliterators.spliteratorUnknownSize(entities.iterator(), Spliterator.ORDERED), false)
                .map(TransactionAdapter::transactionEntityToTransaction)
                .collect(Collectors.toList());
    }

    public BigDecimal getTotalAmount(){
        return totalAmount;
    }

    @Override
    public Transaction createTransaction(Transaction transaction) {
        TransactionEntity transactionEntityToSave = TransactionAdapter.toTransactionEntity(transaction);
        TransactionEntity savedTransactionEntity = transactionRepository.save(transactionEntityToSave);
        updateTotalAmount(savedTransactionEntity);
        return TransactionAdapter.transactionEntityToTransaction(savedTransactionEntity);
    }

    private void updateTotalAmount(TransactionEntity savedTransactionEntity) {
        if(CREDIT.equals(savedTransactionEntity.getTransactionType())) {
            totalAmount.add(BigDecimal.valueOf(savedTransactionEntity.getAmount()));
        } else {
            totalAmount.subtract(BigDecimal.valueOf(savedTransactionEntity.getAmount()));
        }
    }
}
