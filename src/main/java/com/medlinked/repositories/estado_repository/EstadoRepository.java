package com.medlinked.repositories.estado_repository;

import com.medlinked.entities.Estado;

import java.util.List;

public interface EstadoRepository {
    List<Estado> getAllEstados();

    Estado getOneEstado(String uf);
}
