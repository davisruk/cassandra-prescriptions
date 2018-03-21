package uk.co.boots.prescriber.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uk.co.boots.practice.dto.PracticeDTO;
import uk.co.boots.practice.entity.PracticeByPrescriberEntity;
import uk.co.boots.practice.repository.PracticeByPrescriberRepository;
import uk.co.boots.practice.service.PracticeEntityMappingService;
import uk.co.boots.prescriber.dto.PrescriberDTO;
import uk.co.boots.prescriber.repository.PrescriberByPracticeRepository;
import uk.co.boots.prescriber.repository.PrescriberPracticeBatchRepository;

@Service
public class PrescriberRepositoryService {

	@Autowired
	PrescriberByPracticeRepository prescriberRepo;
	@Autowired
	PrescriberEntityMappingService prescriberMapper;
	@Autowired
	PracticeByPrescriberRepository practiceRepo;
	@Autowired
	PracticeEntityMappingService practiceMapper;
	@Autowired
	PrescriberPracticeBatchRepository prescriberPracticeRepo;


	public PrescriberDTO addPractice (PrescriberDTO prescriber, PracticeDTO practice) {
		prescriberPracticeRepo.save(prescriberMapper.toPrescriberByPracticeEntity(prescriber, practice));
		if (prescriber.getPractice() == null)
			prescriber.setPractice(new ArrayList<PracticeDTO>());
		prescriber.getPractice().add(practice);
		return prescriber;
	}
	
	public void deletePrescriber(PrescriberDTO dto) {
		dto.getPractice().forEach(practice -> prescriberPracticeRepo.delete(prescriberMapper.toPrescriberByPracticeEntity(dto, practice)));
	}
	
	public PrescriberDTO getPrescriber(UUID id) {
		List<PracticeByPrescriberEntity> practiceList = practiceRepo.findByPrescriberId(id);
		return practiceMapper.toPrescriberDTOFromPracticeByPrescriberEntityList(practiceList);
	}
}
