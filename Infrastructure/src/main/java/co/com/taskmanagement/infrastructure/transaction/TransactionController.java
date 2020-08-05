package co.com.taskmanagement.infrastructure.transaction;

import co.com.taskmanagement.domain.transaction.Transaction;
import co.com.taskmanagement.domain.transaction.TransactionInputPort;
import co.com.taskmanagement.domain.transaction.TransactionType;
import co.com.taskmanagement.infrastructure.application.URIManager;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/transaction")
@AllArgsConstructor
public class TransactionController {

    private final TransactionInputPort transactionInputPort;

    private final TransactionAdapter transactionAdapter;

    @GetMapping("/{id}")
    public ResponseEntity<TransactionResponseDTO> getTransactionById(@PathVariable @NotNull Long id) {
        Transaction transaction = transactionInputPort.getTransactionById(id);
        if(transaction.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(transactionAdapter.toTransactionResponseDTO(transaction));
    }

    @GetMapping("/")
    public ResponseEntity<List<TransactionResponseDTO>> getAllTransaction() {
        List<Transaction> allTransactions = transactionInputPort.getAllTransactions();
        return ResponseEntity.ok().body(allTransactions.stream()
                .map(TransactionAdapter::toTransactionResponseDTO)
                .collect(Collectors.toList()));
    }


    @PostMapping
    public ResponseEntity<TransactionResponseDTO> createTransaction(
            @RequestBody TransactionRequestDTO requestBody) {
        Double amount = requestBody.getAmount();
        if (isInvalidAmount(requestBody, amount)) {
            return ResponseEntity.unprocessableEntity().build();
        }
        Transaction transaction =
                transactionInputPort.createTransaction(
                        transactionAdapter.toTransaction(requestBody));
        TransactionResponseDTO transactionResponseDTO =
                transactionAdapter.toTransactionResponseDTO(transaction);

        return ResponseEntity
                .created(URIManager.of().createURIFromPath("/{id}", transactionResponseDTO.getId()))
                .body(transactionResponseDTO);
    }

    private boolean isInvalidAmount(TransactionRequestDTO requestBody, Double amount) {
        String type = requestBody.getType().toUpperCase();
        boolean isInvalid;
            isInvalid = amount.doubleValue() <= 0
                    || Arrays.stream(TransactionType.values()).noneMatch(t -> type.equals(t.name()))
                    || (TransactionType.DEBIT.equals(TransactionType.valueOf(type))
                    && transactionInputPort.getTotalAmount().compareTo(BigDecimal.valueOf(amount)) < 0);

        return isInvalid;
    }
}
