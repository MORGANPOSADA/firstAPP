package mx.edu.utez.firstapp.security.controllers;

import mx.edu.utez.firstapp.security.controllers.dtos.LoginDto;
import mx.edu.utez.firstapp.security.jwt.JwtProvider;
import mx.edu.utez.firstapp.utils.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api-market/auth")
@CrossOrigin(origins = {"*"})
public class AuthController {
    @Autowired
    private AuthenticationManager manager;
    @Autowired
    private JwtProvider provider;

    @PostMapping("/login")
    public ResponseEntity<CustomResponse<Map<String, Object>>> login(
            @Valid @RequestBody LoginDto loginDto
    ) {
        Authentication authentication = manager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getUsername(),
                        loginDto.getPassword()
                )
        );
        String token = provider.generateToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Map<String, Object> data = new HashMap<>();
        data.put("token", token);
        data.put("user", userDetails);
        return new ResponseEntity<>(
                new CustomResponse<>(data, false, 200, "OK"),
                HttpStatus.OK
        );
    }


}
