package com.toparchy.atom.permission.model;

import static org.picketlink.idm.model.annotation.IdentityStereotype.Stereotype.USER;

import org.picketlink.idm.model.annotation.AttributeProperty;
import org.picketlink.idm.model.annotation.IdentityStereotype;
import org.picketlink.idm.model.basic.Agent;

@IdentityStereotype(USER)
public class Device extends Agent {

	private static final long serialVersionUID = 8265338322893127199L;
	@AttributeProperty
	private String deviceType;
	@AttributeProperty
	private String channelId;
	@AttributeProperty
	private String userId;
	@AttributeProperty
	private String state;

	public Device() {
	}

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

}
