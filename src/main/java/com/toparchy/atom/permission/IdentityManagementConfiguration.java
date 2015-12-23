package com.toparchy.atom.permission;

import javax.enterprise.inject.Produces;

import org.picketlink.idm.config.IdentityConfiguration;
import org.picketlink.idm.config.IdentityConfigurationBuilder;

import com.toparchy.atom.permission.model.Member;

public class IdentityManagementConfiguration {

	@Produces
	IdentityConfiguration produceIdentityManagementConfiguration() {
		IdentityConfigurationBuilder builder = new IdentityConfigurationBuilder();

		builder.named("default").stores().jpa().supportType(Member.class)
				.supportAllFeatures();

		return builder.build();
	}

}
