package br.edu.ifmg.produto.resources.exceptions;

import br.edu.ifmg.produto.services.exceptions.DatabaseException;
import br.edu.ifmg.produto.services.exceptions.EmailException;
import br.edu.ifmg.produto.services.exceptions.ResourceNotFound;
import jakarta.servlet.http.HttpServletRequest;
import org.hibernate.dialect.Database;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ResourceExceptionListener {

    @ExceptionHandler(ResourceNotFound.class)
    public ResponseEntity<StandardError> resourceNotFound(ResourceNotFound ex, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND; // Erro 404

        StandardError error = new StandardError();
        error.setStatus(status.value());
        error.setMessage(ex.getMessage());
        error.setError("Resource not found");
        error.setTimestamp(Instant.now());
        error.setPath(request.getRequestURI());

        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<StandardError> databaseException(DatabaseException ex, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST; // Erro 400

        StandardError error = new StandardError();
        error.setStatus(status.value());
        error.setMessage(ex.getMessage());
        error.setError("Database exception");
        error.setTimestamp(Instant.now());
        error.setPath(request.getRequestURI());

        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationError> methodArgumentNotValidException (MethodArgumentNotValidException ex, HttpServletRequest request) {
        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY; // Erro 422

        ValidationError error = new ValidationError();
        error.setStatus(status.value());
        error.setMessage(ex.getMessage());
        error.setError("Validation exception");
        error.setTimestamp(Instant.now());
        error.setPath(request.getRequestURI());

        for (FieldError f : ex.getBindingResult().getFieldErrors()) {
            error.addFieldMessage(f.getField(), f.getDefaultMessage());
        }

        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(EmailException.class)
    public ResponseEntity<StandardError> emailException(EmailException ex, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST; // Erro 400

        StandardError error = new StandardError();
        error.setStatus(status.value());
        error.setMessage(ex.getMessage());
        error.setError("Email error");
        error.setTimestamp(Instant.now());
        error.setPath(request.getRequestURI());

        return ResponseEntity.status(status).body(error);
    }

}
