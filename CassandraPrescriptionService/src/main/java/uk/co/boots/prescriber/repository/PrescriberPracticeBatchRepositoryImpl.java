package uk.co.boots.prescriber.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.CassandraBatchOperations;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.data.cassandra.repository.query.CassandraEntityInformation;
import org.springframework.data.cassandra.repository.support.SimpleCassandraRepository;

import uk.co.boots.practice.repository.PracticeByPrescriberRepository;
import uk.co.boots.prescriber.entity.PrescriberByPracticeEntity;
import uk.co.boots.prescriber.service.PrescriberEntityMappingService;

public class PrescriberPracticeBatchRepositoryImpl extends SimpleCassandraRepository<PrescriberByPracticeEntity, UUID> implements PrescriberPracticeBatchRepository {

	@Autowired
	CassandraTemplate cassandraTemplate;
	@Autowired
	PrescriberEntityMappingService mapper;
	@Autowired
	PracticeByPrescriberRepository practiceRepo;
	
	public PrescriberPracticeBatchRepositoryImpl(CassandraEntityInformation<PrescriberByPracticeEntity, UUID> metadata,
			CassandraOperations operations) {
		super(metadata, operations);
	}

	@Override
	public <S extends PrescriberByPracticeEntity> S insert(S entity) {
		CassandraBatchOperations batchOps = cassandraTemplate.batchOps();
		insertPracticeByPrescription(entity, batchOps);
		batchOps.insert(entity);
		batchOps.execute();
		return entity;
	}
	
	private void insertPracticeByPrescription(PrescriberByPracticeEntity prescriber, CassandraBatchOperations batchOps) {
			batchOps.insert(mapper.toPracticeByPrescriberEntity(prescriber));
	}

	@Override
	public <S extends PrescriberByPracticeEntity> List<S> insert(Iterable<S> entities) {
		List<S> result = new ArrayList<>();
		for (S prescriber : entities) {
			result.add(insert(prescriber));
		}
		return result;
	}

	@Override
	public <S extends PrescriberByPracticeEntity> S save(S entity) {
		return insert(entity);
	}

	@Override
	public void delete (PrescriberByPracticeEntity p) {
		CassandraBatchOperations batchOps = cassandraTemplate.batchOps();
		deleteByPractice(p, batchOps);
		batchOps.delete(p);
		batchOps.execute();
	}

	private void deleteByPractice(PrescriberByPracticeEntity p, CassandraBatchOperations batchOps) {
		batchOps.delete(practiceRepo.findByPrescriberIdAndPracticeNameAndPracticeId(p.getPrescriberId(), p.getPracticeName(), p.getPracticeId()));
	}
	
	@Override
	public void deleteById(UUID id) {
		findById(id).ifPresent(this::delete);
	}
	
	@Override
	public void deleteAll(Iterable<? extends PrescriberByPracticeEntity> prescribers) {
		prescribers.forEach(this::delete);
	}
	
	@Override
	public void deleteAll() {
		deleteAll(findAll());
	}

}
