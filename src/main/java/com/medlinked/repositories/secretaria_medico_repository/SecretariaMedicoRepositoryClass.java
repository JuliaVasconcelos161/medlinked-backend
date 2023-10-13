package com.medlinked.repositories.secretaria_medico_repository;

import com.medlinked.entities.MedicoCRM;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SecretariaMedicoRepositoryClass implements SecretariaMedicoRepository {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<MedicoCRM> getAllMedicosSecretaria(Integer idSecretaria, int page, int pageSize) {
        List<Integer> idsMedicos = this.getAllIdsMedicosSecretaria(idSecretaria);
        return this.getAllMedicoCRMsByIdsMedicos(idsMedicos, page, pageSize);
    }
    private List<MedicoCRM> getAllMedicoCRMsByIdsMedicos(List<Integer> idsMedicos, int page, int pageSize) {
        StringBuilder consulta = new StringBuilder(" select medicoCrm ");
        consulta.append(" from MedicoCRM medicoCrm ");
        consulta.append(" where medicoCrm.idMedico in (:IDSMEDICOS) ");
        consulta.append(" order by medicoCrm.medico.pessoa.nome ");
        var query = entityManager.createQuery(consulta.toString(), MedicoCRM.class);
        query.setParameter("IDSMEDICOS", idsMedicos);
        query.setFirstResult(page * pageSize);
        query.setMaxResults(pageSize);
        return query.getResultList();
    }

    private List<Integer> getAllIdsMedicosSecretaria(Integer idSecretaria) {
        StringBuilder consulta = new StringBuilder(" select medico.idMedico ");
        consulta.append(" from Secretaria secretaria ");
        consulta.append(" inner join secretaria.medicos medico ");
        consulta.append(" where secretaria.idSecretaria = :IDSECRETARIA ");
        var query = entityManager.createQuery(consulta.toString(), Integer.class);
        query.setParameter("IDSECRETARIA", idSecretaria);
        return query.getResultList();
    }

    @Override
    public Long countMedicosSecretaria(Integer idSecretaria) {
        StringBuilder consulta = new StringBuilder(" select count(1) ");
        consulta.append(" from Secretaria secretaria ");
        consulta.append(" inner join secretaria.medicos medico");
        consulta.append(" where secretaria.idSecretaria = :IDSECRETARIA ");
        var query = entityManager.createQuery(consulta.toString(), Long.class);
        query.setParameter("IDSECRETARIA", idSecretaria);
        return query.getSingleResult();
    }


}
