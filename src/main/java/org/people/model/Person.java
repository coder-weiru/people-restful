package org.people.model;

import java.io.Serializable;

public class Person implements Serializable {

	private static final long serialVersionUID = -2234106024432929148L;
	private Long pid;
	private String name;
	private Family family;

	public Person() {
		super();
	}

	public Long getPid() {
		return pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Family getFamily() {
		return family;
	}

	public void setFamily(Family family) {
		this.family = family;
	}

	@Override
	public String toString() {
		return new StringBuilder("Person[").append("name: ").append(getName())
				.append(", pid: ").append(getPid()).append(", family: ")
				.append(getFamily()).append("]").toString();
	}

}
