package uk.co.boots.practice.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uk.co.boots.practice.dto.PracticeDTO;
import uk.co.boots.practice.repository.PracticeByRegionRepository;
import uk.co.boots.prescriber.dto.PrescriberDTO;
import uk.co.boots.prescriber.service.PrescriberRepositoryService;

@Service
public class PracticeRepositoryService {
	@Autowired
	PracticeEntityMappingService practiceMapper;
	@Autowired
	PracticeByRegionRepository repo;
	@Autowired
	PrescriberRepositoryService prescriberService;
	
	public PracticeDTO save (PracticeDTO practice) {
		return practiceMapper.toPracticeDTO(repo.save(practiceMapper.toPracticeByRegionEntity(practice)));
	}
	
	public void delete (PracticeDTO practice) {
		repo.delete(practiceMapper.toPracticeByRegionEntity(practice));
	}
	
	public PracticeDTO getPracticeForRegionAndCode (String region, String codeType, String codeValue) {
		return practiceMapper.toPracticeDTO(repo.findByRegionAndCode(region, codeType, codeValue));
	}

	public List<PracticeDTO> getAllPracticesForRegion(String region){
		return practiceMapper.toPracticeDTO(repo.findAllByRegion(region));
	}
	
}
