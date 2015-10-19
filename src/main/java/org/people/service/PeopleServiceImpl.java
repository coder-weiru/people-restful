package org.people.service;

import java.util.List;

import org.people.model.Family;
import org.people.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PeopleServiceImpl implements PeopleService {

	@Autowired
	private PeopleMapper peopleMapper;

	public void setPersonMapper(PeopleMapper peopleMapper) {
		this.peopleMapper = peopleMapper;
	}

	@Override
	public void addFamily(Family family) {
		peopleMapper.addFamily(family);
	}

	@Override
	public void updateFamily(Family family) {
		peopleMapper.updateFamily(family);
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
	public void addPerson(Person person) {
		peopleMapper.addPerson(person);
	}

	@Override
	public void updatePerson(Person Person) {
		peopleMapper.updatePerson(Person);
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
