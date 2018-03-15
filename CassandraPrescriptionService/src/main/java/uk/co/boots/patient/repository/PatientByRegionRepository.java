package uk.co.boots.patient.repository;

import java.util.List;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.query.Param;

import uk.co.boots.patient.entity.PatientByRegionEntity;

public interface PatientByRegionRepository extends CassandraRepository<PatientByRegionEntity, String> {
	public List<PatientByRegionEntity> findAllByRegion(String region);

	@Query("select * from patient_by_region where patient_region=:region and patient_code[:codeType]=:codeValue")
	public PatientByRegionEntity findByRegionAndCode(@Param("region") String region, @Param("codeType") String codeType, @Param("codeValue")String codeValue);
}
