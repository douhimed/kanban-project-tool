package org.adex.web.payload;

import lombok.*;

@Data
@AllArgsConstructor
@Getter
@Setter
@ToString
public class JWTLoginSuccessResponse {

    private boolean success;
    private String token;

}
