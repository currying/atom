package com.toparchy.atom.permission.service;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.toparchy.atom.permission.data.MemberEntityRepository;
import com.toparchy.atom.permission.model.entity.DeviceEntity;
import com.toparchy.atom.permission.rest.PushData;

@Stateless
public class DeviceBindService {
	@Inject
	private EntityManager atomEm;
	@Inject
	private MemberEntityRepository memberEntityRepository;

	public void deviceBind(String accountId, PushData pushData) {
		DeviceEntity device = new DeviceEntity();
		device.setChannelId(pushData.getPushChannelId());
		device.setDeviceType(pushData.getDeviceType());
		device.setState("1");
		device.setMemberEntity(memberEntityRepository.findById(accountId));
		atomEm.persist(device);
	}
}
