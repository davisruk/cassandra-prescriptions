package uk.co.boots.patient.dto;

import java.time.LocalDate;
import java.util.UUID;

import lombok.Builder;
import lombok.Data;
import uk.co.boots.common.Address;

@Data
@Builder
public class PatientDTO {
	private UUID id;
	private LocalDate lastInteraction;
	private String secondName;
	private String firstName;
	private Address address;
}
