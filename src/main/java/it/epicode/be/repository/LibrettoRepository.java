package it.epicode.be.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.epicode.be.model.CorsoDiLaurea;
import it.epicode.be.model.Libretto;

public interface LibrettoRepository extends JpaRepository<Libretto, Long>  {

	
}
