package br.com.javaBackendJuniorJonataMicael.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.javaBackendJuniorJonataMicael.model.Colaborador;
import javassist.tools.rmi.ObjectNotFoundException;

@RestControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {

	@org.springframework.web.bind.annotation.ExceptionHandler(EmptyResultDataAccessException.class)
	public ResponseEntity<Colaborador> errorNaoEncontrado(Exception e) {
		return ResponseEntity.notFound().build();
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<Colaborador> errorObjetoNaoEncontrado(Exception e) {
		return ResponseEntity.notFound().build();
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<Colaborador> errorBadRequest(Exception e) {
		return ResponseEntity.badRequest().build();
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<Colaborador> errorEntradaDuplicada(Exception e) {
		return ResponseEntity.badRequest().build();
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler(LimiteIdadeAtingidoException.class)
	public ResponseEntity<Colaborador> errorLimiteIdadeAtingido(Exception e) {
		return ResponseEntity.badRequest().build();
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler(ColaboradorNaBlacklistException.class)
	public ResponseEntity<Colaborador> errorColaboradorNaBlackList(Exception e) {
		return ResponseEntity.badRequest().build();
	}
}
