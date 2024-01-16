package ua.lemoncat.zom100tasks.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import ua.lemoncat.zom100tasks.dto.ErrorMessage;
import ua.lemoncat.zom100tasks.exception.exceptions.TaskAlreadyHaveStatusException;
import ua.lemoncat.zom100tasks.exception.exceptions.TaskNotFoundException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({HttpMessageNotReadableException.class})
    public final ErrorMessage handleKeycloakException(HttpMessageNotReadableException ex) {
        return new ErrorMessage("default-error");
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({TaskAlreadyHaveStatusException.class})
    public final ErrorMessage handleKeycloakException(TaskAlreadyHaveStatusException ex) {
        return new ErrorMessage(ex.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({TaskNotFoundException.class})
    public final ErrorMessage handleKeycloakException(TaskNotFoundException ex) {
        return new ErrorMessage(ex.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HandlerMethodValidationException.class)
    public Map<String, String> handleValidationExceptions(HandlerMethodValidationException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getAllValidationResults().forEach((error) -> {
            String fieldName = error.getMethodParameter().getParameterName();
            String errorMessage = error.getResolvableErrors().get(0).getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
