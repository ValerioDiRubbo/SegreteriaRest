package it.epicode.be.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import it.epicode.be.model.CorsoDiLaurea;
import it.epicode.be.service.CorsoDiLaureaService;

@Component
public class CorsoConverter implements Converter<Long, CorsoDiLaurea> {

	@Autowired
	CorsoDiLaureaService corsoService;

	/**
	 * METODO CONVERTER: converte la chiave della mappa in un oggetto dal DummyDb
	 * 
	 */

	@Override
	public CorsoDiLaurea convert(Long idCorso) {

		return corsoService.findById(idCorso).get();
	}

}
