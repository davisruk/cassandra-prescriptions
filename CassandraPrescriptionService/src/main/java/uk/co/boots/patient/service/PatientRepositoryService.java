package uk.co.boots.patient.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uk.co.boots.patient.dto.PatientDTO;
import uk.co.boots.patient.repository.PatientByRegionRepository;
import uk.co.boots.prescription.repository.PrescriptionByPatientRepository;

@Service
public class PatientRepositoryService {
	@Autowired
	PatientEntityMappingService patientMapper;
	@Autowired
	PatientByRegionRepository repo;
	@Autowired
	PrescriptionByPatientRepository prescriptionRepo;
	
	public PatientDTO save (PatientDTO patient) {
		return patientMapper.toPatientDTO(repo.save(patientMapper.toPatientByRegionEntity(patient)));
	}
	
	public PatientDTO getPatientForRegionAndCode (String region, String codeType, String codeValue) {
		return patientMapper.toPatientDTO(repo.findByRegionAndCode(region, codeType, codeValue));
	}

	public void delete (PatientDTO patient) {
		repo.delete(patientMapper.toPatientByRegionEntity(patient));
	}
	
	public List<PatientDTO> getAllPatientsForRegion(String region){
		return patientMapper.toPatientDTO(repo.findAllByRegion(region));
	}

	public List<PatientDTO> getPatientWithPrescriptions (UUID patientId){
		return null; 
	}
}
