package org.people.service;

import java.util.List;

import org.people.model.Family;
import org.people.model.Person;

public interface PeopleService {

	void addFamily(Family family) throws FamilyExistsException;

	void updateFamily(Family family) throws FamilyExistsException;

	List<Family> findFamily(String name);

	Family getFamily(Long fid);

	void addPerson(Person person) throws PersonExistsException;

	void updatePerson(Person person) throws PersonExistsException;

	List<Person> findPerson(String name);

	Person getPerson(Long pid);

	void addPersonToFamily(Long pid, Long fid);

	void removePersonFromFamily(Long pid, Long fid);

	List<Person> getFamilyPeople(Long fid);

}
