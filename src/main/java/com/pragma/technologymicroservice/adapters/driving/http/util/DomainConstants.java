package com.pragma.technologymicroservice.adapters.driving.http.util;

public class DomainConstants {
  private DomainConstants() {
    throw new IllegalStateException("Utility class");
  }

  public enum Field {
    NAME,
    DESCRIPTION,

  }

  public static final String FIELD_NAME_NULL_MESSAGE = "Field 'name' cannot be null";
  public static final String FIELD_DESCRIPTION_NULL_MESSAGE = "Field 'description' cannot be null";
  public static final String MAX_CHAR_NAME_MESSAGE = "Field 'name' cannot exceed the maximum number of characters (50)";
  public static final String MAX_CHAR_DESCRIPTION_MESSAGE = "Field 'description' cannot exceed the maximum number of characters (90)";

  }