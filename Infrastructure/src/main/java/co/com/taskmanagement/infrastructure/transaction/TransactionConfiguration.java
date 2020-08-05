package co.com.taskmanagement.infrastructure.transaction;

import co.com.taskmanagement.domain.transaction.TransactionUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class TransactionConfiguration {

  private final TransactionService transactionService;

  @Bean
  public TransactionUseCase boardUseCase() {
    return TransactionUseCase.of(transactionService);
  }

}
