package uk.co.boots;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import uk.co.boots.common.Address;
import uk.co.boots.patient.dto.PatientDTO;
import uk.co.boots.patient.service.PatientRepositoryService;
import uk.co.boots.practice.dto.PracticeDTO;
import uk.co.boots.practice.service.PracticeRepositoryService;
import uk.co.boots.prescriber.dto.PrescriberDTO;
import uk.co.boots.prescriber.service.PrescriberRepositoryService;
import uk.co.boots.prescription.entity.PrescriptionByDateEntity;
import uk.co.boots.prescription.repository.PrescriptionByPatientRepository;
import uk.co.boots.prescription.repository.PrescriptionByPrescriberRepository;
import uk.co.boots.prescription.repository.PrescriptionByStoreRepository;
import uk.co.boots.prescription.repository.PrescriptionRepository;
import uk.co.boots.prescription.service.PrescriptionMappingService;
import uk.co.boots.store.entity.StoreByRegionEntity;
import uk.co.boots.store.repository.StoreByRegionRepository;

@SpringBootApplication
public class Application implements CommandLineRunner{

	@Autowired
	PrescriptionRepository pr;
	@Autowired
	PrescriptionByStoreRepository byStoreRepo;
	@Autowired
	PrescriptionByPatientRepository byPatientRepo;
	@Autowired
	PrescriptionByPrescriberRepository byPrescriberRepo;
	@Autowired
	PrescriptionMappingService mapper;
	@Autowired
	StoreByRegionRepository storeRepo;
	@Autowired
	PracticeRepositoryService practiceRepoService;
	@Autowired
	PatientRepositoryService patientService;
	@Autowired
	PrescriberRepositoryService prescriberRepoService;
	
