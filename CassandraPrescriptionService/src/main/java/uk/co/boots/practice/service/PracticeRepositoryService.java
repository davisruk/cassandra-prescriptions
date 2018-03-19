package uk.co.boots.practice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uk.co.boots.practice.dto.PracticeDTO;
import uk.co.boots.practice.repository.PracticeByPrescriberRepository;
import uk.co.boots.practice.repository.PracticeByRegionRepository;
import uk.co.boots.prescriber.dto.PrescriberDTO;
import uk.co.boots.prescriber.entity.PrescriberByPracticeEntity;
import uk.co.boots.prescriber.repository.PrescriberByPracticeRepository;
import uk.co.boots.prescriber.service.PrescriberEntityMappingService;

@Service
public class PracticeRepositoryService {
	@Autowired
	PracticeEntityMappingService practiceMapper;
	@Autowired
	PracticeByRegionRepository repo;
	@Autowired
	PrescriberByPracticeRepository prescriberRepo;
	@Autowired
	PrescriberEntityMappingService prescriberMapper;
	@Autowired
	PracticeByPrescriberRepository practiceByPrescriberRepo;
	
	public PracticeDTO save (PracticeDTO practice) {
		return practiceMapper.toPracticeDTO(repo.save(practiceMapper.toPracticeByRegionEntity(practice)));
	}
	
	public void delete (PracticeDTO practice) {
		repo.delete(practiceMapper.toPracticeByRegionEntity(practice));
		// need to move this when using the batch implementation for the M:M with PresctiberByPracticeEntity
		practice.getPrescribers().forEach(prescriber->practiceByPrescriberRepo.delete(practiceMapper.toPracticeByPrescriberEntity(practice, prescriber)));
		;
	}
	
	public PracticeDTO getPracticeForRegionAndCode (String region, String codeType, String codeValue) {
		return practiceMapper.toPracticeDTO(repo.findByRegionAndCode(region, codeType, codeValue));
	}

	public List<PracticeDTO> getAllPracticesForRegion(String region){
		return practiceMapper.toPracticeDTO(repo.findAllByRegion(region));
	}
	
	public PracticeDTO getPractice(UUID id) {
		List<PrescriberByPracticeEntity> prescriberList = prescriberRepo.findByPracticeId(id);
		return prescriberMapper.toPracticeDTOFromPrescriberByPracticeEntityList(prescriberList);
	}
	
	public PracticeDTO addPrescriber (PracticeDTO practice, PrescriberDTO prescriber) {
		// PrescriberByPracticeRepository will have to change to a class and extend
		// the Spring CassandraRepository as eventually PracticeByPrescriber will also
		// need to persist this in a batch operation
		practiceByPrescriberRepo.save(practiceMapper.toPracticeByPrescriberEntity(practice, prescriber));
		if (practice.getPrescribers() == null)
			practice.setPrescribers(new ArrayList<PrescriberDTO>());
		practice.getPrescribers().add(prescriber);
		return practice;
	}
	
}
