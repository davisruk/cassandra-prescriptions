package uk.co.boots;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import uk.co.boots.common.Address;
import uk.co.boots.patient.dto.PatientDTO;
import uk.co.boots.patient.service.PatientRepositoryService;
import uk.co.boots.practice.entity.PracticeByRegionEntity;
import uk.co.boots.practice.repository.PracticeByRegionRepository;
import uk.co.boots.prescriber.entity.PrescriberByPracticeEntity;
import uk.co.boots.prescriber.respository.PrescriberByPracticeRepository;
import uk.co.boots.prescriptions.entity.PrescriptionByDateEntity;
import uk.co.boots.prescriptions.repository.PrescriptionByPatientRepository;
import uk.co.boots.prescriptions.repository.PrescriptionByPrescriberRepository;
import uk.co.boots.prescriptions.repository.PrescriptionByStoreRepository;
import uk.co.boots.prescriptions.repository.PrescriptionRepository;
import uk.co.boots.prescriptions.service.PrescriptionMappingService;
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
	PracticeByRegionRepository practiceRepo;
	@Autowired
	PrescriberByPracticeRepository prescriberRepo;
	@Autowired
	PatientRepositoryService patientService;
	
	public static void main (final String args[]) {
		SpringApplication.run(Application.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		
		PracticeByRegionEntity practice1 = PracticeByRegionEntity.builder()
				.addressLine1("39 Linkfield Road")
				.practiceName("Charnwood Surgery")
				.postCode("LE12 7DJ")
				.addressLine2("Mountsorrel")
				.town("Loughborough")
				.region("Leicestershire")
				.country("UK")
				.id(UUID.randomUUID())
				.build();
		
		practiceRepo.save(practice1);
		
		PracticeByRegionEntity practice2 = PracticeByRegionEntity.builder()
				.addressLine1("86 Rothley Road")
				.practiceName("Alpine House Surgery")
				.postCode("LE12 7JU")
				.addressLine2("Mountsorrel")
				.town("Loughborough")
				.region("Leicestershire")
				.country("UK")
				.id(UUID.randomUUID())
				.build();
		
		practiceRepo.save(practice2);
		
		PrescriberByPracticeEntity prescriber1 = PrescriberByPracticeEntity.builder()
				.addressLine1(practice2.getAddressLine1())
				.practiceName(practice2.getAddressLine2())
				.postCode(practice2.getPostCode())
				.addressLine2(practice2.getAddressLine2())
				.town(practice2.getTown())
				.region(practice2.getRegion())
				.country(practice2.getCountry())
				.firstName("Dr Hannibal")
				.secondName("Lector")
				.practiceId(practice2.getId())
				.id(UUID.randomUUID())
				.build();

		prescriberRepo.save(prescriber1);
		
		PrescriberByPracticeEntity prescriber2 = PrescriberByPracticeEntity.builder()
				.addressLine1(practice1.getAddressLine1())
				.practiceName(practice1.getAddressLine2())
				.postCode(practice1.getPostCode())
				.addressLine2(practice1.getAddressLine2())
				.town(practice1.getTown())
				.region(practice1.getRegion())
				.country(practice1.getCountry())
				.firstName("Dr R K")
				.secondName("Hirani")
				.practiceId(practice1.getId())
				.id(UUID.randomUUID())
				.build();

		prescriberRepo.save(prescriber2);
		
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
				.build();

		patientService.save(p1);
		
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
				.build();

		patientService.save(p2);
		
		System.out.println("All Patients in Leicestershire");
		patientService.getAllPatientsForRegion("Leicestershire").forEach(System.out::println);

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
				.prescriberPracticeId(prescriber1.getPracticeId())
				.prescriberPracticeName(prescriber1.getPracticeName())
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
				.prescriberPracticeId(prescriber1.getPracticeId())
				.prescriberPracticeName(prescriber1.getPracticeName())
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
				.prescriberPracticeId(prescriber2.getPracticeId())
				.prescriberPracticeName(prescriber2.getPracticeName())
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
				.prescriberPracticeId(prescriber1.getPracticeId())
				.prescriberPracticeName(prescriber1.getPracticeName())
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
		prescriberRepo.delete(prescriber1);
		prescriberRepo.delete(prescriber2);
		practiceRepo.delete(practice1);
		practiceRepo.delete(practice2);
		storeRepo.delete(store2);
		storeRepo.delete(store1);
	}
}