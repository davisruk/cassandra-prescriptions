package uk.co.boots.patient.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import uk.co.boots.common.Address;
import uk.co.boots.patient.dto.PatientDTO;
import uk.co.boots.patient.entity.PatientByRegionEntity;

@Service
public class PatientEntityMappingService {

	public PatientByRegionEntity toPatientByRegionEntity (PatientDTO dto) {
		return PatientByRegionEntity.builder()
				.id(dto.getId())
				.firstName(dto.getFirstName())
				.secondName(dto.getSecondName())
				.lastInteraction(dto.getLastInteraction())
				.addressLine1(dto.getAddress().getAddressLine1())
				.addressLine2(dto.getAddress().getAddressLine2())
				.addressLine3(dto.getAddress().getAddressLine3())
				.region(dto.getAddress().getRegion())
				.town(dto.getAddress().getTown())
				.postCode(dto.getAddress().getPostCode())
				.country(dto.getAddress().getCountry())
				.build();
	}
	

	public PatientDTO toPatientDTO (PatientByRegionEntity entity) {
		return PatientDTO.builder()
				.id(entity.getId())
				.firstName(entity.getFirstName())
				.secondName(entity.getSecondName())
				.lastInteraction(entity.getLastInteraction())
				.address(Address.builder()
						.addressLine1(entity.getAddressLine1())
						.addressLine2(entity.getAddressLine2())
						.addressLine3(entity.getAddressLine3())
						.postCode(entity.getPostCode())
						.region(entity.getRegion())
						.town(entity.getTown())
						.country(entity.getCountry())
						.build())
				.build();
	}
	
	public List<PatientByRegionEntity> toPatientByRegionEntity (List<PatientDTO> dtoList){
		return dtoList.stream().map(x -> toPatientByRegionEntity(x)).collect(Collectors.toList());
	}
	
	public List<PatientDTO> toPatientDTO (List<PatientByRegionEntity> dtoList){
		return dtoList.stream().map(x -> toPatientDTO(x)).collect(Collectors.toList());
	}
}
