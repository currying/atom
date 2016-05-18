package com.toparchy.atom.permission.controller;

import java.io.Serializable;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import com.toparchy.atom.permission.model.entity.ApplicationResource;
import com.toparchy.atom.permission.model.entity.ApplicationRoleEntity;
import com.toparchy.atom.permission.service.RoleResourceRegistration;

@Model
@ViewScoped
public class ApplicationRoleController implements Serializable {

	private static final long serialVersionUID = -7279682200727128738L;
	@Inject
	private RoleResourceRegistration roleResourceRegistration;
	private boolean disabled = true;
	@Produces
	@Named
	private ApplicationRoleEntity selectApplicationRole;
	@Produces
	@Named
	private Set<ApplicationResource> currentApplicationResources;
	@Produces
	@Named
	private ApplicationRoleEntity newApplicationRole;

	@PostConstruct
	public void initNewApplicationRole() {
		newApplicationRole = new ApplicationRoleEntity();
	}

	public ApplicationRoleEntity getSelectApplicationRole() {
		return selectApplicationRole;
	}

	public void setSelectApplicationRole(ApplicationRoleEntity selectApplicationRole) {
		this.selectApplicationRole = selectApplicationRole;
	}

	public Set<ApplicationResource> getCurrentApplicationResources() {
		return currentApplicationResources;
	}

	public void setCurrentApplicationResources(Set<ApplicationResource> currentApplicationResources) {
		this.currentApplicationResources = currentApplicationResources;
	}

	public void onRowSelect(SelectEvent event) {
		selectApplicationRole = (ApplicationRoleEntity) event.getObject();
		currentApplicationResources = selectApplicationRole.getApplicationResources();
		if (selectApplicationRole != null)
			disabled = false;
	}

	public boolean isDisabled() {
		return disabled;
	}

	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}

	public void onRowUnselect(UnselectEvent event) {
	}

	public void chooseResource() {
		RequestContext.getCurrentInstance().openDialog("selectApplicationResource");
	}

	public ApplicationRoleEntity getNewApplicationRole() {
		return newApplicationRole;
	}

	public void setNewApplicationRole(ApplicationRoleEntity newApplicationRole) {
		this.newApplicationRole = newApplicationRole;
	}

	public void onResourceChosen(SelectEvent event) {
		ApplicationResource resource = (ApplicationResource) event.getObject();
		roleResourceRegistration.add(selectApplicationRole, resource);
		selectApplicationRole.addApplicationResource(resource);
	}

	public void removeResourceFromRole(ApplicationResource applicationResource) {
		roleResourceRegistration.remove(selectApplicationRole, applicationResource);
		selectApplicationRole.removeApplicationResource(applicationResource);
	}

	public void createApplicationRole() {
		roleResourceRegistration.createRole(newApplicationRole);
		initNewApplicationRole();
	}

	public void deleteRole() {
		roleResourceRegistration.deleteRole(selectApplicationRole);
		currentApplicationResources = null;
	}
}
