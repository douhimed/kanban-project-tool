package org.adex.utilities.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@AllArgsConstructor
@Getter
@Setter
public class TaskNotFoundExceptionResponce {
	
	private String taskNotFoundException;

}
