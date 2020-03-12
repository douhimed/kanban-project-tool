package org.adex.utilities.exceptions.id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@AllArgsConstructor
@Getter
@Setter
public class ProjectIdExceptionResponse {
	
	private String projectIdentifier;

}
