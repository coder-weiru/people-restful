package org.people.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import javax.sql.DataSource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.people.model.Family;
import org.people.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
 "classpath:META-INF/db-hsqldb-config.xml",
		"classpath:META-INF/datasource-config.xml",
		"classpath:META-INF/application-config.xml" })
public class PeopleMapperTest {

	@Autowired
	private PeopleMapper peopleMapper;

	private ResourceDatabasePopulator dataPurger;

	@Autowired
	private DataSource dataSource;

	@Before
	public void setUp() throws Exception {
		dataPurger = new ResourceDatabasePopulator();
		dataPurger.setSqlScriptEncoding("UTF-8");
		dataPurger.addScript(new ClassPathResource("db/sql/purge-data.sql",
				PeopleMapper.class.getClassLoader()));
	}

	@After
	public void tearDown() {

	}

	@Test
	public final void testAddFamily() throws Exception {
		dataPurger.execute(dataSource);

		Family family = createSampleFamily();

		peopleMapper.addFamily(family);

		List<Family> familyList = peopleMapper.findFamily(family.getName());

		assertEquals(familyList.size(), 1);

		assertNotNull(familyList.get(0).getFid());
		assertEquals(familyList.get(0).getName(), family.getName());
	}

	@Test
	public final void testUpdateFamily() throws Exception {
		dataPurger.execute(dataSource);

		Family family = createSampleFamily();

		peopleMapper.addFamily(family);

		List<Family> familyList = peopleMapper.findFamily(family.getName());

		assertEquals(familyList.size(), 1);

		Family saved = familyList.get(0);

		saved.setName("New Name");

		peopleMapper.updateFamily(saved);

		Family updated = peopleMapper.getFamily(saved.getFid());

		assertEquals(updated.getName(), "New Name");

	}

	@Test
	public final void testDeleteFamily() throws Exception {
		dataPurger.execute(dataSource);

		Family family = createSampleFamily();

		peopleMapper.addFamily(family);

		List<Family> familyList = peopleMapper.findFamily(family.getName());

		assertEquals(familyList.size(), 1);

		assertNotNull(familyList.get(0).getFid());
		assertEquals(familyList.get(0).getName(), family.getName());

		peopleMapper.deleteFamily(family.getFid());

		familyList = peopleMapper.findFamily(family.getName());

		assertEquals(familyList.size(), 0);
	}

	@Test
	public final void testAddPerson() throws Exception {
		dataPurger.execute(dataSource);

		Person person = createSamplePerson();

		peopleMapper.addPerson(person);

		List<Person> personList = peopleMapper.findPerson(person.getName());

		assertEquals(personList.size(), 1);

		assertNotNull(personList.get(0).getPid());
		assertEquals(personList.get(0).getName(), person.getName());
		assertNull(personList.get(0).getFamily());

	}

	@Test
	public final void testAddPersonWithFamily() throws Exception {
		dataPurger.execute(dataSource);

		Family family = createSampleFamily();

		peopleMapper.addFamily(family);

		List<Family> familyList = peopleMapper.findFamily(family.getName());

		assertEquals(familyList.size(), 1);

		Family savedFamily = familyList.get(0);

		Person person = createSamplePerson();
		person.setFamily(savedFamily);

		peopleMapper.addPerson(person);

		List<Person> personList = peopleMapper.findPerson(person.getName());

		assertEquals(personList.size(), 1);

		assertNotNull(personList.get(0).getPid());
		assertEquals(personList.get(0).getName(), person.getName());
		assertEquals(personList.get(0).getFamily().getName(), person
				.getFamily().getName());
		assertEquals(personList.get(0).getFamily().getFid(), person.getFamily()
				.getFid());
	}

	@Test
	public final void testUpdatePerson() throws Exception {
		dataPurger.execute(dataSource);

		Person person = createSamplePerson();

		peopleMapper.addPerson(person);

		List<Person> personList = peopleMapper.findPerson(person.getName());

		assertEquals(personList.size(), 1);

		Person saved = personList.get(0);

		saved.setName("New Name");

		peopleMapper.updatePerson(saved);

		Person updated = peopleMapper.getPerson(saved.getPid());

		assertEquals(updated.getName(), "New Name");

	}

	@Test
	public final void testDeletePerson() throws Exception {
		dataPurger.execute(dataSource);

		Person person = createSamplePerson();

		peopleMapper.addPerson(person);

		List<Person> personList = peopleMapper.findPerson(person.getName());

		assertEquals(personList.size(), 1);

		assertNotNull(personList.get(0).getPid());
		assertEquals(personList.get(0).getName(), person.getName());
		assertNull(personList.get(0).getFamily());

		peopleMapper.deletePerson(person.getPid());

		personList = peopleMapper.findPerson(person.getName());

		assertEquals(personList.size(), 0);
	}

