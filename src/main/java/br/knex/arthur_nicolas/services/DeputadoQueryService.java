package br.knex.arthur_nicolas.services;

import br.knex.arthur_nicolas.dto.response.DeputadoResponseDTO;
import br.knex.arthur_nicolas.mappers.DeputadoMapper;
import br.knex.arthur_nicolas.repositories.DeputadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service
public class DeputadoQueryService {

    @Autowired
    private DeputadoRepository deputadoRepository;

    // Retorna lista de Deputado filtrada por: "UF" (PB, SP, RJ, RN, RS, MG, AM...).
    public List<DeputadoResponseDTO> listByUf(String uf) {
        List<DeputadoResponseDTO> allDeputadoData = deputadoRepository.getAllDeputatoResponse();

        List<DeputadoResponseDTO> deputadoListByUf = new ArrayList<>();

        for(DeputadoResponseDTO deputado : allDeputadoData) {
            if(deputado.getUf().equals(uf)) {
                deputadoListByUf.add(deputado);
            }
        }

        return deputadoListByUf;
    }
}