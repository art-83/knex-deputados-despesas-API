package br.knex.arthur_nicolas.services;

import br.knex.arthur_nicolas.dto.response.DespesaResponseDTO;
import br.knex.arthur_nicolas.repositories.DespesaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class DespesaQueryService {

    @Autowired
    private DespesaRepository despesaRepository;

    public List<DespesaResponseDTO> getAllDespesaData() {
        return despesaRepository.getAllDespesaResponse();
    }

    // Retorna lista de Despesa filtrada por: "id" - retorno no GET.
    public List<DespesaResponseDTO> getDespesaById(String id) {
        List<DespesaResponseDTO> allDespesaData = getAllDespesaData();
        List<DespesaResponseDTO> despesaListById = new ArrayList<>();

        for (DespesaResponseDTO despesa : allDespesaData) {
            if (despesa.getDeputado().equals(id)) {
                despesaListById.add(despesa);
            }
        }

        return despesaListById;
    }

    // Retorna soma resultante de todas as despesas.
    public BigDecimal getSumAllDespesa() {
        BigDecimal resultSum = new BigDecimal("0");
        List<DespesaResponseDTO> allDespesaData = despesaRepository.getAllDespesaResponse();

        for (DespesaResponseDTO despesa : allDespesaData) {
            resultSum = resultSum.add(despesa.getValorLiquido());
        }

        return resultSum;
    }

    // Retorna soma resultante de todas as despesas filtrada por: "id".
    public BigDecimal getDespesaSumById(String id) {
        List<DespesaResponseDTO> allDespesaById = getDespesaById(id);
        BigDecimal sum = new BigDecimal("0");
        for (DespesaResponseDTO despesa : allDespesaById) {
            sum = sum.add(despesa.getValorLiquido());
        }
        return sum;
    }

    // Retorna lista de Despesa filtrada por: "Data de Emissão".
    public List<DespesaResponseDTO> getDespesaByData(String data) {
        List<DespesaResponseDTO> allDespesaData = getAllDespesaData();
        List<DespesaResponseDTO> despesaListByData = new ArrayList<>();

        for (DespesaResponseDTO despesa : allDespesaData) {
            if (!despesa.getDataEmissao().isEmpty() && formatDataEmissao(despesa.getDataEmissao()).equals(data)) {
                despesaListByData.add(despesa);
            }
        }
        return despesaListByData;
    }

    // Retorna 10 primeiros caracteres de uma data - EX.: ["2025-02-05T00:00:00"] -> ["2025-02-05"].
    public static String formatDataEmissao(String data) {
        return data.substring(0, 10);
    }

    // Retorna lista de Despesa filtrada por: "Fornecedor".
    public List<DespesaResponseDTO> getDespesaByFornecedor(String fornecedor) {
        List<DespesaResponseDTO> allDespesaData = getAllDespesaData();
        List<DespesaResponseDTO> despesaListByFornecedor = new ArrayList<>();

        for (DespesaResponseDTO despesa : allDespesaData) {
            if(!despesa.getFornecedor().isEmpty() && formatFornecedor(despesa.getFornecedor()).equals(formatFornecedor(fornecedor))) {
                despesaListByFornecedor.add(despesa);
            }
        }

        return despesaListByFornecedor;
    }

    // Retorna String de fornecedor sem espaços e em 'toLowerCase()' - EX.: ["CELULAR FUNCIONAL"] -> ["celularfuncional"].
    public static String formatFornecedor(String fornecedor) {
        StringBuilder formattedFornecedor = new StringBuilder();
        for(Character c : fornecedor.toCharArray()) {
            if(c != ' ') {
                formattedFornecedor.append(c);
            }
        }
        return formattedFornecedor.toString().toLowerCase();
    }
}
