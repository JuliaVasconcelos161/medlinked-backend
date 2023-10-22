package com.medlinked.services.tipoagendamento_service;

import com.medlinked.enums.TipoAgendamento;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class TipoAgendamentoServiceImpl implements TipoAgendamentoService {
    @Override
    public List<TipoAgendamento> getAllTipoAgendamento() {
        List<TipoAgendamento> tiposAgendamento = new ArrayList<>();
        tiposAgendamento.add(TipoAgendamento.REGULAR);
        tiposAgendamento.add(TipoAgendamento.RETORNO);
        return tiposAgendamento;
    }
}
