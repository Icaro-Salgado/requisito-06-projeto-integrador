package br.com.mercadolivre.projetointegrador.warehouse.exception.handler;

import br.com.mercadolivre.projetointegrador.warehouse.exception.StandardError;
import br.com.mercadolivre.projetointegrador.warehouse.exception.db.BatchAlreadyExists;
import br.com.mercadolivre.projetointegrador.warehouse.exception.db.ScheduledInboundOrderNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

@RestControllerAdvice
public class InboundOrderErrorHandler {

  @ExceptionHandler(value = BatchAlreadyExists.class)
  public ResponseEntity<StandardError> handleDuplicatedBatch(
      BatchAlreadyExists ex, HttpServletRequest request) {
    StandardError err = new StandardError();
    HttpStatus notModified = HttpStatus.CONFLICT;

    err.setTimestamp(Instant.now());
    err.setStatus(notModified.value());
    err.setError(err.getError());
    err.setMessage(ex.getMessage());
    err.setPath(request.getRequestURI());

    return ResponseEntity.status(notModified).body(err);
  }

  @ExceptionHandler(value = ScheduledInboundOrderNotFound.class)
  public ResponseEntity<StandardError> handleInvalidInboundOrder(
          ScheduledInboundOrderNotFound ex, HttpServletRequest request
  ) {
    StandardError err = new StandardError();
    HttpStatus notFound = HttpStatus.NOT_FOUND;

    err.setTimestamp(Instant.now());
    err.setStatus(notFound.value());
    err.setError(err.getError());
    err.setMessage(ex.getMessage());
    err.setPath(request.getRequestURI());

    return ResponseEntity.status(notFound).body(err);
  }
}
