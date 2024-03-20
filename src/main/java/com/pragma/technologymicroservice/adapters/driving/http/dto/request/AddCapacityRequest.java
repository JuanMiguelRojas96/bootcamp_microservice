package com.pragma.technologymicroservice.adapters.driving.http.dto.request;

import com.pragma.technologymicroservice.adapters.driving.http.util.DomainConstants;
import com.pragma.technologymicroservice.domain.model.Technology;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@AllArgsConstructor
@Getter
public class AddCapacityRequest {

  @NotBlank(message = DomainConstants.FIELD_NAME_NULL_MESSAGE)
  @Size(max = 50 , message = DomainConstants.MAX_CHAR_NAME_MESSAGE)
  private final String name;

  @NotBlank(message = DomainConstants.FIELD_DESCRIPTION_NULL_MESSAGE)
  @Size(max = 90 , message = DomainConstants.MAX_CHAR_DESCRIPTION_MESSAGE)
  private final String description;


  @Size(min = 3, max = 20, message = DomainConstants.CAPACITY_MAX_TECHNOLOGIES_EXCEPTION_MESSAGE)
  private final List<Technology> technologies;

}
