package com.medlinked.services.estado_service;

import com.medlinked.entities.Estado;
import com.medlinked.repositories.estado_repository.EstadoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstadoServiceImpl implements EstadoService {

    private final EstadoRepository estadoRepository;

    public EstadoServiceImpl(EstadoRepository estadoRepository) {
        this.estadoRepository = estadoRepository;
    }

    @Override
    public List<Estado> getAllEstados() {
        return estadoRepository.getAllEstados();
    }

    @Override
    public Estado getOneEstado(String uf) {
        return estadoRepository.getOneEstado(uf);
    }
}
