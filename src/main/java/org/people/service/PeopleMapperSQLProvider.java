package org.people.service;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;
import org.people.model.Family;
import org.people.model.Person;

public class PeopleMapperSQLProvider {

	public String findFamily(final String name) {
		return new SQL() {
			{
				SELECT("families.fid, families.name");
				FROM("families");
				WHERE("lower(families.name) like lower(#{name, jdbcType=VARCHAR}||'%')");
				ORDER_BY("families.name asc");
			}
		}.toString();
	}

	public String getFamily(final Long fid) {
		return new SQL() {
			{
				SELECT("families.fid, families.name");
				FROM("families");
				WHERE("families.fid = #{fid, jdbcType=BIGINT}");
			}
		}.toString();
	}

	public String updateFamily(final Family family) {
		return new SQL() {
			{
				UPDATE("families");
				if (family.getName() != null) {
					SET("name = #{name, jdbcType=VARCHAR}");
				}
				WHERE("fid = #{fid}");
			}
		}.toString();
	}

	public String addFamily(final Family family) {

		return new SQL() {
			{
				INSERT_INTO("families");
				VALUES("name", "#{name, jdbcType=VARCHAR}");
			}
		}.toString();
	}

	public String findPerson(final String name) {
		return new SQL() {
			{
				SELECT("persons.pid, persons.name");
				SELECT("families.fid");
				FROM("persons left join families on persons.family_id = families.fid");
				WHERE("lower(persons.name) like lower(#{name, jdbcType=VARCHAR}||'%')");
				ORDER_BY("persons.name asc");
			}
		}.toString();
	}

	public String getPerson(final Long pid) {
		return new SQL() {
			{
				SELECT("persons.pid, persons.name");
				SELECT("families.fid");
				FROM("persons left join families on persons.family_id = families.fid");
				WHERE("persons.pid = #{pid, jdbcType=BIGINT}");
			}
		}.toString();
	}

	public String updatePerson(final Person person) {
		return new SQL() {
			{
				UPDATE("persons");
				if (person.getName() != null) {
					SET("name = #{name, jdbcType=VARCHAR}");
				}
				if (person.getFamily() != null) {
					SET("family_id = #{password, jdbcType=VARCHAR}");
				}
				WHERE("pid = #{pid}");
			}
		}.toString();
	}

	public String addPerson(final Person person) {

		return new SQL() {
			{
				INSERT_INTO("persons");
				VALUES("name", "#{name, jdbcType=VARCHAR}");
				VALUES("family_id", "#{family.fid, jdbcType=BIGINT}");
			}
		}.toString();
	}

	@SuppressWarnings("unused")
	public String addPersonToFamily(final Map<String, Object> parameters) {

		Long pid = (Long) parameters.get("pid");
		Long fid = (Long) parameters.get("fid");

		return new SQL() {
			{
				UPDATE("persons");
				SET("family_id = #{fid, jdbcType=BIGINT}");
				WHERE("pid = #{pid}");
			}
		}.toString();
	}

	@SuppressWarnings("unused")
	public String removePersonFromFamily(final Map<String, Object> parameters) {

		Long pid = (Long) parameters.get("pid");
		Long fid = (Long) parameters.get("fid");

		return new SQL() {
			{
				UPDATE("persons");
				SET("family_id = null");
				WHERE("pid = #{pid} and family_id = #{fid}");
			}
		}.toString();
	}

	public String getFamilyPeople(final Long fid) {

		return new SQL() {
			{
				SELECT("persons.pid, persons.name");
				SELECT("families.fid");
				FROM("persons left join families on persons.family_id = families.fid");
				WHERE("families.fid = #{fid}");
				ORDER_BY("persons.name asc");
			}
		}.toString();
	}

}
