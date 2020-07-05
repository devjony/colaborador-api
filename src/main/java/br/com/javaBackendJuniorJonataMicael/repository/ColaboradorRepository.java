package br.com.javaBackendJuniorJonataMicael.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.javaBackendJuniorJonataMicael.model.Colaborador;

@Repository
public interface ColaboradorRepository extends JpaRepository<Colaborador, Integer> {
	
	@Query(value = "SELECT COUNT(*) FROM colaborador", nativeQuery = true)
	public Integer recuperaTotalColaboradores();
	
	@Query(value = "SELECT COUNT(*) FROM colaborador WHERE YEAR(FROM_DAYS(TO_DAYS(NOW())-TO_DAYS(data_nascimento))) > :idade", nativeQuery = true)
	public Integer recuperaTotalColaboradoresIdadeMax(@Param("idade") Integer idade);
}
