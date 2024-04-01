package com.pragma.technologymicroservice.adapters.driving.http.dto.request;

import com.pragma.technologymicroservice.utils.constants.ExceptionConstants;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@AllArgsConstructor
@Getter
public class AddTechnologyRequest {

  @NotBlank(message = ExceptionConstants.FIELD_NAME_NULL_MESSAGE)
  @Size(max = 50, message = ExceptionConstants.MAX_CHAR_NAME_MESSAGE)
  private final String name;

  @NotBlank(message = ExceptionConstants.FIELD_DESCRIPTION_NULL_MESSAGE)
  @Size(max = 90, message = ExceptionConstants.MAX_CHAR_DESCRIPTION_MESSAGE)
  private final String description;

}
