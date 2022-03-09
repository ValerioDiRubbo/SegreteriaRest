package it.epicode.be.controller.rest;

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

import it.epicode.be.model.Studente;
import it.epicode.be.service.StudenteService;

@Controller
@RequestMapping("/api")
public class StudenteController {

	@Autowired
	private StudenteService studenteService;

	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@GetMapping(path = "/studente")
	
	public ResponseEntity<Page<Studente>> findAll(Pageable pageable) {
		Page<Studente> findAll = studenteService.findAll(pageable);

		if (findAll.hasContent()) {
			return new ResponseEntity<>(findAll, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@GetMapping(path = "/studente/{id}")
	public ResponseEntity<Studente> findById(@PathVariable(required = true) Long id) {
		Optional<Studente> find = studenteService.findById(id);

		if (find.isPresent()) {
			return new ResponseEntity<>(find.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping(path = "/studente")
	// @PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<Studente> save(@RequestBody Studente studente) {
		Studente save = studenteService.save(studente);
		return new ResponseEntity<>(save, HttpStatus.OK);

	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PutMapping(path = "/studente/{id}")
	//@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<Studente> update(@PathVariable Long id, @RequestBody Studente studente) {
		Studente save = studenteService.update(studente, id);
		return new ResponseEntity<>(save, HttpStatus.OK);

	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@DeleteMapping(path = "/studente/{id}")
	//@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<String> delete(@PathVariable Long id) {
		studenteService.delete(id);
		return new ResponseEntity<>("Studente cancellato.", HttpStatus.OK);

	}
}
