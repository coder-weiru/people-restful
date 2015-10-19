package org.people.model;

import java.io.Serializable;

public class Family implements Serializable {

	private static final long serialVersionUID = 8789210154221066630L;
	private Long fid;
	private String name;
	
	public Long getFid() {
		return fid;
	}
	public void setFid(Long fid) {
		this.fid = fid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return new StringBuilder("Family[").append("name: ").append(getName())
				.append(", fid: ").append(getFid()).toString();
	}

}
