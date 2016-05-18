package com.toparchy.atom.permission.model.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "SYS_APPLICATION_RESOURCE")
public class ApplicationResource implements Serializable {

	private static final long serialVersionUID = 4440709995178566201L;
	@Id
	@Column(name = "SYS_APPLICATION_RESOURCE_ID", length = 50)
	@GeneratedValue(generator = "systemresource-uuid")
	@GenericGenerator(name = "systemresource-uuid", strategy = "uuid")
	private String id;
	@Column(name = "SYS_APPLICATION_RESOURCE_KEY", length = 255)
	private String key;
	@Column(name = "SYS_APPLICATION_RESOURCE_NAME", length = 255)
	private String name;
	@Column(name = "SYS_APPLICATION_RESOURCE_TYPE", length = 255)
	private String type;
	@ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER, mappedBy = "applicationResources")
	private Set<ApplicationRoleEntity> applicationRoles = new HashSet<ApplicationRoleEntity>();

	public ApplicationResource() {
	}

	public ApplicationResource(String key, String name, String type) {
		this.key = key;
		this.name = name;
		this.type = type;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Set<ApplicationRoleEntity> getApplicationRoles() {
		return applicationRoles;
	}

	public void setApplicationRoles(Set<ApplicationRoleEntity> applicationRoles) {
		this.applicationRoles = applicationRoles;
	}

}
