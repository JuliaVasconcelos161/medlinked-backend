package com.medlinked.repositories.paciente_repository;

import com.medlinked.entities.Paciente;

import java.util.Set;

public interface PacienteRepository {
    Paciente savePaciente(Paciente paciente);

    Paciente getOnePaciente(Integer idPaciente);

    Paciente updatePaciente(Paciente paciente);

    Set<Paciente> getAllPacientes(String nomePaciente, String cpf);
}
