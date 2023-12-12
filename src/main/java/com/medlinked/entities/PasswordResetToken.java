package com.medlinked.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "TB_PASSWORD_RESET")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PasswordResetToken {

    private static final int EXPIRATION = 1000 * 60 * 15;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPasswordResetToken;

    private String token;

    @OneToOne
    @JoinColumn(nullable = false, name = "id_usuario")
    private Usuario usuario;

    private Date expiryDate;

    public PasswordResetToken(Usuario usuario, String token) {
        this.usuario = usuario;
        this.token = token;
        this.expiryDate = new Date(System.currentTimeMillis() + EXPIRATION);
    }
}
