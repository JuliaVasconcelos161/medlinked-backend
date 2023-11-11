package com.medlinked.repositories.usuario_repository;

import com.medlinked.entities.Usuario;
import com.medlinked.exceptions.NoObjectFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class UsuarioRepositoryClass implements UsuarioRepository {

    @PersistenceContext
    EntityManager entityManager;


    @Override
    public boolean existsByUsername(String username) {
        StringBuilder consulta = new StringBuilder(" select count(1) ");
        consulta.append(" from Usuario usuario ");
        consulta.append(" where usuario.username = :USERNAME ");
        var query = entityManager.createQuery(consulta.toString(), Long.class);
        query.setParameter("USERNAME", username);
        return query.getSingleResult() > 0;
    }

    @Override
    public void saveUsuario(Usuario usuario) {
        entityManager.persist(usuario);
    }

    @Override
    public Usuario returnUsuarioByUsername(String username) {
        StringBuilder consulta = new StringBuilder(" select usuario ");
        consulta.append(" from Usuario usuario ");
        consulta.append(" where usuario.username = :USERNAME ");
        var query = entityManager.createQuery(consulta.toString(), Usuario.class);
        query.setParameter("USERNAME", username);
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            throw new NoObjectFoundException("Usuário");
        }
    }

    @Override
    public Usuario getOneUsuario(Integer idUsuario) {
        Usuario usuario = entityManager.find(Usuario.class, idUsuario);
        if(usuario == null)
            throw new NoObjectFoundException("Usuário");
        return usuario;
    }

    @Override
    public Usuario updateUsuario(Usuario usuario) {
        entityManager.merge(usuario);
        entityManager.flush();
        return usuario;
    }

    @Override
    public void deleteUsuario(Usuario usuario) {
        entityManager.remove(usuario);
    }
}
