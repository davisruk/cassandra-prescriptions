package uk.co.boots.prescription.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uk.co.boots.prescription.dto.PrescriptionDTO;
import uk.co.boots.prescription.entity.PrescriptionByStoreEntity;
import uk.co.boots.prescription.repository.PrescriptionByStoreRepository;

@Service
public class PrescriptionRepositoryService {

	@Autowired
	PrescriptionByStoreRepository byStoreRepo;
	@Autowired
	PrescriptionMappingService mapper;
	
	public List<PrescriptionDTO> getByStoreId(UUID storeId){
		return mapper.toDTOFromByStore(byStoreRepo.findByStoreId(storeId));
	}
	public List<PrescriptionDTO> getByStoreIdAndPrescriptionDateLessThan(UUID storeId, LocalDateTime date){
		return mapper.toDTOFromByStore(byStoreRepo.findByStoreIdAndPrescriptionDateLessThan(storeId, date));
	}
}
