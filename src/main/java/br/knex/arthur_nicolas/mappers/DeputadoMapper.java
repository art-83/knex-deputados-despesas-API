package br.knex.arthur_nicolas.mappers;

import br.knex.arthur_nicolas.dto.DeputadoDTO;
import br.knex.arthur_nicolas.dto.DespesaDTO;
import br.knex.arthur_nicolas.dto.response.DeputadoResponseDTO;
import br.knex.arthur_nicolas.models.Deputado;
import br.knex.arthur_nicolas.models.Despesa;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DeputadoMapper {

    @Autowired
    private DespesaMapper despesaMapper;

    public DeputadoDTO jsonToDto(JsonNode deputadoData, List<DespesaDTO> despesaDTOList) {
        DeputadoDTO deputado = new DeputadoDTO();

        deputado.setId(deputadoData.path("id").asText());
        deputado.setNome(deputadoData.path("nomeCivil").asText());
        deputado.setUf(deputadoData.path("ufNascimento").asText());
        deputado.setCpf(deputadoData.path("cpf").asText());
        deputado.setPartido(deputadoData.path("ultimoStatus").path("siglaPartido").asText());
        deputado.setDespesas(despesaDTOList);

        return deputado;
    }

    public Deputado dtoToEntity(DeputadoDTO deputadoData) {
        Deputado deputado = new Deputado();

        deputado.setId(deputadoData.getId());
        deputado.setNome(deputadoData.getNome());
        deputado.setUf(deputadoData.getUf());
        deputado.setCpf(deputadoData.getCpf());
        deputado.setPartido(deputadoData.getPartido());
        List<Despesa> despesas = despesaMapper.toEntityList(deputadoData.getDespesas(), deputado);
        deputado.setDespesas(despesas);

        return deputado;
    }
}