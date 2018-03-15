package uk.co.boots.prescriber.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import uk.co.boots.common.Address;
import uk.co.boots.practice.dto.PracticeDTO;
import uk.co.boots.prescriber.dto.PrescriberDTO;
import uk.co.boots.prescriber.entity.PrescriberByPracticeEntity;

@Service
public class PrescriberEntityMappingService {
	public PrescriberByPracticeEntity toPrescriberByPracticeEntity (PrescriberDTO prescriberDTO, PracticeDTO practiceDTO) {
		return PrescriberByPracticeEntity.builder()
				.prescriberId(prescriberDTO.getId())
				.prescriberCodes(prescriberDTO.getCodes())
				.firstName(prescriberDTO.getFirstName())
				.secondName(prescriberDTO.getSecondName())
				.practiceId(practiceDTO.getId())
				.practiceName(practiceDTO.getPracticeName())
				.addressLine1(practiceDTO.getAddress().getAddressLine1())
				.addressLine2(practiceDTO.getAddress().getAddressLine2())
				.addressLine3(practiceDTO.getAddress().getAddressLine3())
				.town(practiceDTO.getAddress().getTown())
				.region(practiceDTO.getAddress().getRegion())
				.country(practiceDTO.getAddress().getCountry())
				.postCode(practiceDTO.getAddress().getPostCode())
				.practiceCodes(practiceDTO.getCodes())
				.build();
	}


	public PracticeDTO toPracticeDTOFromPrescriberByPracticeEntityList(List<PrescriberByPracticeEntity> prescribers) {
		
		PrescriberByPracticeEntity p = prescribers.get(0); 
		PracticeDTO practice = PracticeDTO.builder()
				.id(p.getPracticeId())
				.practiceName(p.getPracticeName())
				.address(Address.builder()
						.addressLine1(p.getAddressLine1())
						.addressLine2(p.getAddressLine2())
						.addressLine3(p.getAddressLine3())
						.country(p.getCountry())
						.postCode(p.getPostCode())
						.region(p.getRegion())
						.town(p.getTown())
						.build()
				)
				.codes(p.getPracticeCodes())
				.build();
		practice.setPrescribers(new ArrayList<PrescriberDTO>());
		prescribers.forEach(prescriber -> practice.getPrescribers().add(toPrescriberDTO(prescriber)));
		return practice;
		
	}

	public PrescriberDTO toPrescriberDTO (PrescriberByPracticeEntity entity) {
		return PrescriberDTO.builder()
				.id(entity.getPrescriberId())
				.firstName(entity.getFirstName())
				.secondName(entity.getSecondName())
				.codes(entity.getPrescriberCodes())
				.build();
	}
	public List<PrescriberByPracticeEntity> toPrescriberByPracticeEntity (List<PrescriberDTO> dtoList){
		// totally wrong - only here to get compilation success 
		return dtoList.stream().map(x -> toPrescriberByPracticeEntity(x, x.getPractice().get(0))).collect(Collectors.toList());
	}
	
	public List<PrescriberDTO> toPrescriberDTO (List<PrescriberByPracticeEntity> entityList){
		return entityList.stream().map(x -> toPrescriberDTO(x)).collect(Collectors.toList());
	}
}
