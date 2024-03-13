package com.pragma.technologymicroservice.configuration.exceptionhandler;


import com.pragma.technologymicroservice.adapters.driven.jpa.mysql.exception.TechnologyAlreadyExistsException;
import com.pragma.technologymicroservice.configuration.Constants;
import com.pragma.technologymicroservice.domain.exception.EmptyFieldException;
import com.pragma.technologymicroservice.domain.exception.MaxCharException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
@RequiredArgsConstructor
public class ControllerAdvisor {
  @ExceptionHandler(EmptyFieldException.class)
  public ResponseEntity<ExceptionResponse> handleEmptyFielException(EmptyFieldException exception){
    return ResponseEntity.badRequest().body(new ExceptionResponse(
        String.format(Constants.EMPTY_FIELD_EXCEPTION_MESSAGE, exception.getMessage()),
        HttpStatus.BAD_REQUEST.toString(), LocalDateTime.now()));
  }

  @ExceptionHandler(MaxCharException.class)
  public ResponseEntity<ExceptionResponse> handleMaxCharException(MaxCharException exception){
    return ResponseEntity.badRequest().body(new ExceptionResponse(
        String.format(Constants.MAX_CHAR_EXCEPTION_MESSAGE, exception.getMessage()),
        HttpStatus.BAD_REQUEST.toString(), LocalDateTime.now()));
  }

  @ExceptionHandler(TechnologyAlreadyExistsException.class)
  public ResponseEntity<ExceptionResponse> handleTechnologyAlreadyExistsException(TechnologyAlreadyExistsException exception){
    return ResponseEntity.badRequest().body(new ExceptionResponse(
        String.format(Constants.TECHNOLOGY_ALREADY_EXISTS_EXCEPTION_MESSAGE, exception.getMessage()),
        HttpStatus.BAD_REQUEST.toString(), LocalDateTime.now()));
  }

}
