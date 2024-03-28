package com.pragma.technologymicroservice.adapters.driving.http.dto.request;

import com.pragma.technologymicroservice.utils.constants.DomainConstants;
import com.pragma.technologymicroservice.domain.model.Capacity;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class AddBootcampRequestTest {

  private final ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
  private final Validator validator = validatorFactory.getValidator();


  @Test
  void testConstructorAndGettersBootcamp(){

    String name = "Capacidad";
    String description = "Description Capacity";
    List<Capacity> capacities = new ArrayList<>();

    AddBootcampRequest request = new AddBootcampRequest(name,description, capacities);

    assertEquals(name, request.getName());
    assertEquals(description,request.getDescription());
    assertEquals(capacities, request.getCapacities());
  }


  @Test
  void testValidationNotBlank(){
    AddBootcampRequest request = new AddBootcampRequest("", "", null);
    Set<ConstraintViolation<AddBootcampRequest>> violations = validator.validate(request);

    assertEquals(3, violations.size());

    for (ConstraintViolation<AddBootcampRequest> violation : violations) {
      assertTrue(violation.getMessage().contains(DomainConstants.FIELD_NAME_NULL_MESSAGE) ||
          violation.getMessage().contains(DomainConstants.FIELD_DESCRIPTION_NULL_MESSAGE) || violation.getMessage().contains(DomainConstants.FIELD_CAPACITIES_NULL_MESSAGE));
    }
  }

  @Test
  void testValidationMaxSize(){
    String longName = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.";
    String longDescription = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.";

    AddBootcampRequest request = new AddBootcampRequest(longName,longDescription,new ArrayList<>());

    Set<ConstraintViolation<AddBootcampRequest>> violations = validator.validate(request);

    assertEquals(3,violations.size());

    for (ConstraintViolation<AddBootcampRequest> violation : violations) {
      assertTrue(violation.getMessage().contains(DomainConstants.MAX_CHAR_NAME_MESSAGE) ||
          violation.getMessage().contains(DomainConstants.MAX_CHAR_DESCRIPTION_MESSAGE) || violation.getMessage().contains(DomainConstants.BOOTCAMP_MIN_OR_MAX_CAPACITIES_EXCEPTION_MESSAGE));
    }
  }

}