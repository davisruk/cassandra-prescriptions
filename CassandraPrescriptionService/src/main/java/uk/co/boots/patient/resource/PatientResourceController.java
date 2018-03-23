package uk.co.boots.patient.resource;

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

import uk.co.boots.patient.dto.PatientDTO;
import uk.co.boots.patient.service.PatientRepositoryService;
import uk.co.boots.store.dto.StoreDTO;

@RestController
@RequestMapping("/api/patient")
public class PatientResourceController {

	private final Logger log = LoggerFactory.getLogger(PatientResourceController.class);
	@Autowired
	PatientRepositoryService ps;

	
	@RequestMapping(value = "/", method = POST, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<PatientDTO> create(@RequestBody PatientDTO p) throws URISyntaxException {

        log.debug("Create PatientDTO : {}", p);

        PatientDTO result = ps.save(p);

        return ResponseEntity.created(new URI("/api/patient/" + result.getId())).body(result);
    }
	@RequestMapping(value = "/multiple", method = POST, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PatientDTO>> create(@RequestBody List<PatientDTO> patientList) throws URISyntaxException {

        log.debug("Create PatientDTO : {}", patientList);

        List<PatientDTO> result = ps.save(patientList);

		return new ResponseEntity<>(result, HttpStatus.OK);
    }
	
	
	@RequestMapping(value = "/{region}/{codeType}/{codeValue}", method = GET, produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<PatientDTO> getByRegionAndCodeAndCodeValue(@PathVariable("region") String region
																	,@PathVariable("codeType") String codeType
																	,@PathVariable("codeValue") String codeValue) throws URISyntaxException {
		PatientDTO dto = ps.getPatientForRegionAndCode(region, codeType, codeValue);
				
		return Optional.ofNullable(dto).map(p -> new ResponseEntity<>(p, HttpStatus.OK))
		                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

}
