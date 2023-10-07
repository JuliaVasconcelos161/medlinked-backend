package com.medlinked.repositories;

import com.medlinked.entities.Paciente;

public interface PacienteRepository {
    Paciente savePaciente(Paciente paciente);

    Paciente getOnePaciente(Integer idPaciente);

    Paciente updatePaciente(Paciente paciente);
}