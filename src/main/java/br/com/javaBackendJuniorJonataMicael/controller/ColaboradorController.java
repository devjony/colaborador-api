package br.com.javaBackendJuniorJonataMicael.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
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
import br.com.javaBackendJuniorJonataMicael.response.Response;
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
	public ResponseEntity<List<Colaborador>> buscarTodos() {
		logger.info("Request recebido [GET][/colaborador]");
		
		return ResponseEntity.ok().body(colaboradorService.buscarTodos());
	}
	
	/**
	 * Método responsável por buscar um colaborador por id
	 * @param id
	 * @return
	 * @throws ObjectNotFoundException 
	 */
	@GetMapping("/{id}")
	@ApiOperation(value = "Retorna um colaborador da base de dados")
	public ResponseEntity<Response<Colaborador>> buscar(@PathVariable("id") Integer id) throws ObjectNotFoundException {
		logger.info("Request recebido [GET][/colaborador/" + id + "]");
		
		Response<Colaborador> response = new Response<Colaborador>();
		response.setData(colaboradorService.buscarPorId(id));
		return ResponseEntity.ok().body(response);
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
	public ResponseEntity<Response<Colaborador>> inserir(@Valid @RequestBody ColaboradorDTO colaboradorDTO, BindingResult result) throws ColaboradorNaBlacklistException, LimiteIdadeAtingidoException {
		logger.info("Request recebido [POST][/colaborador]");
		
		Response<Colaborador> response = new Response<Colaborador>();
		
		if(result.hasErrors()) {
			result.getAllErrors().forEach(error -> response.getErros().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		
		return colaboradorService.inserir(colaboradorDTO);
	}
	
	/**
	 * Método responsável por remover um colaborador na base de dados
	 * @param id
	 * @return
	 */
	@DeleteMapping("/{id}")
	@ApiOperation(value = "Remove um colaborador da base de dados")
	public ResponseEntity<Response<Colaborador>> remover(@PathVariable("id") Integer id) {
		logger.info("Request recebido [DELETE][/colaborador/" + id + "]");
		
		colaboradorService.remover(id);
		return ResponseEntity.ok().build();
	}
}