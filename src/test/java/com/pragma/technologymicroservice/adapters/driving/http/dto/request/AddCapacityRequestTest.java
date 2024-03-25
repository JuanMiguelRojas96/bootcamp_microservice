package com.pragma.technologymicroservice.adapters.driving.http.dto.request;

import com.pragma.technologymicroservice.adapters.driving.http.util.DomainConstants;
import com.pragma.technologymicroservice.domain.model.Technology;
import org.junit.jupiter.api.Test;
import org.w3c.dom.stylesheets.LinkStyle;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.Null;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class AddCapacityRequestTest {

  private final ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
  private final Validator validator = validatorFactory.getValidator();

  @Test
  void testConstructorAndGettersCapacity(){
    String name = "Capacidad";
    String description = "Description Capacity";

    List<Technology> technologies = new ArrayList<>();

    technologies.add(new Technology(2L,"Java","Programing"));
    technologies.add(new Technology(3L, "Python","Programing"));



    AddCapacityRequest request = new AddCapacityRequest(name,description,technologies);

    assertEquals(name,request.getName());
    assertEquals(description,request.getDescription());
    assertEquals(technologies,request.getTechnologies());

  }


  @Test
  void testValidationNotBlank(){
    AddCapacityRequest request = new AddCapacityRequest("", "", null);
    Set<ConstraintViolation<AddCapacityRequest>> violations = validator.validate(request);

    assertEquals(3, violations.size());

    for (ConstraintViolation<AddCapacityRequest> violation : violations) {
      assertTrue(violation.getMessage().contains(DomainConstants.FIELD_NAME_NULL_MESSAGE) ||
          violation.getMessage().contains(DomainConstants.FIELD_DESCRIPTION_NULL_MESSAGE) || violation.getMessage().contains(DomainConstants.FIELD_TECHNOLOGIES_NULL_MESSAGE));
    }
  }

  @Test
  void testValidationMaxSize() {
    String longName = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.";
    String longDescription = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.";

    AddCapacityRequest request = new AddCapacityRequest(longName, longDescription,new ArrayList<>());

    Set<ConstraintViolation<AddCapacityRequest>> violations = validator.validate(request);

    assertEquals(3, violations.size());

    for (ConstraintViolation<AddCapacityRequest> violation : violations) {
      assertTrue(violation.getMessage().contains(DomainConstants.MAX_CHAR_NAME_MESSAGE) ||
          violation.getMessage().contains(DomainConstants.MAX_CHAR_DESCRIPTION_MESSAGE) || violation.getMessage().contains(DomainConstants.CAPACITY_MIN_OR_MAX_TECHNOLOGIES_EXCEPTION_MESSAGE));
    }
  }
  


}