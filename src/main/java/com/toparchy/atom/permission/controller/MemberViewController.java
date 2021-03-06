package com.toparchy.atom.permission.controller;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.picketlink.idm.model.basic.Group;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import com.toparchy.atom.permission.data.MemberRepository;
import com.toparchy.atom.permission.model.Member;
import com.toparchy.atom.permission.model.entity.ApplicationRoleEntity;
import com.toparchy.atom.permission.service.MemberRoleRegistration;

@Model
@ViewScoped
public class MemberViewController implements Serializable {

	private static final long serialVersionUID = -3942443699486324323L;

	@Inject
	private MemberRoleRegistration memberRoleRegistration;
	@Inject
	private MemberRepository memberRepository;
	private boolean disabled = true;
	@Produces
	@Named
	private Member currentMember;
	@Produces
	@Named
	private List<Group> currentGroups;
	@Produces
	@Named
	private List<ApplicationRoleEntity> currentRoles;

	public Member getCurrentMember() {
		return currentMember;
	}

	public void setCurrentMember(Member currentMember) {
		this.currentMember = currentMember;
	}

	public List<Group> getCurrentGroups() {
		return currentGroups;
	}

	public void setCurrentGroups(List<Group> currentGroups) {
		this.currentGroups = currentGroups;
	}

	public List<ApplicationRoleEntity> getCurrentRoles() {
		return currentRoles;
	}

	public void setCurrentRoles(List<ApplicationRoleEntity> currentRoles) {
		this.currentRoles = currentRoles;
	}

	public void onRowSelect(SelectEvent event) {
		currentGroups = memberRepository.findMemberGroup((Member) event.getObject());
		currentRoles = memberRepository.findRoleFromMember((Member) event.getObject());
		if (currentMember != null)
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

	public void chooseRole() {
		RequestContext.getCurrentInstance().openDialog("selectApplicationRole");
	}

	public void selectRoleFromDialog(ApplicationRoleEntity applicationRole) {
		RequestContext.getCurrentInstance().closeDialog(applicationRole);
	}

	public void onRoleChosen(SelectEvent event) {
		ApplicationRoleEntity role = (ApplicationRoleEntity) event.getObject();
		memberRoleRegistration.grantRoleToMember(currentMember, role);
		currentRoles.add(role);
	}

	public void revokeRoleFromMember(ApplicationRoleEntity role) {
		memberRoleRegistration.revokeRoleFromMember(currentMember, role);
		currentRoles.remove(role);
	}
}
