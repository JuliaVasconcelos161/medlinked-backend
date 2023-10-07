package com.medlinked.services;

import com.medlinked.entities.Endereco;
import com.medlinked.entities.Paciente;
import com.medlinked.entities.dtos.EnderecoDto;

public interface EnderecoService {
    Endereco createEndereco(EnderecoDto enderecoDto, Paciente paciente);

    Endereco getOneEndereco(Integer idPaciente);
    Endereco updateEndereco(EnderecoDto enderecoDto, Paciente paciente);
}
