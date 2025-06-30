package br.knex.arthur_nicolas.importer.http;

import br.knex.arthur_nicolas.dto.DeputadoDTO;
import br.knex.arthur_nicolas.dto.DespesaDTO;
import br.knex.arthur_nicolas.mappers.DeputadoMapper;
import br.knex.arthur_nicolas.repositories.DeputadoRepository;
import br.knex.arthur_nicolas.importer.csv.DespesaCsvImport;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class DeputadoHttpImport {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private DeputadoMapper deputadoMapper;

    @Autowired
    private DespesaCsvImport despesaCsvImport;

    @Autowired
    private DeputadoRepository deputadoRepository;

    // Inicia a extração de dados e popula o Banco de Dados.
    public void initDataBase() throws Exception {
        List<DeputadoDTO> allData = getAllData();
        for(DeputadoDTO deputado : allData) {
            deputadoRepository.save(deputadoMapper.dtoToEntity(deputado));
        }
    }

    public List<String> getDeputadosIdList() throws JsonProcessingException {
        // URL para requisição http(GET) para acessar todas as informações de deputados pela API 'Dados Abertos v2' e retornar a lista de ID dos deputados.
        String url = "https://dadosabertos.camara.leg.br/api/v2/deputados";

        String allDataJson = restTemplate.getForObject(url, String.class);

        JsonNode jsonTreeRoot = objectMapper.readTree(allDataJson).path("dados");

        List<String> deputadoIdList = new ArrayList<>();

        for(JsonNode deputado : jsonTreeRoot) {
            deputadoIdList.add(deputado.path("id").asText());
        }

        return deputadoIdList;
    }

    public List<DeputadoDTO> getAllData() throws Exception {
        // URL para requisição http(GET) para acessar os dados detalhados de cada deputado, usando a lista de ID gerada em 'getDeputadosIdList()'.
        String urlDeputado = "https://dadosabertos.camara.leg.br/api/v2/deputados/{endpoint}";

        List<DeputadoDTO> deputadosAllData = new ArrayList<>();
        List<String> deputadosIdAsList = getDeputadosIdList();

        List<DespesaDTO> despesaAllData = despesaCsvImport.getAllDespesa();

        for(String id : deputadosIdAsList) {
            String deputadoDataJson = restTemplate.getForObject(urlDeputado, String.class, id);
            JsonNode deputadoTreeRoot = objectMapper.readTree(deputadoDataJson).path("dados");
            deputadosAllData.add(deputadoMapper.jsonToDto(deputadoTreeRoot, despesaCsvImport.getDespesaById(despesaAllData, id)));
        }

        return deputadosAllData;
    }
}
