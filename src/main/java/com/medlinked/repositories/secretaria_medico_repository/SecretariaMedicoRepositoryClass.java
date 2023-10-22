package com.medlinked.repositories.secretaria_medico_repository;

import com.medlinked.entities.Secretaria;
import com.medlinked.entities.dtos.MedicoCrmResponseDto;
import com.medlinked.repositories.medicocrm_repository.MedicoCrmRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SecretariaMedicoRepositoryClass implements SecretariaMedicoRepository {
    @PersistenceContext
    EntityManager entityManager;

    private final MedicoCrmRepository medicoCrmRepository;

    public SecretariaMedicoRepositoryClass(MedicoCrmRepository medicoCrmRepository) {
        this.medicoCrmRepository = medicoCrmRepository;
    }

    @Override
    public List<MedicoCrmResponseDto> getAllMedicosSecretaria(Integer idSecretaria, int page, int pageSize) {
        List<Integer> idsMedicos = this.getAllIdsMedicosSecretaria(idSecretaria);
        return medicoCrmRepository.buildMedicosCrmResponseByIdsMedicos(idsMedicos, page, pageSize);
    }

    @Override
    public List<Secretaria> getAllSecretariasMedico(Integer idMedico) {
        StringBuilder consulta = new StringBuilder(" select secretaria ");
        consulta.append(" from Secretaria secretaria ");
        consulta.append(" inner join secretaria.medicos medico ");
        consulta.append(" where medico.idMedico = :IDMEDICO ");
        var query = entityManager.createQuery(consulta.toString(), Secretaria.class);
        query.setParameter("IDMEDICO", idMedico);
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
