package org.people.service;

import java.util.List;

import org.people.model.Family;
import org.people.model.Person;

public interface PeopleService {

	Family addFamily(Family family) throws FamilyExistsException;

	Family updateFamily(Family family) throws FamilyExistsException;

	List<Family> findFamily(String name);

	Family getFamily(Long fid);

	void deleteFamily(Long fid);
	
	Person addPerson(Person person) throws PersonExistsException;

	Person updatePerson(Person person) throws PersonExistsException;

	List<Person> findPerson(String name);

	Person getPerson(Long pid);
	
	void deletePerson(Long pid);

	void addPersonToFamily(Long pid, Long fid);

	void removePersonFromFamily(Long pid, Long fid);

	List<Person> getFamilyPeople(Long fid);

}
