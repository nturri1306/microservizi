
package it.csa.patient.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;



import it.csa.patient.model.Patient;
import it.csa.patient.repository.PatientRepository;

@RestController
public class PatientController {

	
	@Autowired
	PatientRepository patientRepository;
	
	

	
	@PostMapping ("/create")
	public boolean create( @RequestBody Patient patient) {
		
		
		try
		
		{	
			patientRepository.save(patient);
		}
		catch(Exception ex)
		{
			return false;
		}
		
		
		
	   return true;
	}
	
}
