package uk.co.boots.store.dto;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uk.co.boots.common.Address;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StoreDTO {
	private UUID id;
	private String storeName;
	private Address address;
}
