package it.epicode.be.util;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import it.epicode.be.model.ArchivioDati;
import it.epicode.be.model.CorsoDiLaurea;
import it.epicode.be.model.Docente;
import it.epicode.be.model.Libretto;
import it.epicode.be.model.Studente;
import it.epicode.be.repository.CorsoLaureaRepository;
import it.epicode.be.repository.DocenteRepository;
import it.epicode.be.repository.LibrettoRepository;
import it.epicode.be.repository.StudenteRepository;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class DataLoader implements CommandLineRunner {

	@Autowired
	StudenteRepository studenteRepo;

	@Autowired
	CorsoLaureaRepository corsodilaureaRepo;

	@Autowired
	LibrettoRepository librettoRepo;

	@Autowired
	DocenteRepository docenteRepo;

	@Override
	public void run(String... args) throws Exception {

		CorsoDiLaurea corso = new CorsoDiLaurea();
		corso.setCodice("A234");
		corso.setNome("Informatica");
		corso.setIndirizzo("Tecnologie per il Web");
		corso.setNumeroEsami(20);
		

		CorsoDiLaurea corso1 = new CorsoDiLaurea();
		corso1.setCodice("D457");
		corso1.setNome("Fisica");
		corso1.setIndirizzo("Tecnologie per la fisica");
		corso1.setNumeroEsami(18);
		

		Studente studente = new Studente();
		studente.setMatricola("BA345");
		studente.setNome("Mario");
		studente.setCognome("Rossi");
		studente.setDataNascita(LocalDate.parse("1999-12-21"));
		studente.setIndirizzo("Via Nazionale, 35");
		studente.setCitta("Roma");
		studente.setEmail("m.rossi@email.em");
		studente.setCorsoDiLaurea(corso);

		Studente studente1 = new Studente();
		studente1.setMatricola("AT281");
		studente1.setNome("Gianni");
		studente1.setCognome("Verdi");
		studente1.setDataNascita(LocalDate.parse("2001-10-11"));
		studente1.setIndirizzo("Via Canne, 5");
		studente1.setCitta("Latina");
		studente1.setEmail("g.verdi@email.em");
		studente1.setCorsoDiLaurea(corso1);

		studenteRepo.save(studente);
		studenteRepo.save(studente1);
		corsodilaureaRepo.save(corso);
		corsodilaureaRepo.save(corso1);

		List<CorsoDiLaurea> corsiDocente = new ArrayList<>();
		corsiDocente.add(corso);

		List<CorsoDiLaurea> corsiDocente1 = new ArrayList<>();
		corsiDocente.add(corso1);

		Docente docente = new Docente();
		docente.setNome("Martino");
		docente.setCognome("Martello");
		docente.setCorsoDiLaurea(corsiDocente);
		

		Docente docente1 = new Docente();
		docente1.setNome("Antonio");
		docente1.setCognome("Pagano");
		docente1.setCorsoDiLaurea(corsiDocente1);
		
		docenteRepo.save(docente);
		docenteRepo.save(docente1);

		Libretto libretto = new Libretto();
		libretto.setCodiceLibretto("ABC123");
		libretto.setStudente(studente);
		librettoRepo.save(libretto);

		Libretto libretto1 = new Libretto();
		libretto1.setCodiceLibretto("ABC456");
		libretto1.setStudente(studente1);
		librettoRepo.save(libretto1);

	}

}
