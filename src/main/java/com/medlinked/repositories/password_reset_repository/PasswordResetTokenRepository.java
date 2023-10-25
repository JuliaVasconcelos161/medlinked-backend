package com.medlinked.repositories.password_reset_repository;

import com.medlinked.entities.PasswordResetToken;
import com.medlinked.entities.Usuario;

public interface PasswordResetTokenRepository {
    PasswordResetToken savePasswordResetToken(PasswordResetToken passwordResetToken);

    PasswordResetToken getPasswordResetTokenByToken(String token);

    PasswordResetToken returnPasswordResetTokenByUsuario(String usuario);

    PasswordResetToken updatePasswordResetToken(PasswordResetToken passwordResetToken);
}
