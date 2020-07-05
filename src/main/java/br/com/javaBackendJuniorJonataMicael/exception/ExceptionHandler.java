package br.com.javaBackendJuniorJonataMicael.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.javaBackendJuniorJonataMicael.response.Response;
import javassist.tools.rmi.ObjectNotFoundException;

@RestControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {

	@org.springframework.web.bind.annotation.ExceptionHandler(EmptyResultDataAccessException.class)
	public ResponseEntity<Response<String>> errorNaoEncontrado(Exception e) {
		return criarExceptionResponse(e);
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<Response<String>> errorObjetoNaoEncontrado(Exception e) {
		return criarExceptionResponse(e);
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<Response<String>> errorBadRequest(Exception e) {
		return criarExceptionResponse(e);
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<Response<String>> errorEntradaDuplicada(Exception e) {
		return criarExceptionResponse(e);
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler(LimiteIdadeAtingidoException.class)
	public ResponseEntity<Response<String>> errorLimiteIdadeAtingido(Exception e) {
		return criarExceptionResponse(e);
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler(ColaboradorNaBlacklistException.class)
	public ResponseEntity<Response<String>> errorColaboradorNaBlackList(Exception e) {
		return criarExceptionResponse(e);
	}
	
	private ResponseEntity<Response<String>> criarExceptionResponse(Exception e) {
		Response<String> response = new Response<String>();
		List<String> erros = new ArrayList<String>();
		erros.add(e.getMessage());
		response.setErros(erros);
		return ResponseEntity.badRequest().body(response);
	}
}
