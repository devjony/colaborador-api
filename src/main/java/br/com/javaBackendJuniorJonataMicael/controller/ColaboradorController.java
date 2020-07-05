package br.com.javaBackendJuniorJonataMicael.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.javaBackendJuniorJonataMicael.dto.ColaboradorDTO;
import br.com.javaBackendJuniorJonataMicael.exception.ColaboradorNaBlacklistException;
import br.com.javaBackendJuniorJonataMicael.exception.LimiteIdadeAtingidoException;
import br.com.javaBackendJuniorJonataMicael.model.Colaborador;
import br.com.javaBackendJuniorJonataMicael.service.ColaboradorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javassist.tools.rmi.ObjectNotFoundException;

/**
 * @author devjony
 *
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/colaborador")
@Api(value = "API Rest Colaborador")
public class ColaboradorController {
	
	Logger logger = LoggerFactory.getLogger(ColaboradorController.class);
	
	@Autowired
	private ColaboradorService colaboradorService;
	
	/**
	 * Método responsável por buscar todos os colaboradores
	 * @return
	 */
	@GetMapping
	@ApiOperation(value = "Retorna uma lista de colaboradores")
	public ResponseEntity<List<ColaboradorDTO>> buscarTodos() {
		logger.info("Request recebido [GET][/colaborador]");
		
		return ResponseEntity.ok(colaboradorService.buscarTodos());
	}
	
	/**
	 * Método responsável por buscar um colaborador por id
	 * @param id
	 * @return
	 * @throws ObjectNotFoundException 
	 */
	@GetMapping("/{id}")
	@ApiOperation(value = "Retorna um colaborador da base de dados")
	public ResponseEntity<Colaborador> buscar(@PathVariable("id") Integer id) throws ObjectNotFoundException {
		logger.info("Request recebido [GET][/colaborador/" + id + "]");
		
		return ResponseEntity.ok(colaboradorService.buscarPorId(id));
	}
	
	/**
	 * Método responsável pela inserção de um colaborador na base de dados
	 * @param colaborador
	 * @return
	 * @throws LimiteIdadeAtingidoException 
	 * @throws ColaboradorNaBlacklistException 
	 */
	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	@ApiOperation(value = "Insere um colaborador na base de dados")
	public ColaboradorDTO inserir(@RequestBody Colaborador colaborador) throws ColaboradorNaBlacklistException, LimiteIdadeAtingidoException {
		logger.info("Request recebido [POST][/colaborador]");
		
		return colaboradorService.inserir(colaborador);
	}
	
	/**
	 * Método responsável por remover um colaborador na base de dados
	 * @param id
	 * @return
	 */
	@DeleteMapping("/{id}")
	@ApiOperation(value = "Remove um colaborador da base de dados")
	public ResponseEntity<Colaborador> remover(@PathVariable("id") Integer id) {
		logger.info("Request recebido [DELETE][/colaborador/" + id + "]");
		
		colaboradorService.remover(id);
		return ResponseEntity.ok().build();
	}
}