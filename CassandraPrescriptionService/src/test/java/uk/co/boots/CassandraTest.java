package uk.co.boots;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import uk.co.boots.common.Address;
import uk.co.boots.practice.dto.PracticeDTO;
import uk.co.boots.practice.service.PracticeRepositoryService;

@SpringBootTest
public class CassandraTest extends AbstractEmbeddedCassandraTest {

	@Autowired
	PracticeRepositoryService practiceRepoService;
	
	@Test
	public void test() {
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
	}
}
