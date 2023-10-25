package com.medlinked.repositories.password_reset_repository;

import com.medlinked.entities.PasswordResetToken;

public interface PasswordResetTokenRepository {
    PasswordResetToken savePasswordResetToken(PasswordResetToken passwordResetToken);

    PasswordResetToken getPasswordResetTokenByToken(String token);
}
