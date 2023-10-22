package com.medlinked.repositories.endereco_repository;

import com.medlinked.entities.Endereco;

public interface EnderecoRepository {
    Endereco saveEndereco(Endereco endereco);

    Endereco getOneEndereco(Integer idPaciente);

    Endereco updateEndereco(Endereco endereco);

    void deleteEndereco(Endereco endereco);
}
