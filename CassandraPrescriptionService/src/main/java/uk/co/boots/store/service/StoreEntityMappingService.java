package uk.co.boots.store.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import uk.co.boots.common.Address;
import uk.co.boots.patient.dto.PatientDTO;
import uk.co.boots.patient.entity.PatientByRegionEntity;
import uk.co.boots.prescription.entity.PrescriptionByDateEntity;
import uk.co.boots.store.dto.StoreDTO;
import uk.co.boots.store.entity.StoreByRegionEntity;

@Service
public class StoreEntityMappingService {
	public StoreDTO toStoreDTO (PrescriptionByDateEntity pbde) {
		return StoreDTO.builder()
				.id(pbde.getStoreId())
				.storeName(pbde.getStoreName())
				.build();
	}
	
	public List<StoreDTO> toStoreDTO (List<StoreByRegionEntity> entityList){
		return entityList.stream().map(x -> toStoreDTO(x)).collect(Collectors.toList());
	}

	public StoreDTO toStoreDTO (StoreByRegionEntity s) {
		return StoreDTO.builder()
				.id(s.getStoreId())
				.storeName(s.getStoreName())
				.address(Address.builder()
						.addressLine1(s.getAddressLine1())
						.addressLine2(s.getAddressLine2())
						.addressLine3(s.getAddressLine3())
						.country(s.getCountry())
						.postCode(s.getPostCode())
						.region(s.getRegion())
						.town(s.getTown())
						.build()
				)
				.build();
	}

	public List<StoreByRegionEntity> toStoreByRegionEntity (List<StoreDTO> dtoList){
		return dtoList.stream().map(x -> toStoreByRegionEntity(x)).collect(Collectors.toList());
	}

	public StoreByRegionEntity toStoreByRegionEntity (StoreDTO s) {
		return StoreByRegionEntity.builder()
				.storeId(s.getId())
				.storeName(s.getStoreName())
				.addressLine1(s.getAddress().getAddressLine1())
				.addressLine2(s.getAddress().getAddressLine2())
				.addressLine3(s.getAddress().getAddressLine3())
				.country(s.getAddress().getCountry())
				.postCode(s.getAddress().getPostCode())
				.region(s.getAddress().getRegion())
				.town(s.getAddress().getTown())
				.build();
	}
	
}
