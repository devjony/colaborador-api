package br.com.javaBackendJuniorJonataMicael.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.javaBackendJuniorJonataMicael.domain.Colaborador;
import br.com.javaBackendJuniorJonataMicael.service.ColaboradorService;
import javassist.tools.rmi.ObjectNotFoundException;

@RestController
@RequestMapping("/api/colaborador")
@CrossOrigin(origins = "*")
public class ColaboradorController {
	
	Logger logger = LoggerFactory.getLogger(ColaboradorController.class);
	
	@Autowired
	private ColaboradorService colaboradorService;
	
	@GetMapping
	public ResponseEntity<List<Colaborador>> buscarTodos() {
		logger.info("Request recebido [GET][/colaborador]");
		
		return ResponseEntity.ok(colaboradorService.buscarTodos());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Colaborador> buscar(@PathVariable("id") Integer id) {
		logger.info("Request recebido [GET][/colaborador/" + id + "]");
		
		try {
			return ResponseEntity.ok(colaboradorService.buscarPorId(id));
		} catch (ObjectNotFoundException e) {
			logger.info("Colaborador não encontrado");
			return ResponseEntity.notFound().build();
		}
	}
}