package it.epicode.be.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.epicode.be.model.CorsoDiLaurea;
import it.epicode.be.model.Docente;

public interface DocenteRepository extends JpaRepository<Docente, Long>  {

	

	
}
