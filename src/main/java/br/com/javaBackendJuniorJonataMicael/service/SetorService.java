package br.com.javaBackendJuniorJonataMicael.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.javaBackendJuniorJonataMicael.model.Setor;
import br.com.javaBackendJuniorJonataMicael.repository.SetorRepository;
import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class SetorService {

	@Autowired
	SetorRepository repoSetor;
	
	public Setor buscaPorId(Integer id) throws ObjectNotFoundException {
		Optional<Setor> setor = repoSetor.findById(id); 
		
		return setor.orElseThrow(() -> new ObjectNotFoundException(
				"setor não encontrado, id: " + id + "Tipo: " + Setor.class.getName()));
	}
}
