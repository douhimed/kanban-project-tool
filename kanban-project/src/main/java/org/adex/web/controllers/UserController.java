package org.adex.web.controllers;

import org.adex.security.JwtTokenProvider;
import org.adex.services.IErrorServices;
import org.adex.services.IUserServices;
import org.adex.utilities.validators.UserValidator;
import org.adex.web.models.User;
import org.adex.web.payload.JWTLoginSuccessResponse;
import org.adex.web.payload.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Map;
import java.util.Optional;

import static org.adex.security.SecurityConstants.JWT_PREFIX;

@RestController
@RequestMapping("/api/users")
public class UserController {


    @Autowired
    private IErrorServices errorServices;

    @Autowired
    private IUserServices userServices;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private AuthenticationManager authenticationManager;


    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@Valid @RequestBody LoginRequest loginRequest, BindingResult result){

        Optional<ResponseEntity<Map<String, String>>> errorsOptional = this.errorServices.validate(result);
        if(errorsOptional.isPresent())
            return errorsOptional.get();

        Authentication authentication = this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = JWT_PREFIX + this.jwtTokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JWTLoginSuccessResponse(true, token));
    }


    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody User user, BindingResult result){

        userValidator.validate(user, result);
        Optional<ResponseEntity<Map<String, String>>> errorsOptional = this.errorServices.validate(result);
        return errorsOptional.isPresent() ? errorsOptional.get() : new ResponseEntity<User>(this.userServices.saveUser(user), HttpStatus.CREATED);


    }

}
