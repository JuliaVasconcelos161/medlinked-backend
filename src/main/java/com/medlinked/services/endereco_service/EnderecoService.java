package com.medlinked.services.endereco_service;

import com.medlinked.entities.Endereco;
import com.medlinked.entities.Paciente;
import com.medlinked.entities.dtos.EnderecoDto;

public interface EnderecoService {
    Endereco createEndereco(EnderecoDto enderecoDto, Paciente paciente);

    Endereco getOneEndereco(Integer idPaciente);
    Endereco updateEndereco(EnderecoDto enderecoDto, Paciente paciente);

    void deleteEndereco(Integer idPaciente);
}
