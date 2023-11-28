package com.medlinked.repositories.password_reset_repository;

import com.medlinked.entities.PasswordResetToken;
import com.medlinked.exceptions.NoObjectFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
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
        try{
            return query.getSingleResult();
        } catch (NoResultException e) {
            throw new NoObjectFoundException("Token");
        }
    }

    @Override
    public PasswordResetToken returnPasswordResetTokenByUsuario(String username) {
        StringBuilder consulta = new StringBuilder(" select passwordReset ");
        consulta.append(" from PasswordResetToken passwordReset ");
        consulta.append(" where passwordReset.usuario.username = :USERNAME ");
        var query = entityManager.createQuery(consulta.toString(), PasswordResetToken.class);
        query.setParameter("USERNAME", username);
        try{
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public PasswordResetToken updatePasswordResetToken(PasswordResetToken passwordResetToken) {
        entityManager.merge(passwordResetToken);
        entityManager.flush();
        return passwordResetToken;
    }
}
