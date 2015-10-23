package org.people.service.restful;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.people.model.Family;
import org.people.model.Person;
import org.people.service.FamilyExistsException;
import org.people.service.PeopleService;
import org.people.service.PersonExistsException;
import org.people.utils.JSONUtil;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.setup.StandaloneMockMvcBuilder;

@RunWith(MockitoJUnitRunner.class)
public class PeopleServiceRestControllerTest {

	@Mock
	private PeopleService peopleServiceMock;

	@InjectMocks
	private PeopleServiceRestController peopleServiceRestController;

	private MockMvc mockMvc;

	@Before
	public void setUp() throws Exception {
		peopleServiceRestController = new PeopleServiceRestController();
		ReflectionTestUtils.setField(peopleServiceRestController,
				"peopleService", peopleServiceMock);

		StandaloneMockMvcBuilder standaloneMockMvcBuilder = MockMvcBuilders
				.standaloneSetup(peopleServiceRestController);

		// Set MappingJackson2HttpMessageConverter
		MappingJackson2HttpMessageConverter messageConverter = new MappingJackson2HttpMessageConverter();

		mockMvc = standaloneMockMvcBuilder.setMessageConverters(
				messageConverter).build();
	}

	@Test
	public final void testAddFamily() throws Exception {
		Family family = createSampleFamily();
		Mockito.when(peopleServiceMock.addFamily(Mockito.any(Family.class)))
				.thenReturn(family);

		mockMvc.perform(
				MockMvcRequestBuilders.post("/family")
						.contentType(MediaType.APPLICATION_JSON)
						.content(JSONUtil.toJSonString(family).getBytes()))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(
						MockMvcResultMatchers.content().string(
								JSONUtil.toJSonString(family)));
	}

