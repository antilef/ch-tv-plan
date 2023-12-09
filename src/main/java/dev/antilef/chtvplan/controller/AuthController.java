package dev.antilef.chtvplan.controller;

import dev.antilef.chtvplan.dto.LoginResponse;
import dev.antilef.chtvplan.dto.LoginUserData;
import dev.antilef.chtvplan.dto.RegisterUserData;
import dev.antilef.chtvplan.entity.User;
import dev.antilef.chtvplan.service.AuthServiceDefault;
import dev.antilef.chtvplan.service.JWTService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("api/v1/auth")
@RestController
public class AuthController {

    private final JWTService jwtService;

    private final AuthServiceDefault authenticationService;

    public AuthController(JWTService jwtService, AuthServiceDefault authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/signup")
    public ResponseEntity<User> register(@RequestBody RegisterUserData registerUserDto) {
        User registeredUser = authenticationService.signup(registerUserDto);

        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserData loginUserDto) {
        User authenticatedUser = authenticationService.authenticate(loginUserDto);

        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(jwtToken);
        loginResponse.setExpiresIn(jwtService.getExpirationTime());

        return ResponseEntity.ok(loginResponse);
    }

}
