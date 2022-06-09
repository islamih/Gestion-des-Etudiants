package org.sid.web;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.validation.Valid;

import org.sid.entities.Etudiant;
import org.sid.repositories.EtudiantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class EtudiantController {

	@Autowired
	private EtudiantRepository etudiantRepository;
	
	@GetMapping(path = "/Etudiants")
	public String index(Model model, @RequestParam(name = "page", defaultValue = "0") int currentPage,
		@RequestParam(name = "size", defaultValue = "5") int size,
		@RequestParam(name = "motCle",defaultValue ="") String mc) {
		 Page<Etudiant> PageEtd = etudiantRepository.chercher(mc,PageRequest.of(currentPage, size));
		model.addAttribute("PageEtudiants", PageEtd);
		model.addAttribute("motCle",mc);
		model.addAttribute("page", currentPage);
		

		int totalPages = PageEtd.getTotalPages();
		if (totalPages > 0) {
			List<Integer> pageNumbers = IntStream.rangeClosed(0, totalPages).boxed().collect(Collectors.toList());
			model.addAttribute("pageNumbers", pageNumbers);
			
		}
		return "etudiants";

	}
	@GetMapping(path="/deleteEtudiant")
	public String delete(Long id ,String motCle ,String page ,int size) {
		etudiantRepository.deleteById(id);
	      return "redirect:/Etudiants?page="+page+"&motCle="+motCle+"&size="+size;
	}
	@GetMapping(path="/Form")

    public String formEtudiant(Model model){
        model.addAttribute("etudiant",new Etudiant());
        return "formEtudiant";

}
	@PostMapping(path="/SaveEtudiant")
    public String save( @Valid Etudiant etu,BindingResult bindgResult ){
		if( bindgResult.hasErrors()) { return "formEtudiant";}
		
		etudiantRepository.save(etu);
        return "redirect:/Etudiants";

}
	}