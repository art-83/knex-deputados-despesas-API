package br.knex.arthur_nicolas.importer.csv;

import br.knex.arthur_nicolas.dto.DespesaDTO;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

@Service
public class DespesaCsvImport {

    public List<DespesaDTO> getAllDespesa() throws Exception {
        String despesaPath = "src/main/CSV-Files/Ano-2025.csv";
        Reader reader = new FileReader(despesaPath);

        CsvToBean<DespesaDTO> csvToBean = new CsvToBeanBuilder<DespesaDTO>(reader)
                .withType(DespesaDTO.class)
                .withSeparator(';')
                .withQuoteChar('"')
                .withIgnoreLeadingWhiteSpace(true)
                .build();
        return csvToBean.parse();
    }

    public List<DespesaDTO> getDespesaById(List<DespesaDTO> allDespesaData, String id) {
        List<DespesaDTO> despesaByIdList = new ArrayList<>();

        for(DespesaDTO despesa : allDespesaData) {
            if(despesa.getDeputadoId().equals(id)) {
                despesaByIdList.add(despesa);
            }
        }
        return despesaByIdList;
    }
}
