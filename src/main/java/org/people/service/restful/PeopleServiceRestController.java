package org.people.service.restful;

import java.util.List;

import org.people.model.Family;
import org.people.model.Person;
import org.people.service.FamilyExistsException;
import org.people.service.PeopleService;
import org.people.service.PersonExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PeopleServiceRestController {

	@Autowired
	private PeopleService peopleService;

	@RequestMapping(value = "/family", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Family addFamily(@RequestBody Family family)
			throws FamilyExistsException {
		return peopleService.addFamily(family);
	}

	@RequestMapping(value = "/family/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Family updateFamily(@RequestBody Family family)
			throws FamilyExistsException {
		return peopleService.updateFamily(family);
	}

	@RequestMapping(value = "/family/find", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Family> findFamily() {
		return peopleService.findFamily("");
	}

	@RequestMapping(value = "/family/find/{name}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Family> findFamily(@PathVariable String name) {
		return peopleService.findFamily(name);
	}

	@RequestMapping(value = "/family/{fid}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Family getFamily(@PathVariable Long fid) {
		return peopleService.getFamily(fid);
	}

	@RequestMapping(value = "/person", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Person addPerson(@RequestBody Person person)
			throws PersonExistsException {
		return peopleService.addPerson(person);
	}

	@RequestMapping(value = "/person/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Person updatePerson(@RequestBody Person person)
			throws PersonExistsException {
		return peopleService.updatePerson(person);
	}

	@RequestMapping(value = "/person/find", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Person> findPerson() {
		return peopleService.findPerson("");
	}

	@RequestMapping(value = "/person/find/{name}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Person> findPerson(@PathVariable String name) {
		return peopleService.findPerson(name);
	}

	@RequestMapping(value = "/person/{pid}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Person getPerson(@PathVariable Long pid) {
		return peopleService.getPerson(pid);
	}

	@RequestMapping(value = "/person/{pid}/family/{fid}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public void addPersonToFamily(@PathVariable Long pid, @PathVariable Long fid) {
		peopleService.addPersonToFamily(pid, fid);
	}

	@RequestMapping(value = "/person/{pid}/family/{fid}/delete", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public void removePersonFromFamily(Long pid, Long fid) {
		peopleService.removePersonFromFamily(pid, fid);
	}

	@RequestMapping(value = "/family/{fid}/people", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Person> getFamilyPeople(Long fid) {
		return peopleService.getFamilyPeople(fid);
	}

	@ExceptionHandler({ FamilyExistsException.class })
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<RestError> handleFamilyExistsException(
			FamilyExistsException exe) {
		RestError restError = new RestError(HttpStatus.BAD_REQUEST,
				exe.getMessage());
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		return new ResponseEntity<RestError>(restError, httpHeaders,
				HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler({ PersonExistsException.class })
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<RestError> handlePersonExistsException(
			PersonExistsException exe) {
		RestError restError = new RestError(HttpStatus.BAD_REQUEST,
				exe.getMessage());
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		return new ResponseEntity<RestError>(restError, httpHeaders,
				HttpStatus.BAD_REQUEST);
	}
}
