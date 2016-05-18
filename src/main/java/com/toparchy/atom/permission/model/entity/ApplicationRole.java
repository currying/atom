package com.toparchy.atom.permission.model.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OrderBy;

import org.picketlink.idm.jpa.annotations.AttributeValue;
import org.picketlink.idm.jpa.annotations.entity.IdentityManaged;
import org.picketlink.idm.jpa.model.sample.simple.IdentityTypeEntity;
import org.picketlink.idm.model.basic.Role;

@IdentityManaged(Role.class)
@Entity
public class ApplicationRole extends IdentityTypeEntity {
	private static final long serialVersionUID = 1183507936637255274L;
	@AttributeValue
	private String name;
	@AttributeValue
	@Column(name = "SYS_APPLICATION_ROLE_ASNAME", length = 255)
	private String asName;
	@AttributeValue
	@ManyToMany(cascade = { CascadeType.REFRESH, CascadeType.MERGE }, fetch = FetchType.EAGER)
	@OrderBy("name ASC")
	private Set<ApplicationResource> applicationResources = new HashSet<ApplicationResource>();

	public ApplicationRole() {
	}

	public ApplicationRole(String name, String asName) {
		this.name = name;
		this.asName = asName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAsName() {
		return asName;
	}

	public void setAsName(String asName) {
		this.asName = asName;
	}

	public void addApplicationResource(ApplicationResource applicationResource) {
		applicationResources.add(applicationResource);
	}

	public void removeApplicationResource(ApplicationResource applicationResource) {
		applicationResources.remove(applicationResource);
	}

	public void setApplicationResources(Set<ApplicationResource> applicationResources) {
		this.applicationResources = applicationResources;
	}

	public Set<ApplicationResource> getApplicationResources() {
		return applicationResources;
	}

}
