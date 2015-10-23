package org.people.service;

import java.util.List;

import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.people.model.Family;
import org.people.model.Person;

public interface PeopleMapper {

	@InsertProvider(type = PeopleMapperSQLProvider.class, method = "addFamily")
	@Options(useGeneratedKeys = true, keyProperty = "fid", flushCache = true)
	void addFamily(Family family);

	@UpdateProvider(type = PeopleMapperSQLProvider.class, method = "updateFamily")
	@Options(fetchSize = 1, timeout = 0, useCache = true, flushCache = true)
	void updateFamily(Family family);

	@Results(value = {
			@Result(column = "fid", property = "fid", javaType = long.class),
			@Result(column = "name", property = "name", javaType = String.class) })
	@SelectProvider(type = PeopleMapperSQLProvider.class, method = "findFamily")
	List<Family> findFamily(String name);

	@Results(value = {
			@Result(column = "fid", property = "fid", javaType = long.class),
			@Result(column = "name", property = "name", javaType = String.class) })
	@SelectProvider(type = PeopleMapperSQLProvider.class, method = "getFamily")
	Family getFamily(Long fid);

	@DeleteProvider(type=PeopleMapperSQLProvider.class, method="deleteFamily")
    void deleteFamily(Long fid);
	
	@InsertProvider(type = PeopleMapperSQLProvider.class, method = "addPerson")
	@Options(useGeneratedKeys = true, keyProperty = "pid", flushCache = true)
	void addPerson(Person person);

	@UpdateProvider(type = PeopleMapperSQLProvider.class, method = "updatePerson")
	@Options(fetchSize = 1, timeout = 0, useCache = true, flushCache = true)
	void updatePerson(Person person);

	@Results(value = {
			@Result(column = "pid", property = "pid", javaType = long.class),
			@Result(column = "name", property = "name", javaType = String.class),
			@Result(column = "fid", property = "family", javaType = Family.class, one = @One(select = "org.people.service.PeopleMapper.getFamily")) })
	@SelectProvider(type = PeopleMapperSQLProvider.class, method = "findPerson")
	List<Person> findPerson(String name);

	@Results(value = {
			@Result(column = "pid", property = "pid", javaType = long.class),
			@Result(column = "name", property = "name", javaType = String.class),
			@Result(column = "fid", property = "family", javaType = Family.class, one = @One(select = "org.people.service.PeopleMapper.getFamily")) })
	@SelectProvider(type = PeopleMapperSQLProvider.class, method = "getPerson")
	Person getPerson(Long pid);

	@DeleteProvider(type=PeopleMapperSQLProvider.class, method="deletePerson")
    void deletePerson(Long pid);
	
	@InsertProvider(type = PeopleMapperSQLProvider.class, method = "addPersonToFamily")
	public void addPersonToFamily(@Param("pid") Long pid, @Param("fid") Long fid);

	@InsertProvider(type = PeopleMapperSQLProvider.class, method = "removePersonFromFamily")
	public void removePersonFromFamily(@Param("pid") Long pid,
			@Param("fid") Long fid);

	@Results(value = {
			@Result(column = "pid", property = "pid", javaType = long.class),
			@Result(column = "name", property = "name", javaType = String.class),
			@Result(column = "fid", property = "family", javaType = Family.class, one = @One(select = "org.people.service.PeopleMapper.getFamily")) })
	@SelectProvider(type = PeopleMapperSQLProvider.class, method = "getFamilyPeople")
	List<Person> getFamilyPeople(Long fid);

}
