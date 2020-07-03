package br.com.javaBackendJuniorJonataMicael.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.javaBackendJuniorJonataMicael.domain.Colaborador;
import br.com.javaBackendJuniorJonataMicael.repository.ColaboradorRepository;
import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class ColaboradorService {
	
	Logger logger = LoggerFactory.getLogger(ColaboradorService.class);

	@Autowired
	ColaboradorRepository repoColaborador;
	
	public Colaborador buscarPorId(Integer id) throws ObjectNotFoundException {
		logger.info("Buscando colaborador: id = " + id);
		Optional<Colaborador> colaborador = repoColaborador.findById(id); 
		
		return colaborador.orElseThrow(() -> new ObjectNotFoundException(
				"colaborador não encontrado, id: " + id + "Tipo: " + ColaboradorService.class.getName()));
	}

	public List<Colaborador> buscarTodos() {
		logger.info("Buscando todos os colaboradores");
		List<Colaborador> colaboradores = repoColaborador.findAll(); 
		
		logger.info("Retornando os colaboradores");
		return colaboradores;
	}
}
