package uk.co.boots.store.resource;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import uk.co.boots.store.dto.StoreDTO;
import uk.co.boots.store.service.StoreRepositoryService;

@RestController
@RequestMapping("/api/store")
public class StoreResourceController {
	private final Logger log = LoggerFactory.getLogger(StoreResourceController.class);
	@Autowired
	StoreRepositoryService srs;

	
	@RequestMapping(value = "/", method = POST, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<StoreDTO> create(@RequestBody StoreDTO p) throws URISyntaxException {

        log.debug("Create StoreDTO : {}", p);

        StoreDTO result = srs.save(p);

        return ResponseEntity.created(new URI("/api/store/" + result.getId())).body(result);
    }
	
	@RequestMapping(value = "/multiple", method = POST, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<StoreDTO>> create(@RequestBody List<StoreDTO> storeList) throws URISyntaxException {

        log.debug("Create StoreDTO : {}", storeList);

        List<StoreDTO> result = srs.save(storeList);

		return new ResponseEntity<>(result, HttpStatus.OK);
    }

	@RequestMapping(value = "/{region}", method = GET, produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<List<StoreDTO>> getByRegion(@PathVariable("region") String region) throws URISyntaxException {
		List<StoreDTO> dto = srs.getByRegion(region);
				
		return Optional.ofNullable(dto).map(p -> new ResponseEntity<>(p, HttpStatus.OK))
		                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

}
