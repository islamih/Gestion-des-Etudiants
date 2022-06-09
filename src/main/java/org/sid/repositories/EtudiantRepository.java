package org.sid.repositories;

import org.sid.entities.Etudiant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EtudiantRepository extends JpaRepository<Etudiant, Long>{


	@Query("select e from Etudiant e where e.nom like %:x%")
	public Page<Etudiant> chercher(
			@Param("x") String mc , Pageable pageable);
	
	public Page<Etudiant> findByNomContains(String mc, Pageable pageable);
	
	
}
