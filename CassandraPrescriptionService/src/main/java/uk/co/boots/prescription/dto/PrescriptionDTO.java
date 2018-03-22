package uk.co.boots.prescription.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.Builder;
import lombok.Data;
import uk.co.boots.patient.dto.PatientDTO;
import uk.co.boots.prescriber.dto.PrescriberDTO;
import uk.co.boots.store.dto.StoreDTO;

@Data
@Builder
public class PrescriptionDTO {
	UUID id;
	PatientDTO patient;
	StoreDTO store;
	PrescriberDTO prescriber;
	LocalDateTime issueDate;
}