	public static void main (final String args[]) {
		SpringApplication.run(Application.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		
		Map<String, String> practice1Codes = new HashMap<String, String>();
		practice1Codes.put("GMC", "12345678");
		practice1Codes.put("ODS", "AB897982");
		practice1Codes.put("SDS", "938035766436");

		PracticeDTO practice1 = PracticeDTO.builder()
				.id(UUID.randomUUID())
				.practiceName("Charnwood Surgery")
				.codes(practice1Codes)
				.address(Address.builder()
						.addressLine1("39 Linkfield Road")
						.postCode("LE12 7DJ")
						.addressLine2("Mountsorrel")
						.town("Loughborough")
						.region("Leicestershire")
						.country("UK")
						.build())
				.build();
		
		practiceRepoService.save(practice1);
		
		Map<String, String> practice2Codes = new HashMap<String, String>();
		practice2Codes.put("GMC", "98324739");
		practice2Codes.put("ODS", "JH543543");
		practice2Codes.put("SDS", "437637387856");

		PracticeDTO practice2 = PracticeDTO.builder()
				.id(UUID.randomUUID())
				.practiceName("Alpine House Surgery")
				.codes(practice2Codes)
				.address(Address.builder()
						.addressLine1("86 Rothley Road")
						.postCode("LE12 7JU")
						.addressLine2("Mountsorrel")
						.town("Loughborough")
						.region("Leicestershire")
						.country("UK")
						.build())
				.build();

		practiceRepoService.save(practice2);
		
		System.out.println("Practices in Leicestershire with ODS code " + practice1Codes.get("ODS"));
		System.out.println(practiceRepoService.getPracticeForRegionAndCode("Leicestershire", "ODS", practice1Codes.get("ODS")));
		
		Map<String, String> prescriber1Codes = new HashMap<String, String>();
		prescriber1Codes.put("GMC", "984032983");
		prescriber1Codes.put("ODS", "KL9083409");
		prescriber1Codes.put("SDS", "348208430298");

		PrescriberDTO prescriber1 = PrescriberDTO.builder()
					.firstName("Dr Hannibal")
					.secondName("Lector")
					.id(UUID.randomUUID())
					.codes(prescriber1Codes)
					.build();
		prescriberRepoService.addPractice(prescriber1, practice2);
		prescriberRepoService.addPractice(prescriber1, practice1);		
		
		Map<String, String> prescriber2Codes = new HashMap<String, String>();
		prescriber2Codes.put("GMC", "0980982340");
		prescriber2Codes.put("ODS", "LH03990809");
		prescriber2Codes.put("SDS", "98098340928");

		PrescriberDTO prescriber2 = PrescriberDTO.builder()
				.firstName("Dr R K")
				.secondName("Hirani")
				.id(UUID.randomUUID())
				.codes(prescriber2Codes)
				.build();

		prescriberRepoService.addPractice(prescriber2, practice1);
		
		System.out.println("All Prescribers in " + practice1.getPracticeName());
		practiceRepoService.getPractice(practice1.getId()).getPrescribers().forEach(System.out::println);
		
		System.out.println("All Prescribers in " + practice2.getPracticeName());
		practiceRepoService.getPractice(practice2.getId()).getPrescribers().forEach(System.out::println);

		System.out.println("All Practices for " + prescriber1.getFirstName() + " " + prescriber1.getSecondName());
		prescriberRepoService.getPrescriber(prescriber1.getId()).getPractice().forEach(System.out::println);
		
		System.out.println("All Practices for " + prescriber2.getFirstName() + " " + prescriber2.getSecondName());
		prescriberRepoService.getPrescriber(prescriber2.getId()).getPractice().forEach(System.out::println);

		Map<String, String> p1Codes = new HashMap<String, String>();
		p1Codes.put("NI", "NW328600C");
		p1Codes.put("NHS", "A6709JU12");
	
		PatientDTO p1 = PatientDTO.builder()
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
				.codes(p1Codes)
				.build();

		patientService.save(p1);
		
		Map<String, String> p2Codes = new HashMap<String, String>();
		p2Codes.put("NI", "AA758943L");
		p2Codes.put("NHS", "A93L0200");

		PatientDTO p2 = PatientDTO.builder()
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
				.codes(p2Codes)
				.build();

		patientService.save(p2);
		
		System.out.println("All Patients in Leicestershire");
		patientService.getAllPatientsForRegion("Leicestershire").forEach(System.out::println);

		System.out.println("Patient in Leicestershire with NI code " + p1Codes.get("NI"));
		System.out.println(patientService.getPatientForRegionAndCode("Leicestershire", "NI", p1Codes.get("NI")));
		
		StoreByRegionEntity store1 = StoreByRegionEntity.builder()
				.storeId(UUID.randomUUID())
				.region("Leicestershire")
				.storeName("High Cross")
				.addressLine1("38-39 Shires Lane")
				.town("Leicester")
				.postCode("LE1 4FQ")
				.country("England")
				.build();
		storeRepo.save(store1);
		
		StoreByRegionEntity store2  = StoreByRegionEntity.builder()
				.storeId(UUID.randomUUID())
				.region("Nottinghamshire")
				.storeName("Riverside")
				.addressLine1("Riverside Retail Park")
				.town("Nottingham")
				.postCode("NG2 1RU")
				.country("England")
				.build();

		storeRepo.save(store2);
		
		PrescriptionByDateEntity e1 = PrescriptionByDateEntity.builder()
				.id(UUID.randomUUID())
				.patientId(p1.getId())
				.patientFirstName(p1.getFirstName())
				.patientSecondName(p1.getSecondName())
				.storeId(store1.getStoreId())
				.storeName(store1.getStoreName())
				.prescriberId(prescriber1.getId())
				.prescriberFirstName(prescriber1.getFirstName())
				.prescriberSecondName(prescriber1.getSecondName())
				.prescriberPracticeId(prescriber1.getPractice().get(0).getId())
				.prescriberPracticeName(prescriber1.getPractice().get(0).getPracticeName())
				.prescriptionDate(LocalDateTime.now())
				.build();
		
		PrescriptionByDateEntity e2 = PrescriptionByDateEntity.builder()
				.id(UUID.randomUUID())
				.patientId(p1.getId())
				.patientFirstName(p1.getFirstName())
				.patientSecondName(p1.getSecondName())
				.storeId(store2.getStoreId())
				.storeName(store2.getStoreName())
				.prescriberId(prescriber1.getId())
				.prescriberFirstName(prescriber1.getFirstName())
				.prescriberSecondName(prescriber1.getSecondName())
				.prescriberPracticeId(prescriber1.getPractice().get(1).getId())
				.prescriberPracticeName(prescriber1.getPractice().get(1).getPracticeName())
				.prescriptionDate(LocalDateTime.of(2013, 8, 30, 15, 30))
				.build();

		PrescriptionByDateEntity e3 = PrescriptionByDateEntity.builder()
				.id(UUID.randomUUID())
				.patientId(p2.getId())
				.patientFirstName(p2.getFirstName())
				.patientSecondName(p2.getSecondName())
				.storeId(store2.getStoreId())
				.storeName(store2.getStoreName())
				.prescriberId(prescriber2.getId())
				.prescriberFirstName(prescriber2.getFirstName())
				.prescriberSecondName(prescriber2.getSecondName())
				.prescriberPracticeId(prescriber2.getPractice().get(0).getId())
				.prescriberPracticeName(prescriber2.getPractice().get(0).getPracticeName())
				.prescriptionDate(LocalDateTime.now())
				.build();
		
		PrescriptionByDateEntity e4 = PrescriptionByDateEntity.builder()
				.id(UUID.randomUUID())
				.patientId(p2.getId())
				.patientFirstName(p2.getFirstName())
				.patientSecondName(p2.getSecondName())
				.storeId(store2.getStoreId())
				.storeName(store2.getStoreName())
				.prescriberId(prescriber1.getId())
				.prescriberFirstName(prescriber1.getFirstName())
				.prescriberSecondName(prescriber1.getSecondName())
				.prescriberPracticeId(prescriber1.getPractice().get(0).getId())
				.prescriberPracticeName(prescriber1.getPractice().get(0).getPracticeName())
				.prescriptionDate(LocalDateTime.now())
				.build();
		
		pr.insert(e1);
		pr.insert(e2);
		pr.insert(e3);
		pr.insert(e4);

		System.out.println("All Prescriptions For" + store2.getStoreName());
		byStoreRepo.findByStoreId(store2.getStoreId()).forEach(System.out::println);

		System.out.println("All Prescriptions For" + store1.getStoreName());
		byStoreRepo.findByStoreId(store1.getStoreId()).forEach(System.out::println);

		System.out.println("All Prescriptions For " +  store2.getStoreName() + " Older than today");
		byStoreRepo.findByStoreIdAndPrescriptionDateLessThan(store2.getStoreId(), LocalDateTime.of(LocalDate.now().minusDays(1), LocalTime.now()))
							.forEach(System.out::println);
		pr.delete(e1);
		pr.delete(e2);
		pr.delete(e3);
		pr.delete(e4);
		patientService.delete(p1);
		patientService.delete(p2);
		practiceRepoService.delete(practice1);
		practiceRepoService.delete(practice2);
		storeRepo.delete(store2);
		storeRepo.delete(store1);
	}
}