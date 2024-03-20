package com.pragma.technologymicroservice.configuration.exceptionhandler;


import com.pragma.technologymicroservice.adapters.driven.jpa.mysql.exception.*;
import com.pragma.technologymicroservice.configuration.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
@RequiredArgsConstructor
public class ControllerAdvisor {
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ExceptionResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
    BindingResult result = ex.getBindingResult();

    if (result.hasFieldErrors()) {
      FieldError error = result.getFieldError();

      if (error != null) {
        String errorField = error.getField();
        String errorMessage = getErrorMessage(error, errorField);

        return ResponseEntity.badRequest().body(new ExceptionResponse(
            errorMessage, HttpStatus.BAD_REQUEST.toString(), LocalDateTime.now()));
      }
    }
    return ResponseEntity.badRequest().build();
  }


  private String getErrorMessage(FieldError error, String errorField) {
    if (error == null) {
      return "Validation error";
    }

    String code = error.getCode();

    if (code != null && code.equals("NotBlank")) {
      return Constants.EMPTY_FIELD_EXCEPTION_MESSAGE;
    } else if (code != null && code.equals("Size") && "technologies".equals(errorField)) {
      return Constants.CAPACITY_MAX_TECHNOLOGIES_EXCEPTION_MESSAGE;
    } else if (code != null && code.equals("Size")) {
      return Constants.MAX_CHAR_EXCEPTION_MESSAGE;
    } else {
      return error.getDefaultMessage();
    }
  }


  @ExceptionHandler(TechnologyAlreadyExistsException.class)
  public ResponseEntity<ExceptionResponse> handleTechnologyAlreadyExistsException(TechnologyAlreadyExistsException exception){
    return ResponseEntity.badRequest().body(new ExceptionResponse(
        String.format(Constants.TECHNOLOGY_ALREADY_EXISTS_EXCEPTION_MESSAGE, exception.getMessage()),
        HttpStatus.BAD_REQUEST.toString(), LocalDateTime.now()));
  }

  @ExceptionHandler(CapacityAlreadyExistsException.class)
  public ResponseEntity<ExceptionResponse> handleCapacityAlreadyExistsException(CapacityAlreadyExistsException exception){
    return ResponseEntity.badRequest().body(new ExceptionResponse(
        String.format(Constants.CAPACITY_ALREADY_EXISTS_EXCEPTION_MESSAGE, exception.getMessage()),
        HttpStatus.BAD_REQUEST.toString(), LocalDateTime.now()));
  }

  @ExceptionHandler(CapacityMaxTechnologiesException.class)
  public ResponseEntity<ExceptionResponse> handleCapacityMaxTechnologiesException(CapacityMaxTechnologiesException exception){
    return ResponseEntity.badRequest().body(new ExceptionResponse(
        String.format(Constants.CAPACITY_MAX_TECHNOLOGIES_EXCEPTION_MESSAGE, exception.getMessage()),
        HttpStatus.BAD_REQUEST.toString(), LocalDateTime.now()));
  }

  @ExceptionHandler(RepeatTechInCapacityException.class)
  public ResponseEntity<ExceptionResponse> handleRepeatTechInCapacityException(RepeatTechInCapacityException exception){
    return ResponseEntity.badRequest().body(new ExceptionResponse(
        String.format(Constants.REPEAT_TECH_IN_CAPACITY_EXCEPTION_MESSAGE, exception.getMessage()),
        HttpStatus.BAD_REQUEST.toString(), LocalDateTime.now()));
  }

  @ExceptionHandler(NoDataFoundException.class)
  public ResponseEntity<ExceptionResponse> handleNoDataFoundException() {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ExceptionResponse(
        Constants.NO_DATA_FOUND_EXCEPTION_MESSAGE, HttpStatus.NOT_FOUND.toString(), LocalDateTime.now()));
  }


}
