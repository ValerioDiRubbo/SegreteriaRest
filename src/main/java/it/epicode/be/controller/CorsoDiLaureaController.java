package it.epicode.be.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import it.epicode.be.model.CorsoDiLaurea;
import it.epicode.be.service.CorsoDiLaureaService;

@Controller
@RequestMapping("/api")
public class CorsoDiLaureaController {

	@Autowired
	private CorsoDiLaureaService corsoService;

	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@GetMapping(path = "/corsolaurea")
	public ResponseEntity<Page<CorsoDiLaurea>> findAll(Pageable pageable) {
		Page<CorsoDiLaurea> findAll = corsoService.findAll(pageable);

		if (findAll.hasContent()) {
			return new ResponseEntity<>(findAll, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@GetMapping(path = "/corsolaurea/{id}")
	public ResponseEntity<CorsoDiLaurea> findById(@PathVariable(required = true) Long id) {
		Optional<CorsoDiLaurea> find = corsoService.findById(id);

		if (find.isPresent()) {
			return new ResponseEntity<>(find.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping(path = "/corsolaurea")
	// @PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<CorsoDiLaurea> save(@RequestBody CorsoDiLaurea corso) {
		CorsoDiLaurea save = corsoService.save(corso);
		return new ResponseEntity<>(save, HttpStatus.OK);

	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PutMapping(path = "/corsolaurea/{id}")
	// @PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<CorsoDiLaurea> update(@PathVariable Long id, @RequestBody CorsoDiLaurea corso) {
		CorsoDiLaurea save = corsoService.update(corso, id);
		return new ResponseEntity<>(save, HttpStatus.OK);

	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@DeleteMapping(path = "/corsolaurea/{id}")
	// @PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<String> delete(@PathVariable Long id) {
		corsoService.delete(id);
		return new ResponseEntity<>("Corso cancellato.", HttpStatus.OK);

	}
}
