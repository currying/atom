package com.toparchy.atom.permission.service;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.picketlink.idm.IdentityManager;
import org.picketlink.idm.PartitionManager;
import org.picketlink.idm.model.basic.BasicModel;
import org.picketlink.idm.model.basic.Role;

import com.toparchy.atom.permission.model.entity.ApplicationResource;
import com.toparchy.atom.permission.model.entity.ApplicationRole;

@Stateless
public class RoleResourceRegistration {
	@Inject
	private EntityManager atomEm;
	@Inject
	private PartitionManager partitionManager;

	@Inject
	private Event<ApplicationRole> applicationRoleEventSrc;

	public void add(ApplicationRole applicationRole, ApplicationResource applicationResource) {
		ApplicationRole role = atomEm.find(ApplicationRole.class, applicationRole.getId());
		ApplicationResource resource = atomEm.find(ApplicationResource.class, applicationResource.getId());
		role.addApplicationResource(resource);
		// applicationRole.addApplicationResource(applicationResource);
		atomEm.merge(role);
		atomEm.flush();
		applicationRoleEventSrc.fire(role);
	}

	public void remove(ApplicationRole applicationRole, ApplicationResource applicationResource) {
		ApplicationRole role = atomEm.find(ApplicationRole.class, applicationRole.getId());
		ApplicationResource resource = atomEm.find(ApplicationResource.class, applicationResource.getId());
		role.removeApplicationResource(resource);
		// applicationRole.removeApplicationResource(applicationResource);
		atomEm.merge(role);
		atomEm.flush();
		applicationRoleEventSrc.fire(role);
	}

	public void createRole(ApplicationRole applicationRole) {
		atomEm.persist(applicationRole);
		atomEm.flush();
		applicationRoleEventSrc.fire(applicationRole);
		IdentityManager identityManager = this.partitionManager.createIdentityManager();
		Role role = new Role(applicationRole.getName());
		identityManager.add(role);
	}

	public void deleteRole(ApplicationRole applicationRole) {
		ApplicationRole role = atomEm.find(ApplicationRole.class, applicationRole.getId());
		atomEm.remove(role);
		atomEm.flush();
		applicationRoleEventSrc.fire(role);
		IdentityManager identityManager = this.partitionManager.createIdentityManager();
		Role role_ = BasicModel.getRole(identityManager, applicationRole.getName());
		identityManager.remove(role_);
	}
}
