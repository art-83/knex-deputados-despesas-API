package br.knex.arthur_nicolas.mappers;

import br.knex.arthur_nicolas.dto.DespesaDTO;
import br.knex.arthur_nicolas.models.Deputado;
import br.knex.arthur_nicolas.models.Despesa;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DespesaMapper {
    public Despesa toEntity(DespesaDTO despesaData, Deputado deputado) {
        Despesa despesa = new Despesa();
        despesa.setDataEmissao(despesaData.getDataEmissao());
        despesa.setFornecedor(despesaData.getFornecedor());
        despesa.setValorLiquido(despesaData.getValorLiquido());
        despesa.setUrlDocumento(despesaData.getUrlDocumento());
        despesa.setDeputado(deputado);
        return despesa;
    }

    public List<Despesa> toEntityList(List<DespesaDTO> despesaDTODataList, Deputado deputado) {
        List<Despesa> despesasList = new ArrayList<>();
        for(DespesaDTO despesaData : despesaDTODataList) {
            despesasList.add(toEntity(despesaData, deputado));
        }
        return despesasList;
    }
}