	@Test
	public final void testAddPersonToFamily() throws Exception {
		dataPurger.execute(dataSource);

		Family family = createSampleFamily();

		peopleMapper.addFamily(family);

		List<Family> familyList = peopleMapper.findFamily(family.getName());

		assertEquals(familyList.size(), 1);

		Family savedFamily = familyList.get(0);

		Person person = createSamplePerson();

		peopleMapper.addPerson(person);

		List<Person> personList = peopleMapper.findPerson(person.getName());

		assertEquals(personList.size(), 1);

		Person savedPerson = personList.get(0);

		peopleMapper.addPersonToFamily(savedPerson.getPid(),
				savedFamily.getFid());

		Person updatedPerson = peopleMapper.getPerson(savedPerson.getPid());

		assertEquals(updatedPerson.getName(), savedPerson.getName());
		assertEquals(updatedPerson.getFamily().getName(), savedFamily.getName());
		assertEquals(updatedPerson.getFamily().getFid(), savedFamily.getFid());
	}

	@Test
	public final void testRemovePersonFromFamily() throws Exception {
		dataPurger.execute(dataSource);

		Family family = createSampleFamily();

		peopleMapper.addFamily(family);

		List<Family> familyList = peopleMapper.findFamily(family.getName());

		assertEquals(familyList.size(), 1);

		Family savedFamily = familyList.get(0);

		Person person = createSamplePerson();

		peopleMapper.addPerson(person);

		List<Person> personList = peopleMapper.findPerson(person.getName());

		assertEquals(personList.size(), 1);

		Person savedPerson = personList.get(0);

		peopleMapper.addPersonToFamily(savedPerson.getPid(),
				savedFamily.getFid());

		Person updatedPerson = peopleMapper.getPerson(savedPerson.getPid());

		assertEquals(updatedPerson.getName(), savedPerson.getName());
		assertEquals(updatedPerson.getFamily().getName(), savedFamily.getName());
		assertEquals(updatedPerson.getFamily().getFid(), savedFamily.getFid());

		// Now remove
		peopleMapper.removePersonFromFamily(savedPerson.getPid(),
				savedFamily.getFid());

		Person detachedPerson = peopleMapper.getPerson(savedPerson.getPid());

		assertEquals(detachedPerson.getName(), savedPerson.getName());
		assertNull(detachedPerson.getFamily());

	}

	@Test
	public final void testGetFamilyPeople() throws Exception {
		dataPurger.execute(dataSource);

		Family family = createSampleFamily();

		peopleMapper.addFamily(family);

		List<Family> familyList = peopleMapper.findFamily(family.getName());

		assertEquals(familyList.size(), 1);

		Family savedFamily = familyList.get(0);

		Person person1 = createSamplePerson();
		person1.setName(person1.getName() + " 1");
		peopleMapper.addPerson(person1);

		Person person2 = createSamplePerson();
		person2.setName(person2.getName() + " 2");
		peopleMapper.addPerson(person2);

		Person person3 = createSamplePerson();
		person3.setName(person3.getName() + " 3");
		peopleMapper.addPerson(person3);

		List<Person> personList = peopleMapper.findPerson(createSamplePerson()
				.getName());

		assertEquals(personList.size(), 3);

		for (Person p : personList) {
			peopleMapper.addPersonToFamily(p.getPid(), savedFamily.getFid());

		}

		List<Person> people = peopleMapper
				.getFamilyPeople(savedFamily.getFid());

		assertEquals(people.size(), 3);
		assertEquals(people.get(0).getName(), person1.getName());
		assertEquals(people.get(0).getFamily().getName(), savedFamily.getName());
		assertEquals(people.get(0).getFamily().getFid(), savedFamily.getFid());

		assertEquals(people.get(1).getName(), person2.getName());
		assertEquals(people.get(1).getFamily().getName(), savedFamily.getName());
		assertEquals(people.get(1).getFamily().getFid(), savedFamily.getFid());

		assertEquals(people.get(2).getName(), person3.getName());
		assertEquals(people.get(2).getFamily().getName(), savedFamily.getName());
		assertEquals(people.get(2).getFamily().getFid(), savedFamily.getFid());

	}

	private Person createSamplePerson() {
		Person person = new Person();
		person.setName("Andy Weber");
		return person;
	}

	private Family createSampleFamily() {
		Family family = new Family();
		family.setName("Weber");
		return family;
	}
}
