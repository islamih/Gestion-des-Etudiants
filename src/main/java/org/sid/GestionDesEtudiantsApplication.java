package org.sid;


import java.text.DateFormat;
import java.text.SimpleDateFormat;


import org.sid.entities.Etudiant;
import org.sid.repositories.EtudiantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;



@SpringBootApplication
public class GestionDesEtudiantsApplication  implements CommandLineRunner{
	@Autowired
	private  EtudiantRepository etudiantRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(GestionDesEtudiantsApplication.class, args);
				
		
	}	
	@Override
	public void run(String... args) throws Exception {

		DateFormat df=new SimpleDateFormat("yyyy-MM-dd");
		etudiantRepository.save(new Etudiant(null,"islam",df.parse("1991-03-11"),"islam@gmail.com","islam.jpg"));
		etudiantRepository.save(new Etudiant(null,"ali",df.parse("1992-03-11"),"ali@gmail.com","ali.jpg"));
		etudiantRepository.save(new Etudiant(null,"abdo",df.parse("1993-03-11"),"abdo@gmail.com","abdo.jpg"));
		etudiantRepository.save(new Etudiant(null,"islam",df.parse("1991-03-11"),"islam@gmail.com","islam.jpg"));
		etudiantRepository.save(new Etudiant(null,"ali",df.parse("1992-03-11"),"ali@gmail.com","ali.jpg"));
		etudiantRepository.save(new Etudiant(null,"abdo",df.parse("1993-03-11"),"abdo@gmail.com","abdo.jpg"));
		etudiantRepository.save(new Etudiant(null,"islam",df.parse("1991-03-11"),"islam@gmail.com","islam.jpg"));
		
		etudiantRepository.findAll().forEach(x-> System.out.println(x.getNom()));
		
		System.out.println("-------------------------------------");
		
		Page<Etudiant> etds=etudiantRepository.findAll( PageRequest.of(0,2));

		etds.forEach(x-> System.out.println(x.getNom()));;
		
		System.out.println("-------------------------------------");
		
		Page<Etudiant> etd=etudiantRepository.chercher("%b%", PageRequest.of(0,3));
		
		etd.forEach(x-> System.out.println(x.getNom()));

	}

}
