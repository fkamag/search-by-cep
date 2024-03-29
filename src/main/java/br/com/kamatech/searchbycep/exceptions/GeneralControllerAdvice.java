package br.com.kamatech.searchbycep.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GeneralControllerAdvice {

  @ExceptionHandler
  public ResponseEntity<String> finalSmallerThenInitException(FinalSmallerThenInitException e) {
    return ResponseEntity.status(HttpStatus.CONFLICT)
        .body("A faixa final deve ser maior que a faixa inicial");
  }

  @ExceptionHandler
  public ResponseEntity<String> rangeCepException(RangeCepException e) {
    return ResponseEntity.status(HttpStatus.CONFLICT)
        .body("A faixa de início ou fim do CEP já está sendo utilizada, "
            + "favor verificar as faixas utilizadas.");
  }

  @ExceptionHandler
  public ResponseEntity<String> genericException(RuntimeException e) {
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
  }

}
