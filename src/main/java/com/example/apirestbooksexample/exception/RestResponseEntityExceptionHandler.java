package com.example.apirestbooksexample.exception;

import com.example.apirestbooksexample.exception.dto.ErrorMessageDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorMessageDTO> createBookNotFoundException(CreateBookException exception) {
        ErrorMessageDTO messageDTO = new ErrorMessageDTO(
                HttpStatus.NOT_FOUND,
                exception.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(messageDTO);

    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorMessageDTO> updateBookException(UpdateBookException exception) {
        ErrorMessageDTO messageDTO = new ErrorMessageDTO(
                HttpStatus.BAD_REQUEST,
                exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(messageDTO);
    }

}
