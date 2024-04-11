package com.pragma.technologymicroservice.adapters.driving.http.dto.request;

import com.pragma.technologymicroservice.domain.model.Bootcamp;
import com.pragma.technologymicroservice.utils.constants.ExceptionConstants;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Date;

@AllArgsConstructor
@Getter
public class AddVersionRequest {


  @NotNull(message = ExceptionConstants.FIELD_INITIAL_DATE_NULL_MESSAGE)
  private LocalDate initialDate;

  @NotNull(message = ExceptionConstants.FIELD_FINAL_DATE_NULL_MESSAGE)
  private LocalDate finalDate;

  @NotNull(message = ExceptionConstants.FIELD_CUPOS_NULL_MESSAGE)
  private Integer cupos;

  @NotNull(message = ExceptionConstants.FIELD_BOOTCAMP_NULL_MESSAGE)
  private Bootcamp bootcamp;

}
