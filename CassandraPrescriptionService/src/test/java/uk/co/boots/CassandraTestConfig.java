package uk.co.boots;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

import lombok.Getter;
import uk.co.boots.common.Address;
import uk.co.boots.patient.dto.PatientDTO;
import uk.co.boots.patient.service.PatientEntityMappingService;
import uk.co.boots.patient.service.PatientRepositoryService;
import uk.co.boots.practice.dto.PracticeDTO;
import uk.co.boots.practice.service.PracticeEntityMappingService;
import uk.co.boots.practice.service.PracticeRepositoryService;
import uk.co.boots.prescriber.dto.PrescriberDTO;
import uk.co.boots.prescriber.service.PrescriberEntityMappingService;
import uk.co.boots.prescriber.service.PrescriberRepositoryService;
import uk.co.boots.prescription.dto.PrescriptionDTO;
import uk.co.boots.prescription.entity.PrescriptionByDateEntity;
import uk.co.boots.prescription.service.PrescriptionMappingService;
import uk.co.boots.prescription.service.PrescriptionRepositoryService;
import uk.co.boots.store.dto.StoreDTO;
import uk.co.boots.store.service.StoreEntityMappingService;
import uk.co.boots.store.service.StoreRepositoryService;

@Getter
@Configuration
@Profile("test")
@EnableCassandraRepositories
@PropertySource(value={"classpath:application-test.properties"}, ignoreResourceNotFound = true)
public class CassandraTestConfig extends AbstractCassandraConfiguration{

	// Cassandra Specific Config
	
	@Value("${cassandra.port}")
	private int port;
	
	@Value("${cassandra.keySpace}")
	private String keyspaceName;
	
	@Value("${cassandra.basePackages}")
	private String basePackages;

	@Value("${cassandra.contactPoints}")
	private String contactPoints;

	// Repository and Service Beans
	@Bean
	public PracticeRepositoryService practiceRepoService() {
		return new PracticeRepositoryService();
	}
	
	@Bean
	public PracticeEntityMappingService practiceEntityMappingService() {
		return new PracticeEntityMappingService();
	}

	@Bean
	public PrescriberEntityMappingService prescriberEntityMappingService() {
		return new PrescriberEntityMappingService();
	}

	@Bean
	public PrescriptionMappingService prescriptionMappingService() {
		return new PrescriptionMappingService();
	}
	
	@Bean
	public PrescriberRepositoryService prescriberRepoService() {
		return new PrescriberRepositoryService();
	}

	@Bean
	public PatientRepositoryService patientService() {
		return new PatientRepositoryService();
	}

	@Bean
	public PatientEntityMappingService patientMapper() {
		return new PatientEntityMappingService();
	}

	@Bean 
	public PrescriptionRepositoryService prescriptionRepoService() {
		return new PrescriptionRepositoryService();
	}
	
	@Bean 
	public StoreEntityMappingService storeMapper() {
		return new StoreEntityMappingService();
	}

	@Bean 
	public StoreRepositoryService storeRepoServiceMapper() {
		return new StoreRepositoryService();
	}
	
	// Test Data
	@Bean 
	public PracticeDTO practice1() {
		return PracticeDTO.builder()
				.id(UUID.randomUUID())
				.practiceName("Charnwood Surgery")
				.codes(new HashMap<String, String>(){
						{
							put("GMC", "12345678");
							put("ODS", "AB897982");
							put("SDS", "938035766436");
						}
					}
				)
				.address(Address.builder()
						.addressLine1("39 Linkfield Road")
						.postCode("LE12 7DJ")
						.addressLine2("Mountsorrel")
						.town("Loughborough")
						.region("Leicestershire")
						.country("UK")
						.build())
				.build();
	}
	
	@Bean 
	public PracticeDTO practice2() {
		return PracticeDTO.builder()
			.id(UUID.randomUUID())
			.practiceName("Alpine House Surgery")
			.codes(new HashMap<String, String>(){
					{
						put("GMC", "98324739");
						put("ODS", "JH543543");
						put("SDS", "437637387856");
					}
				}
			)
			.address(Address.builder()
					.addressLine1("86 Rothley Road")
					.postCode("LE12 7JU")
					.addressLine2("Mountsorrel")
					.town("Loughborough")
					.region("Leicestershire")
					.country("UK")
					.build())
			.build();
	}

	@Bean
	public PrescriberDTO prescriber1() {
		return PrescriberDTO.builder()
			.firstName("Dr Hannibal")
			.secondName("Lector")
			.id(UUID.randomUUID())
			.codes(new HashMap<String, String>(){
					{
						put("GMC", "874834573");
						put("ODS", "DF7876876");
						put("SDS", "784579060890");
					}
				}
			)
			.build();
	}
	