	@Test
	public final void testAddFamilyWithFamilyExistsException() throws Exception {
		Family family = createSampleFamily();
		Mockito.doThrow(FamilyExistsException.class).when(peopleServiceMock)
				.addFamily(Mockito.any(Family.class));
		mockMvc.perform(
				MockMvcRequestBuilders.post("/family")
						.contentType(MediaType.APPLICATION_JSON)
						.content(JSONUtil.toJSonString(family).getBytes()))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isBadRequest())
				.andExpect(
						MockMvcResultMatchers.jsonPath("['httpStatus']").value(
								Matchers.containsString("BAD_REQUEST")));
	}

	@Test
	public final void testUpdateFamily() throws Exception {
		Family family = createSampleFamily();
		Mockito.when(peopleServiceMock.updateFamily(Mockito.any(Family.class)))
				.thenReturn(family);

		mockMvc.perform(
				MockMvcRequestBuilders.post("/family/update")
						.contentType(MediaType.APPLICATION_JSON)
						.content(JSONUtil.toJSonString(family).getBytes()))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(
						MockMvcResultMatchers.content().string(
								JSONUtil.toJSonString(family)));
	}

	@Test
	public final void testFindFamily() throws Exception {
		Family family = createSampleFamily();
		List<Family> list = new ArrayList<Family>();
		list.add(family);

		Mockito.when(peopleServiceMock.findFamily("Weber")).thenReturn(list);
		mockMvc.perform(
				MockMvcRequestBuilders.get("/family/find/Weber").accept(
						MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(
						MockMvcResultMatchers.content().contentType(
								MediaType.APPLICATION_JSON_VALUE))
				.andExpect(
						MockMvcResultMatchers.content().string(
								JSONUtil.toJSonString(list)));
	}

	@Test
	public final void testGetFamily() throws Exception {
		Family family = createSampleFamily();
		Mockito.when(peopleServiceMock.getFamily(new Long(1))).thenReturn(
				family);
		mockMvc.perform(
				MockMvcRequestBuilders.get("/family/1").accept(
						MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(
						MockMvcResultMatchers.content().contentType(
								MediaType.APPLICATION_JSON_VALUE))
				.andExpect(
						MockMvcResultMatchers.content().string(
								JSONUtil.toJSonString(family)));
	}

	@Test
	public final void testDeleteFamily() throws Exception {
		Mockito.doNothing().when(peopleServiceMock)
				.deleteFamily(Mockito.any(Long.class));

		mockMvc.perform(
				MockMvcRequestBuilders.delete("/delFamily/100").contentType(
						MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().string(""));
	}

	@Test
	public final void testAddPerson() throws Exception {
		Person person = createSamplePerson();
		Mockito.when(peopleServiceMock.addPerson(Mockito.any(Person.class)))
				.thenReturn(person);
		mockMvc.perform(
				MockMvcRequestBuilders.post("/person")
						.contentType(MediaType.APPLICATION_JSON)
						.content(JSONUtil.toJSonString(person).getBytes()))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(
						MockMvcResultMatchers.content().string(
								JSONUtil.toJSonString(person)));
	}

	@Test
	public final void testAddPersonWithPersonExistsException() throws Exception {
		Person person = createSamplePerson();
		Mockito.doThrow(PersonExistsException.class).when(peopleServiceMock)
				.addPerson(Mockito.any(Person.class));
		mockMvc.perform(
				MockMvcRequestBuilders.post("/person")
						.contentType(MediaType.APPLICATION_JSON)
						.content(JSONUtil.toJSonString(person).getBytes()))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isBadRequest())
				.andExpect(
						MockMvcResultMatchers.jsonPath("['httpStatus']").value(
								Matchers.containsString("BAD_REQUEST")));
	}

	@Test
	public final void testUpdatePerson() throws Exception {
		Person person = createSamplePerson();
		Mockito.when(peopleServiceMock.updatePerson(Mockito.any(Person.class)))
				.thenReturn(person);
		mockMvc.perform(
				MockMvcRequestBuilders.post("/person/update")
						.contentType(MediaType.APPLICATION_JSON)
						.content(JSONUtil.toJSonString(person).getBytes()))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(
						MockMvcResultMatchers.content().string(
								JSONUtil.toJSonString(person)));
	}

	@Test
	public final void testFindPerson() throws Exception {
		Person person = createSamplePerson();
		List<Person> list = new ArrayList<Person>();
		list.add(person);

		Mockito.when(peopleServiceMock.findPerson("Andy")).thenReturn(list);
		mockMvc.perform(
				MockMvcRequestBuilders.get("/person/find/Andy").accept(
						MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(
						MockMvcResultMatchers.content().contentType(
								MediaType.APPLICATION_JSON_VALUE))
				.andExpect(
						MockMvcResultMatchers.content().string(
								JSONUtil.toJSonString(list)));
	}

	@Test
	public final void testGetPerson() throws Exception {
		Person person = createSamplePerson();
		Mockito.when(peopleServiceMock.getPerson(new Long(1))).thenReturn(
				person);
		mockMvc.perform(
				MockMvcRequestBuilders.get("/person/1")
						.accept(MediaType.APPLICATION_JSON)
						.contentType(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(
						MockMvcResultMatchers.content().contentType(
								MediaType.APPLICATION_JSON_VALUE))
				.andExpect(
						MockMvcResultMatchers.content().string(
								JSONUtil.toJSonString(person)));
	}

	@Test
	public final void testDeletePerson() throws Exception {
		Mockito.doNothing().when(peopleServiceMock)
				.deletePerson(Mockito.any(Long.class));

		mockMvc.perform(
				MockMvcRequestBuilders.delete("/delPerson/200").contentType(
						MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().string(""));
	}

	@Test
	public final void testAddPersonToFamily() throws Exception {
		Mockito.doNothing()
				.when(peopleServiceMock)
				.addPersonToFamily(Mockito.any(Long.class),
						Mockito.any(Long.class));
		mockMvc.perform(
				MockMvcRequestBuilders.get("/add/person/11/family/22")
						.accept(MediaType.APPLICATION_JSON)
						.contentType(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public final void testRemovePersonFromFamily() throws Exception {
		Mockito.doNothing()
				.when(peopleServiceMock)
				.removePersonFromFamily(Mockito.any(Long.class),
						Mockito.any(Long.class));
		mockMvc.perform(
				MockMvcRequestBuilders.get("/del/person/11/family/22")
						.accept(MediaType.APPLICATION_JSON)
						.contentType(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public final void testGetFamilyPeople() throws Exception {
		Person person = createSamplePerson();
		List<Person> list = new ArrayList<Person>();
		list.add(person);

		Mockito.when(peopleServiceMock.getFamilyPeople(Mockito.any(Long.class)))
				.thenReturn(list);
		mockMvc.perform(
				MockMvcRequestBuilders.get("/familyPeople/22")
						.accept(MediaType.APPLICATION_JSON)
						.contentType(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(
						MockMvcResultMatchers.content().contentType(
								MediaType.APPLICATION_JSON_VALUE))
				.andExpect(
						MockMvcResultMatchers.content().string(
								JSONUtil.toJSonString(list)));
	}

	private Person createSamplePerson() {
		Person person = new Person();
		person.setPid(new Long(1));
		person.setName("Andy Weber");
		return person;
	}

	private Family createSampleFamily() {
		Family family = new Family();
		family.setFid(new Long(1));
		family.setName("Weber");
		return family;
	}

}
