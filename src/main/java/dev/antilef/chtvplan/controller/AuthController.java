package dev.antilef.chtvplan.controller;

import dev.antilef.chtvplan.dto.AuthRequest;
import dev.antilef.chtvplan.dto.AuthResponse;
import dev.antilef.chtvplan.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("api/v1")
public class AuthController {

    @Autowired
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public AuthResponse authEntryPoint(@RequestBody AuthRequest request){
        try {
            return authService.auth();
        }catch (Exception e){
            return new AuthResponse();
        }
    }


}
