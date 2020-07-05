package br.com.javaBackendJuniorJonataMicael.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.javaBackendJuniorJonataMicael.dto.ColaboradorDTO;
import br.com.javaBackendJuniorJonataMicael.exception.ColaboradorNaBlacklistException;
import br.com.javaBackendJuniorJonataMicael.exception.LimiteIdadeAtingidoException;
import br.com.javaBackendJuniorJonataMicael.mapper.ColaboradorMapper;
import br.com.javaBackendJuniorJonataMicael.model.Colaborador;
import br.com.javaBackendJuniorJonataMicael.repository.ColaboradorRepository;
import br.com.javaBackendJuniorJonataMicael.response.Response;
import br.com.javaBackendJuniorJonataMicael.util.DatasUtil;
import javassist.tools.rmi.ObjectNotFoundException;

/**
 * @author devjony
 *
 */
@Service
public class ColaboradorService {
	
	Logger logger = LoggerFactory.getLogger(ColaboradorService.class);
	
	/**
	 * Utilizado para calcular máximo e minimo de colaboradores
	 * permitidos tendo como referencia suas idades
	 */
	public static final Double PERCENTUAL = 0.20;
	
	/**
	 * Utilizado para calcular o percentual de 
	 * colaboradores maiores que essa idade na empresa
	 */
	public static final Integer IDADE_MAX = 65;
	
	/**
	 * Utilizado para calcular o percentual de 
	 * colaboradores menores que essa idade por setor
	 */
	public static final Integer IDADE_MIN = 18;
	
	@Autowired
	ColaboradorRepository repoColaborador;
	
	@Autowired
	ColaboradorMapper mapper;
	
	@Autowired
	BlackListApiService blackListApiService;
	
	/**
	 * Método que busca um colaborador no sistema por id
	 * @param id
	 * @return Colaborador
	 * @throws ObjectNotFoundException
	 */
	public Colaborador buscarPorId(Integer id) throws ObjectNotFoundException {
		logger.info("Buscando colaborador [id = " + id + "]");
		Optional<Colaborador> colaborador = repoColaborador.findById(id);
		
		return colaborador.orElseThrow(() -> new ObjectNotFoundException(
					"Colaborador não encontrado"));
	}
	
	/**
	 * Método que retorna uma lista de todos os colaboradores
	 * agrupados por setor
	 * @return List<ColaboradorDTO>
	 */
	public List<Colaborador> buscarTodos() {
		logger.info("Buscando todos os colaboradores");
		
		return repoColaborador.findAll();
	}
	
	/**
	 * Método que insere um novo colaborador no sistema
	 * 
	 * @param colaborador
	 * @return {@link ColaboradorDTO}
	 * @throws ColaboradorNaBlacklistException
	 * @throws LimiteIdadeAtingidoException
	 */
	public ResponseEntity<Response<Colaborador>> inserir(ColaboradorDTO colaboradorDTO) throws ColaboradorNaBlacklistException, LimiteIdadeAtingidoException {
		logger.info("Verificando se colaborador existe na blackListApi");
		
		if(blackListApiService.verificarColaboradorPorCpf(colaboradorDTO.getCpf())) {
			logger.info("Colaborador não encontrado na blackListApi");
			
			Response<Colaborador> response = new Response<Colaborador>();
			
			int idadeColaborador = DatasUtil.calculaIdade(colaboradorDTO.getDataNascimento());
			
			if(idadeColaborador > IDADE_MAX) {
				
				boolean insere = verificarPercentualIdadeMax(
										repoColaborador.recuperaTotalColaboradores(),
										repoColaborador.recuperaTotalColaboradoresIdadeMax(IDADE_MAX)
										);
				
				if (insere) {
					response.setData(repoColaborador.save(mapper.toColaborador(colaboradorDTO)));
					logger.info("Colaborador inserido com sucesso");
					return ResponseEntity.status(HttpStatus.CREATED).body(response);
				} else {
					throw new LimiteIdadeAtingidoException("Numero máximo de colaboradores maiores que "
													+ IDADE_MAX + " anos, operação cancelada");
				}
			} else {
				response.setData(repoColaborador.save(mapper.toColaborador(colaboradorDTO)));
				logger.info("Colaborador inserido com sucesso");
				return ResponseEntity.status(HttpStatus.CREATED).body(response);
			}
		} else {
			throw new ColaboradorNaBlacklistException("Colaborador encontrado na black list, inserção cancelada");
		}
	}
	
	/**
	 * Método sem retorno que remove um colaborador do sistema através do id
	 * @param id
	 */
	public void remover(Integer id) {
		repoColaborador.deleteById(id);
		
		logger.info("Colaborador [id = " + id + "] removido");
	}
	
	/**
	 * Método que verifica se um colaborador maior de 65 pode ser cadastrado
	 * Retorna true caso não possa cadastrar
	 * Retorna false caso possa ser cadastrar
	 * @param totalColaboradores
	 * @param totalIdadeMax
	 * @return {@link Boolean}
	 */
	private boolean verificarPercentualIdadeMax(Integer totalColaboradores, Integer totalIdadeMax) {
		logger.info("Verificando total de colaboradores com idade maior que " + IDADE_MAX + " anos");
		
		return ((totalIdadeMax + 1) <= ((totalColaboradores + 1) * PERCENTUAL));
	}
}
