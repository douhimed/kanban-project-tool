package org.adex.utilities.exceptions;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class InvalidLoginResponce {

    private String username;
    private String password;

    public InvalidLoginResponce() {
        this.username = "Invalid Username";
        this.password = "Invalid Password";
    }
}
