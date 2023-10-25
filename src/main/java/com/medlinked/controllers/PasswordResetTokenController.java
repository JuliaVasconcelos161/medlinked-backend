package com.medlinked.controllers;

import com.medlinked.entities.dtos.PasswordResetDto;
import com.medlinked.exceptions.MedLinkedException;
import com.medlinked.services.password_reset_service.PasswordResetTokenService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/password")
public class PasswordResetTokenController {

    private final PasswordResetTokenService passwordResetTokenService;

    public PasswordResetTokenController(PasswordResetTokenService passwordResetTokenService) {
        this.passwordResetTokenService = passwordResetTokenService;
    }

    @PostMapping("/reset")
    @Operation(summary = "Envia email para usuário com link para resetar senha e retorna o token de resetar senha.")
    public ResponseEntity<String> resetPassword(HttpServletRequest request,
                                             @RequestParam("username") String username) {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(passwordResetTokenService
                    .resetPassword(request, username));
        }catch(MedLinkedException e){
            return ResponseEntity.status(e.getHttpStatus()).body(e.getMessage());
        }
    }

    @GetMapping("/change")
    @Operation(summary = "Verifica token de resetar senha enviado.")
    public ResponseEntity<String> showChangePasswordPage(@RequestParam("token") String token) {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(passwordResetTokenService
                    .validatePasswordResetToken(token));
        }catch(MedLinkedException e){
            return ResponseEntity.status(e.getHttpStatus()).body(e.getMessage());
        }
    }

    @PostMapping("/save")
    @Operation(summary = "Salva nova senha de usuário.")
    public ResponseEntity<Object> savePassword(@Valid @RequestBody PasswordResetDto passwordResetDto) {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(passwordResetTokenService.savePassword(passwordResetDto));
        }catch(MedLinkedException e){
            return ResponseEntity.status(e.getHttpStatus()).body(e.getMessage());
        }
    }
}
