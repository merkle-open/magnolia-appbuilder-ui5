package com.namics.oss.magnolia.appbuilder.ui5.builder.generated.permission;

import info.magnolia.cms.security.operations.ConfiguredAccessDefinition;

import javax.annotation.processing.Generated;
import java.util.Arrays;
import java.util.Collection;

@Generated(
		value = "DefinitionExtender",
		date = "2020-06-15T13:24:02.008905"
)
public class AccessBuilder extends ConfiguredAccessDefinition {
	public AccessBuilder roles(Collection<String> roles) {
		this.setRoles(roles);
		return this;
	}

	public AccessBuilder role(String role) {
		this.addRole(role);
		return this;
	}

	public AccessBuilder superuserRole(String superuserRole) {
		this.setSuperuserRole(superuserRole);
		return this;
	}

	public AccessBuilder roles(String... roles) {
		this.setRoles(Arrays.asList(roles));
		return this;
	}
}
