package com.pragma.technologymicroservice.configuration;

public class Constants {
  private Constants(){
    throw new IllegalStateException("utility class");
  }

  public static final String EMPTY_FIELD_EXCEPTION_MESSAGE = "The element indicated does not exist";
  public static final String MAX_CHAR_EXCEPTION_MESSAGE = "The Field indicated exceed the maximum number of characters";
  public static final String TECHNOLOGY_ALREADY_EXISTS_EXCEPTION_MESSAGE = "The technology you want to create already exists";

}
