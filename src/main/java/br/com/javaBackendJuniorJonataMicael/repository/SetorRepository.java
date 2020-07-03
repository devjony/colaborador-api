package br.com.javaBackendJuniorJonataMicael.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.javaBackendJuniorJonataMicael.domain.Setor;

@Repository
public interface SetorRepository extends JpaRepository<Setor, Integer> {

}
