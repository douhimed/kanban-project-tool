package org.adex.services.impl;

import java.util.Map;
import java.util.Optional;

import org.adex.services.IErrorServices;
import org.adex.utilities.mappers.ObjectMappers;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

@Service
public class ErrorServices implements IErrorServices {

	@Override
	public Optional<ResponseEntity<Map<String, String>>> validate(BindingResult result) {

		return result.hasErrors()
				? Optional.of(new ResponseEntity<Map<String, String>>(
						ObjectMappers.mapErrorResponse(result.getFieldErrors()), HttpStatus.BAD_REQUEST))
				: Optional.ofNullable(null);

	}

}
