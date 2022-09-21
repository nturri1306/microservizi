
package it.csa.patient.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.csa.patient.model.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long> {

	    
	
}

