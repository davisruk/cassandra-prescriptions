package uk.co.boots.store.dto;

import java.util.UUID;

import lombok.Builder;
import lombok.Data;
import uk.co.boots.common.Address;

@Builder
@Data
public class StoreDTO {
	private UUID id;
	private String storeName;
	private Address address;
}
