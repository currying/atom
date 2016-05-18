package com.toparchy.atom.permission.model;

import org.picketlink.idm.model.annotation.AttributeProperty;
import org.picketlink.idm.model.basic.Role;

public class SystemRole extends Role {

	private static final long serialVersionUID = -2402192076573245086L;
	@AttributeProperty
	private String asName;

	public SystemRole() {
	}

	public SystemRole(String name) {
		super(name);
	}

	public SystemRole(String name, String asName) {
		super(name);
		this.asName = asName;
	}

	public String getAsName() {
		return asName;
	}

	public void setAsName(String asName) {
		this.asName = asName;
	}

}
