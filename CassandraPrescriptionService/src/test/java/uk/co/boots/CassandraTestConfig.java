package uk.co.boots;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import uk.co.boots.practice.service.PracticeEntityMappingService;
import uk.co.boots.practice.service.PracticeRepositoryService;

@Configuration
@Profile("test")
public class CassandraTestConfig {

	@Bean
	public PracticeRepositoryService practiceRepoService() {
		return new PracticeRepositoryService();
	}
	
	@Bean
	public PracticeEntityMappingService practiceEntityMappingService() {
		return new PracticeEntityMappingService();
	}

}
