package com.toparchy.atom.permission.data;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.event.Reception;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import com.toparchy.atom.permission.model.entity.ApplicationRoleEntity;

@ApplicationScoped
public class ApplicationRoleListProducer {

	@Inject
	private ApplicationRoleRepository applicationRoleRepository;

	private List<ApplicationRoleEntity> applicationRoles;

	@Produces
	@Named
	public List<ApplicationRoleEntity> getApplicationRoles() {
		return applicationRoles;
	}

	public void onApplicationRoleRepositoryListChanged(
			@Observes(notifyObserver = Reception.IF_EXISTS) final ApplicationRoleEntity role) {
		retrieveAllApplicationRoleRepositorys();
	}

	@PostConstruct
	public void retrieveAllApplicationRoleRepositorys() {
		applicationRoles = applicationRoleRepository.findAll();
	}

}
