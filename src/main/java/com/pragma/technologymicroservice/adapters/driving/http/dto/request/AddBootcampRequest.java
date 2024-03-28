package com.pragma.technologymicroservice.adapters.driving.http.dto.request;

import com.pragma.technologymicroservice.utils.constants.DomainConstants;
import com.pragma.technologymicroservice.domain.model.Capacity;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@AllArgsConstructor
@Getter
public class AddBootcampRequest {

  @NotBlank(message = DomainConstants.FIELD_NAME_NULL_MESSAGE)
  @Size(max = 50, message = DomainConstants.MAX_CHAR_NAME_MESSAGE)
  private final String name;

  @NotBlank(message = DomainConstants.FIELD_DESCRIPTION_NULL_MESSAGE)
  @Size(max = 90, message = DomainConstants.MAX_CHAR_DESCRIPTION_MESSAGE)
  private final String description;

  @NotNull(message = DomainConstants.FIELD_CAPACITIES_NULL_MESSAGE)
  @Size(min = 1, max = 4, message = DomainConstants.BOOTCAMP_MIN_OR_MAX_CAPACITIES_EXCEPTION_MESSAGE)
  private final List<Capacity> capacities;
}
