package org.adex.utilities.mappers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.validation.FieldError;

public final class ObjectMappers {

	public static Map<String, String> mapErrorResponse(List<FieldError> errors) {

		Map<String, String> errorsMap = new HashMap<String, String>();
		
		for (FieldError e : errors)
			errorsMap.put(e.getField(), e.getDefaultMessage());

		return errorsMap;

	}

}
