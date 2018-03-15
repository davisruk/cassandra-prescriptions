package uk.co.boots.prescriber.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uk.co.boots.practice.dto.PracticeDTO;
import uk.co.boots.prescriber.dto.PrescriberDTO;
import uk.co.boots.prescriber.entity.PrescriberByPracticeEntity;
import uk.co.boots.prescriber.respository.PrescriberByPracticeRepository;

@Service
public class PrescriberRepositoryService {

	@Autowired
	PrescriberByPracticeRepository prescriberRepo;
	@Autowired
	PrescriberEntityMappingService prescriberMapper;
		
	public PrescriberDTO addPractice (PrescriberDTO prescriber, PracticeDTO practice) {
		prescriberRepo.save(prescriberMapper.toPrescriberByPracticeEntity(prescriber, practice));
		if (prescriber.getPractice() == null)
			prescriber.setPractice(new ArrayList<PracticeDTO>());
		prescriber.getPractice().add(practice);
		return prescriber;
	}
	
	public PracticeDTO getPractice(UUID id) {
		List<PrescriberByPracticeEntity> prescriberList = prescriberRepo.findByPracticeId(id);
		return prescriberMapper.toPracticeDTOFromPrescriberByPracticeEntityList(prescriberList);
	}
	
	public void deletePrescriber(PrescriberDTO dto) {
		dto.getPractice().forEach(practice -> prescriberRepo.delete(prescriberMapper.toPrescriberByPracticeEntity(dto, practice)));
	}
}
