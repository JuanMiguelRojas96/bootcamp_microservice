package com.pragma.technologymicroservice.domain.exception;

public class MaxCharException extends RuntimeException{
  public MaxCharException(String message){
    super(message);

  }
}
