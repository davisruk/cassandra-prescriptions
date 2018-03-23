package uk.co.boots.prescription.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uk.co.boots.prescription.dto.PrescriptionDTO;
import uk.co.boots.prescription.repository.PrescriptionByPatientRepository;
import uk.co.boots.prescription.repository.PrescriptionByPrescriberRepository;
import uk.co.boots.prescription.repository.PrescriptionByStoreRepository;
import uk.co.boots.prescription.repository.PrescriptionRepository;

@Service
public class PrescriptionRepositoryService {

	@Autowired
	PrescriptionRepository pr; 
	@Autowired
	PrescriptionByStoreRepository byStoreRepo;
	@Autowired
	PrescriptionMappingService mapper;
	@Autowired
	PrescriptionByPatientRepository byPatientRepo;
	@Autowired
	PrescriptionByPrescriberRepository byPrescriberRepo;
	
	
	public List<PrescriptionDTO> getByStoreId(UUID storeId){
		return mapper.toDTOFromByStore(byStoreRepo.findByStoreId(storeId));
	}
	public List<PrescriptionDTO> getByStoreIdAndPrescriptionDateLessThan(UUID storeId, LocalDateTime date){
		return mapper.toDTOFromByStore(byStoreRepo.findByStoreIdAndPrescriptionDateLessThan(storeId, date));
	}
	
	public PrescriptionDTO save (PrescriptionDTO prescription) {
		return mapper.toDTOFromByDate(pr.insert(mapper.toByDateFromDTO(prescription)));
	}
	public void delete (PrescriptionDTO prescription) {
		pr.delete(mapper.toByDateFromDTO(prescription));
	}
	
	public List<PrescriptionDTO> getByPrescriptionDate(LocalDateTime date){
		return mapper.toDTOFromByDate(pr.findByPrescriptionDate(date));
	}

	public List<PrescriptionDTO> getByPatientId(UUID id){
		return mapper.toDTOFromByPatient(byPatientRepo.findByPatientId(id));
	}

	public List<PrescriptionDTO> getByPrescriberId(UUID id){
		return mapper.toDTOFromByPrescriber(byPrescriberRepo.findByPrescriberId(id));
	}
}
