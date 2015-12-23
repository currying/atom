package com.toparchy.atom.service;

import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;

import org.picketlink.idm.IdentityManager;
import org.picketlink.idm.PartitionManager;
import org.picketlink.idm.credential.Password;

import com.toparchy.atom.permission.model.Member;

@Stateless
public class MemberRegistration {

	@Inject
	private Logger log;
	@Inject
	private PartitionManager partitionManager;

	@Inject
	private Event<Member> memberEventSrc;

	public void register(Member member) throws Exception {
		log.info("Registering " + member.getLoginName());
		IdentityManager identityManager = this.partitionManager.createIdentityManager();
		identityManager.add(member);
		identityManager.updateCredential(member, new Password(member.getPassWord()));
		memberEventSrc.fire(member);
	}
}
