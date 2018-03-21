package uk.co.boots.practice.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uk.co.boots.practice.dto.PracticeDTO;
import uk.co.boots.practice.repository.PracticeByPrescriberRepository;
import uk.co.boots.practice.repository.PracticeByRegionRepository;
import uk.co.boots.prescriber.entity.PrescriberByPracticeEntity;
import uk.co.boots.prescriber.repository.PrescriberByPracticeRepository;
import uk.co.boots.prescriber.repository.PrescriberPracticeBatchRepository;
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
	@Autowired
	PrescriberPracticeBatchRepository prescriberPracticeBatchRepo;
	
	public PracticeDTO save (PracticeDTO practice) {
		return practiceMapper.toPracticeDTO(repo.save(practiceMapper.toPracticeByRegionEntity(practice)));
	}
	
	public void delete (PracticeDTO practice) {
		//find all the prescribers for this practice
		List<PrescriberByPracticeEntity> pbpList = prescriberRepo.findByPracticeId(practice.getId());
		//if no prescribers then delete the PracticeByRegion entry only
		if (pbpList == null || pbpList.size() == 0)
			repo.delete(practiceMapper.toPracticeByRegionEntity(practice));
		else {
		// otherwise use a Batch Repository to delete PracticeByRegion, PrescriberByPractice and PracticeByPrescriber
			prescriberPracticeBatchRepo.deleteAllIncludingPractice(pbpList);	
		}
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
}
