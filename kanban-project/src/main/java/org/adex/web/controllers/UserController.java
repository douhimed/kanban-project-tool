package org.adex.web.controllers;

import org.adex.services.IErrorServices;
import org.adex.services.IUserServices;
import org.adex.utilities.validators.UserValidator;
import org.adex.web.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {


    @Autowired
    private IErrorServices errorServices;

    @Autowired
    private IUserServices userServices;

    @Autowired
    private UserValidator userValidator;

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody User user, BindingResult result){

        userValidator.validate(user, result);
        Optional<ResponseEntity<Map<String, String>>> errorsOptional = this.errorServices.validate(result);
        return errorsOptional.isPresent() ? errorsOptional.get() : new ResponseEntity<User>(this.userServices.saveUser(user), HttpStatus.CREATED);


    }

}
