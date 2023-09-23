package com.medlinked.repositories;

import com.medlinked.entities.Especialidade;
import com.medlinked.entities.Medico;
import com.medlinked.entities.PlanoSaude;
import com.medlinked.entities.dtos.MedicoResponseDto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MedicoRepositoryClass implements MedicoRepository {
    @PersistenceContext
    EntityManager entityManager;
    @Override
    @Transactional
    public Medico saveMedico(Medico medico) {
        entityManager.persist(medico);
        return medico;
    }

    @Override
    public List<Medico> getAllMedicos() {
        StringBuilder consulta = new StringBuilder("select medico from Medico medico");
        var query = entityManager.createQuery(consulta.toString(), Medico.class);
        return query.getResultList();
    }

    @Override
    public MedicoResponseDto getOneMedicoByCrm(Integer idMedico) {
        StringBuilder consulta = new StringBuilder(" select new com.medlinked.entities.dtos.MedicoResponseDto(");
        consulta.append(" crm.idMedico, estado.uf, crm.numeroCrm ");
        consulta.append(") from CRM crm ");
        consulta.append(" inner join crm.estado estado ");
        consulta.append(" where crm.idMedico = :ID ");
        var query = entityManager.createQuery(consulta.toString(), MedicoResponseDto.class);
        query.setParameter("ID", idMedico);
        return query.getSingleResult();
    }

    @Override
    public List<PlanoSaude> getPlanosSaudeMedicoByCrm(Integer idMedico) {
        StringBuilder consulta = new StringBuilder(" select plano from Medico medico ");
        consulta.append(" inner join medico.planosSaude plano ");
        consulta.append(" where medico.idMedico = :ID ");
        var query = entityManager.createQuery(consulta.toString(), PlanoSaude.class);
        query.setParameter("ID", idMedico);
        return query.getResultList();
    }

    @Override
    public List<Especialidade> getEspecialidadesMedicoByCrm(Integer idMedico) {
        StringBuilder consulta = new StringBuilder(" select especialidade from CRM crm ");
        consulta.append(" inner join crm.especialidades especialidade ");
        consulta.append(" where crm.idMedico = :ID ");
        var query = entityManager.createQuery(consulta.toString(), Especialidade.class);
        query.setParameter("ID", idMedico);
        return query.getResultList();
    }

    @Override
    public boolean existsMedicoByCpf(String cpf) {
        StringBuilder consulta = new StringBuilder(" select count(1) ");
        consulta.append(" from Medico medico ");
        consulta.append(" where medico.pessoa.cpf = :CPF ");
        var query = entityManager.createQuery(consulta.toString(), Long.class);
        query.setParameter("CPF", cpf);
        return query.getSingleResult() > 0;
    }


}