	@Bean	
	public PrescriberDTO prescriber2() {
		return PrescriberDTO.builder()
			.firstName("Dr R K")
			.secondName("Hirani")
			.id(UUID.randomUUID())
			.codes(new HashMap<String, String>(){
					{
						put("GMC", "0980982340");
						put("ODS", "LH03990809");
						put("SDS", "98098340928");
					}
				}
			)
			.build();
	}
	
	@Bean
	public StoreDTO store1() {
		return StoreDTO.builder()
				.id(UUID.randomUUID())
				.storeName("High Cross")
				.address(Address.builder()
						.addressLine1("38-39 Shires Lane")
						.country("England")
						.postCode("LE1 4FQ")
						.region("Leicestershire")
						.town("Leicester")
						.build()
				)
				.build();
		
	}

	@Bean
	public StoreDTO store2() {
		return StoreDTO.builder()
				.id(UUID.randomUUID())
				.storeName("Riverside")
				.address(Address.builder()
						.addressLine1("Riverside Retail Park")
						.country("England")
						.postCode("NG2 1RU")
						.region("Nottinghamshire")
						.town("Nottingham")
						.build()
				)
				.build();
				
	}
	
	@Bean 
	public PatientDTO patient1() {
		return PatientDTO.builder()
			.id(UUID.randomUUID())
			.firstName("Richard")
			.secondName("Davis")
			.lastInteraction(LocalDate.of(2016, 03, 15))
			.address(Address.builder()
					.addressLine1("8 Pott Acre")
					.addressLine2("Rothley")
					.country("UK")
					.region("Leicestershire")
					.town("Leicester")
					.postCode("LE7 7LT")
					.build()
			)
			.codes(new HashMap<String, String>(){
					{
						put("NI", "NW328600C");
						put("NHS", "A6709JU12");
					}
				}
			)
			.build();
	}

	@Bean 
	public PatientDTO patient2() {
		return PatientDTO.builder()
			.id(UUID.randomUUID())
			.firstName("Merle")
			.secondName("Davis")
			.lastInteraction(LocalDate.of(2017, 07, 30))
			.address(Address.builder()
					.addressLine1("8 Pott Acre")
					.addressLine2("Rothley")
					.country("UK")
					.region("Leicestershire")
					.town("Leicester")
					.postCode("LE7 7LT")
					.build()
			)
			.codes(new HashMap<String, String>(){
					{
						put("NI", "AA758943L");
						put("NHS", "A93L0200");
					}
				}
			)
			.build();
	}
	
	@Bean
	public PrescriptionDTO prescriptionDTO1 () {
		PatientDTO p1 = patient1();
		StoreDTO store1 = store1();
		PrescriberDTO prescriber1 = prescriber1();
		return PrescriptionDTO.builder()
			.id(UUID.randomUUID())
			.patient(p1)
			.prescriber(prescriber1)
			.store(store1)
			.issueDate(LocalDateTime.now())
			// no practice details - must be set using the Repository Service addPractice method
			.build();
	}
	
	@Bean
	public PrescriptionDTO prescriptionDTO2 () {
		PatientDTO p = patient1();
		StoreDTO s = store2();
		PrescriberDTO prescriber = prescriber1();
		return PrescriptionDTO.builder()
			.id(UUID.randomUUID())
			.patient(p)
			.prescriber(prescriber)
			.store(s)
			.issueDate(LocalDateTime.of(2013, 8, 30, 15, 30))
			// no practice details - must be set using the Repository Service addPractice method
			.build();
	}

	@Bean
	public PrescriptionDTO prescriptionDTO3 () {
		PatientDTO p = patient2();
		StoreDTO s = store2();
		PrescriberDTO prescriber = prescriber2();
		return PrescriptionDTO.builder()
			.id(UUID.randomUUID())
			.patient(p)
			.prescriber(prescriber)
			.store(s)
			.issueDate(LocalDateTime.now())
			// no practice details - must be set using the Repository Service addPractice method
			.build();
	}

	@Bean
	public PrescriptionDTO prescriptionDTO4 () {
		PatientDTO p = patient2();
		StoreDTO s = store2();
		PrescriberDTO prescriber = prescriber1();
		return PrescriptionDTO.builder()
			.id(UUID.randomUUID())
			.patient(p)
			.prescriber(prescriber)
			.store(s)
			.issueDate(LocalDateTime.now())
			// no practice details - must be set using the Repository Service addPractice method
			.build();
	}
}
