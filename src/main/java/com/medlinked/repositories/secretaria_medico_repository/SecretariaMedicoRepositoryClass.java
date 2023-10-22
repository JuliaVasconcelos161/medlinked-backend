package com.medlinked.repositories.secretaria_medico_repository;

import com.medlinked.entities.Secretaria;
import com.medlinked.entities.dtos.MedicoCrmResponseDto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SecretariaMedicoRepositoryClass implements SecretariaMedicoRepository {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<MedicoCrmResponseDto> getAllMedicosSecretaria(Integer idSecretaria, int page, int pageSize) {
        List<Integer> idsMedicos = this.getAllIdsMedicosSecretaria(idSecretaria);
        return this.buildMedicosCrmResponseByIdsMedicos(idsMedicos, page, pageSize);
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

    private List<MedicoCrmResponseDto> buildMedicosCrmResponseByIdsMedicos(List<Integer> idsMedicos, int page, int pageSize) {
        StringBuilder consulta = new StringBuilder(" select new com.medlinked.entities.dtos.MedicoCrmResponseDto( ");
        consulta.append(" crm.medico.idMedico, crm.medico.pessoa.nome, crm.medico.pessoa.cpf, ");
        consulta.append(" crm.medico.pessoa.email, crm.medico.pessoa.celular, estado.uf, crm.numeroCrm) ");
        consulta.append(" from MedicoCRM crm ");
        consulta.append(" where crm.idMedico in (:IDSMEDICOS) ");
        consulta.append(" order by crm.medico.pessoa.nome ");
        var query = entityManager.createQuery(consulta.toString(), MedicoCrmResponseDto.class);
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
