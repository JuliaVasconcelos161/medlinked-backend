package com.medlinked.services.estado_service;

import com.medlinked.entities.Estado;

import java.util.List;

public interface EstadoService {
    List<Estado> getAllEstados();

    Estado getOneEstado(String uf);
}
