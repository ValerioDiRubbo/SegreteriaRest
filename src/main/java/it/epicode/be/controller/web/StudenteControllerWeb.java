package it.epicode.be.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import it.epicode.be.model.Studente;
import it.epicode.be.service.StudenteService;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/web")
public class StudenteControllerWeb {

	@Autowired
	StudenteService studenteService;

	@GetMapping("/mostraelenco")
	public ModelAndView mostraElencoStudenti() {
		log.info("Test Elenco studenti Thymeleaf.");
		ModelAndView view = new ModelAndView("elencostudenti");
		view.addObject("studenti" , studenteService.findAll());
		//model.addAttribute("listastudenti", studenteService.findAll());
		return view;
	}

	@GetMapping("/mostraformaggiungi")
	public String mostraFormAggiungi(Studente studente, Model model) {
		log.info("Test Form studenti.");
		return "Form studenti";
	}

	@PostMapping("/aggiungistudente")
	public String aggiungiStudente(Studente studente, BindingResult result, Model model) {
		log.info("Test aggiungi studenti.");
		return "Studente aggiunto.";
	}

	@GetMapping("/mostraformaggiorna/{matricola}")
	public String mostraFormAggiungi(@PathVariable String matricola, Model model) {
		log.info("Test form aggiorna studenti.");
		return "Form aggiornastudenti";
	}

	@PostMapping("/aggiornastudente/{matricola}")
	public String aggiornaStudente(@PathVariable String matricola, Studente studente, BindingResult result,
			Model model) {
		log.info("Studente aggiornato.");
		return "Studente aggiornato con successo";
	}

	@GetMapping("/eliminastudente/{matricola}")
	public String eliminaStudente(@PathVariable String matricola, Model model) {
		log.info("Elimina studente.");
		return "Studente eliminato.";
	}
}
