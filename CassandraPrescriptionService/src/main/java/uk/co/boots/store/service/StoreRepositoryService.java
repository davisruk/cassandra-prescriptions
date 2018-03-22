package uk.co.boots.store.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uk.co.boots.store.dto.StoreDTO;
import uk.co.boots.store.repository.StoreByRegionRepository;

@Service
public class StoreRepositoryService {
	@Autowired
	StoreEntityMappingService storeMapper;
	@Autowired
	StoreByRegionRepository sbrRepo;
	
	public StoreDTO save (StoreDTO store) {
		return storeMapper.toStoreDTO(sbrRepo.save(storeMapper.toStoreByRegionEntity(store)));
	}
	
	public void delete (StoreDTO store) {
		sbrRepo.delete(storeMapper.toStoreByRegionEntity(store));
	}
	
	public List<StoreDTO> getByRegion (String region) {
		return storeMapper.toStoreDTO(sbrRepo.findByRegion(region));
	}

	
}
