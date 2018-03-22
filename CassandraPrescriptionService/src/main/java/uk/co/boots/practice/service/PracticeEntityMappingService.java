package uk.co.boots.practice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uk.co.boots.common.Address;
import uk.co.boots.practice.dto.PracticeDTO;
import uk.co.boots.practice.entity.PracticeByPrescriberEntity;
import uk.co.boots.practice.entity.PracticeByRegionEntity;
import uk.co.boots.prescriber.dto.PrescriberDTO;
import uk.co.boots.prescriber.service.PrescriberEntityMappingService;
import uk.co.boots.prescription.entity.PrescriptionByDateEntity;

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
	
	public PracticeDTO toPracticeDTO (PracticeByPrescriberEntity entity) {
		return PracticeDTO.builder()
				.id(entity.getPracticeId())
				.practiceName(entity.getPracticeName())
				.codes(entity.getPracticeCodes())
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

	public PrescriberDTO toPrescriberDTOFromPracticeByPrescriberEntityList(List<PracticeByPrescriberEntity> practices) {
		
		PracticeByPrescriberEntity p = practices.get(0); 
		PrescriberDTO prescriber = PrescriberDTO.builder()
				.id(p.getPrescriberId())
				.firstName(p.getFirstName())
				.secondName(p.getSecondName())
				.codes(p.getPrescriberCodes())
				.build();
		prescriber.setPractice(new ArrayList<PracticeDTO>());
		practices.forEach(practice -> prescriber.getPractice().add(toPracticeDTO(practice)));
		return prescriber;
	}
	
	public PracticeByPrescriberEntity toPracticeByPrescriberEntity (PracticeDTO practiceDTO, PrescriberDTO prescriberDTO) {
		return PracticeByPrescriberEntity.builder()
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
	
	public PracticeDTO toDTOFromPrescriptionByDateEntity(PrescriptionByDateEntity pbde, int depth) {
		if (depth == 0)
			return null;
		ArrayList<PrescriberDTO> prbl = new ArrayList<PrescriberDTO>() {
			{
				add(prescriberMapper.toPrescriberDTO(pbde, depth - 1));
			}
		};
		return PracticeDTO.builder()
			.practiceName(pbde.getPrescriberPracticeName())
			.id(pbde.getPrescriberPracticeId())
			.prescribers(prbl)
			.build();

	}
}
