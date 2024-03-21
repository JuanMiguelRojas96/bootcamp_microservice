package com.pragma.technologymicroservice.adapters.driving.http.dto.request;

import com.pragma.technologymicroservice.adapters.driving.http.util.DomainConstants;
import org.junit.jupiter.api.Test;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

 class AddTechnologyRequestTest {

  private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
  private final Validator validator = factory.getValidator();

  @Test
  void testConstructorAndGetters() {
    String name = "Java";
    String description = "Programming language";

    AddTechnologyRequest request = new AddTechnologyRequest(name, description);

    assertEquals(name, request.getName());
    assertEquals(description, request.getDescription());
  }

  @Test
  void testValidationNotBlank() {
    AddTechnologyRequest request = new AddTechnologyRequest("", "");

    Set<ConstraintViolation<AddTechnologyRequest>> violations = validator.validate(request);

    assertEquals(2, violations.size());

    for (ConstraintViolation<AddTechnologyRequest> violation : violations) {
      assertTrue(violation.getMessage().contains(DomainConstants.FIELD_NAME_NULL_MESSAGE) ||
          violation.getMessage().contains(DomainConstants.FIELD_DESCRIPTION_NULL_MESSAGE));
    }
  }

  @Test
  void testValidationMaxSize() {
    String longName = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.";
    String longDescription = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.";

    AddTechnologyRequest request = new AddTechnologyRequest(longName, longDescription);

    Set<ConstraintViolation<AddTechnologyRequest>> violations = validator.validate(request);

    assertEquals(2, violations.size());

    for (ConstraintViolation<AddTechnologyRequest> violation : violations) {
      assertTrue(violation.getMessage().contains(DomainConstants.MAX_CHAR_NAME_MESSAGE) ||
          violation.getMessage().contains(DomainConstants.MAX_CHAR_DESCRIPTION_MESSAGE));
    }
  }

}