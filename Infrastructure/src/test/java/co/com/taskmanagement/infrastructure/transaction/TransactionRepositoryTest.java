package co.com.taskmanagement.infrastructure.transaction;

import co.com.taskmanagement.domain.transaction.TransactionType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class TransactionRepositoryTest {
    @Autowired
    TransactionRepository transactionRepository;

    @Test
    @Rollback(false)
    public void createTransaction() {
        transactionRepository.save(TransactionEntity.builder()
                .amount(Double.valueOf(0))
                .transactionType(TransactionType.CREDIT)
        .build());
    }
}
