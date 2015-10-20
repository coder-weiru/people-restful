package org.people.service;

import java.util.List;

import org.people.model.Family;
import org.people.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PeopleServiceImpl implements PeopleService {

	private static final String FAMILY_EXISTS_ERROR_MESSAGE = "Family with name %s exists in the system, please enter a different name.";
	private static final String PERSON_EXISTS_ERROR_MESSAGE = "Person with name %s exists in the system, please enter a different name.";

	private PeopleMapper peopleMapper;

	@Autowired
	public void setPeopleMapper(PeopleMapper peopleMapper) {
		this.peopleMapper = peopleMapper;
	}

	@Override
	@Transactional
	public Family addFamily(Family family) throws FamilyExistsException {
		String name = family.getName();
		List<Family> list = peopleMapper.findFamily(name);
		for (Family f : list) {
			if (name.equalsIgnoreCase(f.getName())) {
				throw new FamilyExistsException(String.format(
						FAMILY_EXISTS_ERROR_MESSAGE, name));
			}
		}
		peopleMapper.addFamily(family);
		return family;
	}

	@Override
	public Family updateFamily(Family family) throws FamilyExistsException {
		String name = family.getName();
		Long fid = family.getFid();
		List<Family> list = peopleMapper.findFamily(name);
		for (Family f : list) {
			if (name.equalsIgnoreCase(f.getName()) && !(fid.equals(f.getFid()))) {
				throw new FamilyExistsException(String.format(
						FAMILY_EXISTS_ERROR_MESSAGE, name));
			}
		}
		peopleMapper.updateFamily(family);
		return family;
	}

	@Override
	public List<Family> findFamily(String name) {
		return peopleMapper.findFamily(name);
	}

	@Override
	public Family getFamily(Long fid) {
		return peopleMapper.getFamily(fid);
	}

	@Override
	@Transactional
	public Person addPerson(Person person) throws PersonExistsException {
		String name = person.getName();
		List<Person> list = peopleMapper.findPerson(name);
		for (Person p : list) {
			if (name.equalsIgnoreCase(p.getName())) {
				throw new PersonExistsException(String.format(
						PERSON_EXISTS_ERROR_MESSAGE, name));
			}
		}
		peopleMapper.addPerson(person);
		return person;
	}

	@Override
	public Person updatePerson(Person person) throws PersonExistsException {
		String name = person.getName();
		Long pid = person.getPid();
		List<Person> list = peopleMapper.findPerson(name);
		for (Person p : list) {
			if (name.equalsIgnoreCase(p.getName()) && !(pid.equals(p.getPid()))) {
				throw new PersonExistsException(String.format(
						PERSON_EXISTS_ERROR_MESSAGE, name));
			}
		}
		peopleMapper.updatePerson(person);
		return person;
	}

	@Override
	public List<Person> findPerson(String name) {
		return peopleMapper.findPerson(name);
	}

	@Override
	public Person getPerson(Long pid) {
		return peopleMapper.getPerson(pid);
	}

	@Override
	public void addPersonToFamily(Long pid, Long fid) {
		peopleMapper.addPersonToFamily(pid, fid);
	}

	@Override
	public void removePersonFromFamily(Long pid, Long fid) {
		peopleMapper.removePersonFromFamily(pid, fid);
	}

	@Override
	public List<Person> getFamilyPeople(Long fid) {
		return peopleMapper.getFamilyPeople(fid);
	}

}
