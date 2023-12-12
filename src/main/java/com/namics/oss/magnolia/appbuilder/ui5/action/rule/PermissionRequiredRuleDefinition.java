package com.namics.oss.magnolia.appbuilder.ui5.action.rule;

import info.magnolia.ui.api.availability.AvailabilityRule;
import info.magnolia.ui.api.availability.AvailabilityRuleDefinition;

public class PermissionRequiredRuleDefinition implements AvailabilityRuleDefinition {
	private final long requiredPermissions;

	public PermissionRequiredRuleDefinition(final long requiredPermissions) {
		this.requiredPermissions = requiredPermissions;
	}

	@Override
	public Class<? extends AvailabilityRule> getImplementationClass() {
		return PermissionRequiredRule.class;
	}

	public long getRequiredPermissions() {
		return requiredPermissions;
	}
}
