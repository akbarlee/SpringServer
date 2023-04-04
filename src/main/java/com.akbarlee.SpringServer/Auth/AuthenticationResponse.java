package com.akbarlee.SpringServer.Auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Data
@Builder
@Slf4j
@AllArgsConstructor
@NoArgsConstructor

public class AuthenticationResponse {

    private String token;

}
