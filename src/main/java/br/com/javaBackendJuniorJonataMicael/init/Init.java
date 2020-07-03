package br.com.javaBackendJuniorJonataMicael.init;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import br.com.javaBackendJuniorJonataMicael.domain.Colaborador;
import br.com.javaBackendJuniorJonataMicael.domain.Setor;
import br.com.javaBackendJuniorJonataMicael.repository.ColaboradorRepository;
import br.com.javaBackendJuniorJonataMicael.service.SetorService;
import javassist.tools.rmi.ObjectNotFoundException;

@Component
public class Init implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	ColaboradorRepository repo;
	
	@Autowired
	SetorService setorService;
	
	Logger logger = LoggerFactory.getLogger(Init.class);
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		Date nascimento = null;
		
		try {
			nascimento = format.parse("22/09/1992");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Colaborador colaborador;
		Colaborador colaborador2;
		Setor setor = null;
		Setor setor2 = null;
		
		try {
			setor = setorService.buscaPorId(1);
			setor2 = setorService.buscaPorId(2);
		} catch (ObjectNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		colaborador = new Colaborador("12778747788", "devjony", "975099889", "jonatamicael@gmail.com", nascimento, setor);
		colaborador2 = new Colaborador("11111111111", "aaaaaaaaa", "975099889", "jonatamicae22l@gmail.com", nascimento, setor2);
		repo.save(colaborador);
		repo.save(colaborador2);
	}
}
