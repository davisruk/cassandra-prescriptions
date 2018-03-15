package uk.co.boots.practice.dto;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import lombok.Builder;
import lombok.Data;
import uk.co.boots.common.Address;
import uk.co.boots.prescriber.dto.PrescriberDTO;

@Data
@Builder
public class PracticeDTO {
	private UUID id;
	private String practiceName;
	private Map<String, String> codes;
	private Address address;
	private List<PrescriberDTO> prescribers;
}
