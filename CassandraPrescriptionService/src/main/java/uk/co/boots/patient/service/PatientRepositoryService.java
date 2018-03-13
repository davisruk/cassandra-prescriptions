package uk.co.boots.patient.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uk.co.boots.patient.dto.PatientDTO;
import uk.co.boots.patient.repository.PatientByRegionRepository;

@Service
public class PatientRepositoryService {
	@Autowired
	PatientEntityMappingService patientMapper;
	@Autowired
	PatientByRegionRepository repo;
	
	public PatientDTO save (PatientDTO patient) {
		return patientMapper.toPatientDTO(repo.save(patientMapper.toPatientByRegionEntity(patient)));
	}
	
	public void delete (PatientDTO patient) {
		repo.delete(patientMapper.toPatientByRegionEntity(patient));
	}
	
	public List<PatientDTO> getAllPatientsForRegion(String region){
		return patientMapper.toPatientDTO(repo.findAllByRegion(region));
	}
}
