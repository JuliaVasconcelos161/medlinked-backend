package com.medlinked.services.impl;

import com.medlinked.entities.Estado;
import com.medlinked.repositories.EstadoRepository;
import com.medlinked.services.EstadoService;
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
}
