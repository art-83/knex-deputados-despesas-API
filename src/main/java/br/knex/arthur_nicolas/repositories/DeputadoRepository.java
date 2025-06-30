package br.knex.arthur_nicolas.repositories;

import br.knex.arthur_nicolas.dto.response.DeputadoResponseDTO;
import br.knex.arthur_nicolas.models.Deputado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeputadoRepository extends JpaRepository<Deputado, String> {

    /*
    Retorna uma lista de Reponse apropriadamente modelada para o Query Request em DeputadoQueryService.
    DTO modelado a partir de: "Deputado" -> "DeputadoResponseDTO" - Query feita usando JPQL, de JpaRepository.
    */
    @Query("""
    SELECT new br.knex.arthur_nicolas.dto.response.DeputadoResponseDTO(
        d.id,
        d.nome,
        d.uf,
        d.cpf,
        d.partido
    )
    FROM Deputado d
""")
    public List<DeputadoResponseDTO> getAllDeputatoResponse();
}