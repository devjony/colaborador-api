package br.com.javaBackendJuniorJonataMicael.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import br.com.javaBackendJuniorJonataMicael.dto.ColaboradorDTO;
import br.com.javaBackendJuniorJonataMicael.model.Colaborador;

@Mapper(componentModel = "spring")
public interface ColaboradorMapper {

	ColaboradorDTO toColaboradorDTO (Colaborador colaborador);
	List<ColaboradorDTO> toColaboradorDTOList (List<Colaborador> colaboradores);
	
	Colaborador toColaborador(ColaboradorDTO colaboradorDTO);
	List<Colaborador> toColaboradorList (List<ColaboradorDTO> colaboradoresDTO);
	
}
