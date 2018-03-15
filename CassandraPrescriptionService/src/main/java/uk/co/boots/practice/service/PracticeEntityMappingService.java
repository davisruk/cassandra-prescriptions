package uk.co.boots.practice.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uk.co.boots.common.Address;
import uk.co.boots.practice.dto.PracticeDTO;
import uk.co.boots.practice.entity.PracticeByRegionEntity;
import uk.co.boots.prescriber.dto.PrescriberDTO;
import uk.co.boots.prescriber.service.PrescriberEntityMappingService;

@Service
public class PracticeEntityMappingService {

	@Autowired
	PrescriberEntityMappingService prescriberMapper;
	
	public PracticeByRegionEntity toPracticeByRegionEntity (PracticeDTO dto) {
		return PracticeByRegionEntity.builder()
				.id(dto.getId())
				.practiceName(dto.getPracticeName())
				.codes(dto.getCodes())
				.addressLine1(dto.getAddress().getAddressLine1())
				.addressLine2(dto.getAddress().getAddressLine2())
				.addressLine3(dto.getAddress().getAddressLine3())
				.town(dto.getAddress().getTown())
				.region(dto.getAddress().getRegion())
				.country(dto.getAddress().getCountry())
				.postCode(dto.getAddress().getPostCode())
				.build();
	}

	public PracticeDTO toPracticeDTO (PracticeByRegionEntity entity) {
		return PracticeDTO.builder()
				.id(entity.getId())
				.practiceName(entity.getPracticeName())
				.codes(entity.getCodes())
				.address(Address.builder()
						.addressLine1(entity.getAddressLine1())
						.addressLine2(entity.getAddressLine2())
						.addressLine3(entity.getAddressLine3())
						.town(entity.getTown())
						.region(entity.getRegion())
						.country(entity.getCountry())
						.postCode(entity.getPostCode())
						.build())
				.build();
	}
	public List<PracticeByRegionEntity> toPatientByRegionEntity (List<PracticeDTO> dtoList){
		return dtoList.stream().map(x -> toPracticeByRegionEntity(x)).collect(Collectors.toList());
	}
	
	public List<PracticeDTO> toPracticeDTO (List<PracticeByRegionEntity> entityList){
		return entityList.stream().map(x -> toPracticeDTO(x)).collect(Collectors.toList());
	}
 
}
