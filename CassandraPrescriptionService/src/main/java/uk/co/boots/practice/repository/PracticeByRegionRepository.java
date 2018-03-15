package uk.co.boots.practice.repository;

import java.util.List;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.query.Param;

import uk.co.boots.practice.entity.PracticeByRegionEntity;

public interface PracticeByRegionRepository extends CassandraRepository<PracticeByRegionEntity, String> {
	public List<PracticeByRegionEntity> findAllByRegion(String region);
	
	@Query("select * from practice_by_region where practice_region=:region and practice_code[:codeType]=:codeValue")
	public PracticeByRegionEntity findByRegionAndCode(@Param("region") String region, @Param("codeType") String codeType, @Param("codeValue")String codeValue);
	
}
