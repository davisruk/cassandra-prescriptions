package uk.co.boots.patient.dto;

import java.time.LocalDate;
import java.util.Map;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uk.co.boots.common.Address;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PatientDTO {
	private UUID id;
	private LocalDate lastInteraction;
	private String secondName;
	private String firstName;
	private Address address;
	private Map<String, String> codes;	
}
