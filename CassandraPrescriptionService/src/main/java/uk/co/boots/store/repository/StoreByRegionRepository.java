package uk.co.boots.store.repository;

import java.util.List;

import org.springframework.data.cassandra.repository.CassandraRepository;

import uk.co.boots.store.entity.StoreByRegionEntity;

public interface StoreByRegionRepository extends CassandraRepository<StoreByRegionEntity, String> {
	public List<StoreByRegionEntity> findByRegion(String region);
}
