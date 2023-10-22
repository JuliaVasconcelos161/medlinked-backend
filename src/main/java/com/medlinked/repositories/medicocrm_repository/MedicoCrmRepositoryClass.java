package com.medlinked.repositories.medicocrm_repository;

import com.medlinked.entities.Especialidade;
import com.medlinked.entities.MedicoCRM;
import com.medlinked.entities.dtos.MedicoCrmResponseDto;
import com.medlinked.exceptions.NoObjectFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MedicoCrmRepositoryClass implements MedicoCrmRepository {

    @PersistenceContext
    EntityManager entityManager;
    @Override
    public MedicoCRM saveCrm(MedicoCRM medicoCrm) {
        entityManager.persist(medicoCrm);
        return medicoCrm;
    }

    @Override
    public MedicoCRM getOneCrmByMedico(Integer idMedico) {
        MedicoCRM medicoCRM = entityManager.find(MedicoCRM.class, idMedico);
        if(medicoCRM == null)
            throw new NoObjectFoundException("Medico");
        return medicoCRM;
    }

    @Override
    public List<Especialidade> getEspecialidadesMedicoByCrm(Integer idMedico) {
        StringBuilder consulta = new StringBuilder(" select especialidade from MedicoCRM crm ");
        consulta.append(" inner join crm.especialidades especialidade ");
        consulta.append(" where crm.idMedico = :ID ");
        consulta.append(" order by especialidade.descricao ");
        var query = entityManager.createQuery(consulta.toString(), Especialidade.class);
        query.setParameter("ID", idMedico);
        return query.getResultList();
    }

    @Override
    public boolean existsMedicoByNumeroCrm(Integer numeroCrm, Integer idMedico) {
        StringBuilder consulta = new StringBuilder(" select count(1) ");
        consulta.append(" from MedicoCRM crm ");
        consulta.append(" where crm.numeroCrm = :NUMEROCRM ");
        if(idMedico != null)
            consulta.append(" and crm.medico.idMedico != :IDMEDICO ");
        var query = entityManager.createQuery(consulta.toString(), Long.class);
        if(idMedico != null)
            query.setParameter("IDMEDICO", idMedico);
        query.setParameter("NUMEROCRM", numeroCrm);
        return query.getSingleResult() > 0;
    }

    @Override
    public MedicoCRM updateMedicoCrm(MedicoCRM medicoCrm) {
        entityManager.merge(medicoCrm);
        entityManager.flush();
        return medicoCrm;
    }

    @Override
    public MedicoCrmResponseDto buildMedicoCrmResponseDto(Integer idMedico) {
        StringBuilder consulta = new StringBuilder(" select new com.medlinked.entities.dtos.MedicoCrmResponseDto( ");
        consulta.append(" crm.medico.idMedico, crm.medico.pessoa.nome, crm.medico.pessoa.cpf, ");
        consulta.append(" crm.medico.pessoa.email, crm.medico.pessoa.celular, estado.uf, crm.numeroCrm) ");
        consulta.append(" from MedicoCRM crm ");
        consulta.append(" inner join crm.estado estado ");
        consulta.append(" where crm.medico.idMedico = :IDMEDICO ");
        var query = entityManager.createQuery(consulta.toString(), MedicoCrmResponseDto.class);
        query.setParameter("IDMEDICO", idMedico);
        return query.getSingleResult();
    }

    @Override
    public void deleteMedicoCrm(MedicoCRM medicoCrm) {
        entityManager.remove(medicoCrm);
    }
}
