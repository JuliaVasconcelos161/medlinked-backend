package com.medlinked.repositories.paciente_repository;

import com.medlinked.entities.Paciente;

import java.util.List;

public interface PacienteRepository {
    Paciente savePaciente(Paciente paciente);

    Paciente getOnePaciente(Integer idPaciente);

    Paciente updatePaciente(Paciente paciente);

    List<Paciente> getAllPacientes(String nomePaciente, String cpf, Integer page, Integer pageSize);

    Long countPacientes();

    void deletePaciente(Paciente paciente);
}
