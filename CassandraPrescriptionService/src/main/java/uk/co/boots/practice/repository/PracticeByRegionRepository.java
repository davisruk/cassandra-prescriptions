package uk.co.boots.practice.repository;

import org.springframework.data.cassandra.repository.CassandraRepository;

import uk.co.boots.practice.entity.PracticeByRegionEntity;

public interface PracticeByRegionRepository extends CassandraRepository<PracticeByRegionEntity, String> {

}
