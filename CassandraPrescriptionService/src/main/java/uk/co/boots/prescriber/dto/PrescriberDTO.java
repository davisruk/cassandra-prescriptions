package uk.co.boots.prescriber.dto;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import lombok.Builder;
import lombok.Data;
import uk.co.boots.practice.dto.PracticeDTO;

@Data
@Builder
public class PrescriberDTO {
	private UUID id;
	private String firstName;
	private String secondName;
	private Map<String, String> codes;
 	private List<PracticeDTO> practice;
}
