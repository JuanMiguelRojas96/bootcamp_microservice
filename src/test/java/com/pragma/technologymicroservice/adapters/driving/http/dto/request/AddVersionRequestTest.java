package com.pragma.technologymicroservice.adapters.driving.http.dto.request;

import com.pragma.technologymicroservice.domain.model.Bootcamp;
import com.pragma.technologymicroservice.utils.constants.ExceptionConstants;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class AddVersionRequestTest {

  private final ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
  private final Validator validator = validatorFactory.getValidator();

  @Test
  void testConstructorAndGettersVersion() {

    LocalDate initialDate = LocalDate.now();
    LocalDate finalDate = LocalDate.now();
    Integer cupos = 1;
    Bootcamp bootcamp = new Bootcamp(1L,"name","description",new ArrayList<>());

    AddVersionRequest request = new AddVersionRequest(initialDate,finalDate,cupos,bootcamp);

    assertEquals(initialDate, request.getInitialDate());
    assertEquals(finalDate, request.getFinalDate());
    assertEquals(cupos, request.getCupos());
    assertEquals(bootcamp, request.getBootcamp());

  }

  @Test
  void testValidationNotNull() {

    AddVersionRequest request = new AddVersionRequest(null ,null,null,null);

    Set<ConstraintViolation<AddVersionRequest>> violations = validator.validate(request);

    assertEquals(4, violations.size());

  }


}