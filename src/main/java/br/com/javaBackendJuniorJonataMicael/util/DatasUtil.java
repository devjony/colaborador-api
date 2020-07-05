package br.com.javaBackendJuniorJonataMicael.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneOffset;
import java.util.Date;

public class DatasUtil {

	public static int calculaIdade(Date dataNascimento) {
		
		LocalDate dataNascimentoConvertida = converteParaLocalDate(dataNascimento);
		return Period.between(dataNascimentoConvertida, LocalDate.now()).getYears();
	}
	
	public static LocalDate converteParaLocalDate(Date dataNascimento) {
		
		return LocalDateTime.ofInstant(dataNascimento.toInstant(), ZoneOffset.UTC).toLocalDate();
	}
}