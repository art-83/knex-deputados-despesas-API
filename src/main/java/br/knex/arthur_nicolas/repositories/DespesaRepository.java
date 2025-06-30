package br.knex.arthur_nicolas.repositories;

import br.knex.arthur_nicolas.dto.DespesaDTO;
import br.knex.arthur_nicolas.dto.response.DespesaResponseDTO;
import br.knex.arthur_nicolas.models.Despesa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DespesaRepository extends JpaRepository<Despesa, String> {

    /*
    Retorna uma lista de Reponse apropriadamente modelada para o Query Request em DespesaQueryService.
    DTO modelado a partir de: "Despesa" -> "DespesaResponseDTO" - Query feita usando JPQL, de JpaRepository.
    */
    @Query("""
    SELECT new br.knex.arthur_nicolas.dto.response.DespesaResponseDTO(
        d.dataEmissao,
        d.fornecedor,
        d.valorLiquido,
        d.urlDocumento,
        d.deputado.id
    )
    FROM Despesa d
""")
    public List<DespesaResponseDTO> getAllDespesaResponse();
}
