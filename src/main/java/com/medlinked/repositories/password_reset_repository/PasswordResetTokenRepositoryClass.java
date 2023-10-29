package com.medlinked.repositories.password_reset_repository;

import com.medlinked.entities.PasswordResetToken;
import com.medlinked.exceptions.NoObjectFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class PasswordResetTokenRepositoryClass implements PasswordResetTokenRepository {
    @PersistenceContext
    EntityManager entityManager;


    @Override
    public PasswordResetToken savePasswordResetToken(PasswordResetToken passwordResetToken) {
        entityManager.persist(passwordResetToken);
        return passwordResetToken;
    }

    @Override
    public PasswordResetToken getPasswordResetTokenByToken(String token) {
        StringBuilder consulta = new StringBuilder(" select passwordReset ");
        consulta.append(" from PasswordResetToken passwordReset ");
        consulta.append(" where passwordReset.token = :TOKEN ");
        var query = entityManager.createQuery(consulta.toString(), PasswordResetToken.class);
        query.setParameter("TOKEN", token);
        PasswordResetToken passwordResetToken =  query.getSingleResult();
        if(passwordResetToken == null)
            throw new NoObjectFoundException("Token");
        return passwordResetToken;
    }

    @Override
    public PasswordResetToken returnPasswordResetTokenByUsuario(String username) {
        StringBuilder consulta = new StringBuilder(" select passwordReset ");
        consulta.append(" from PasswordResetToken passwordReset ");
        consulta.append(" where passwordReset.usuario.username = :USERNAME ");
        var query = entityManager.createQuery(consulta.toString(), PasswordResetToken.class);
        query.setParameter("USERNAME", username);
        PasswordResetToken passwordResetToken =  query.getSingleResult();
        if(passwordResetToken == null)
            throw new NoObjectFoundException("Username");
        return passwordResetToken;
    }

    @Override
    public PasswordResetToken updatePasswordResetToken(PasswordResetToken passwordResetToken) {
        entityManager.merge(passwordResetToken);
        entityManager.flush();
        return passwordResetToken;
    }
}
