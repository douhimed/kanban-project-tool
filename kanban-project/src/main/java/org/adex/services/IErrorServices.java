package org.adex.services;

import java.util.Map;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

public interface IErrorServices {
	
	Optional<ResponseEntity<Map<String, String>>> validate(BindingResult result);

}
