package com.namics.oss.magnolia.appbuilder.ui5.action.activation;

import info.magnolia.ui.api.action.ConfiguredActionDefinition;
import info.magnolia.ui.framework.availability.IsDeletedRule;

import com.namics.oss.magnolia.appbuilder.ui5.MgnlIcon;
import com.namics.oss.magnolia.appbuilder.ui5.action.AppActionDefinition;
import com.namics.oss.magnolia.appbuilder.ui5.builder.generated.action.ActivationActionBuilder;
import com.namics.oss.magnolia.appbuilder.ui5.builder.generated.availability.AvailabilityBuilder;
import com.namics.oss.magnolia.appbuilder.ui5.builder.generated.availability.AvailabilityRuleBuilder;
import com.namics.oss.magnolia.appbuilder.ui5.builder.generated.permission.AccessBuilder;

public class ActivateDeletionAppActionDefinition implements AppActionDefinition {

	@Override
	public ConfiguredActionDefinition action() {
		return new ActivationActionBuilder()
				.name("activateDeletion")
				.label("actions.activateDeleted")
				.icon(MgnlIcon.PUBLISH)
				.command("activate")
				.successMessage("appbuilder.ui5.actions.activateDeletion.success")
				.errorMessage("appbuilder.ui5.actions.activateDeletion.failure")
				.availability(new AvailabilityBuilder()
						.access(new AccessBuilder().roles("editor", "publisher"))
						.writePermissionRequired(true)
						.rules(
								new AvailabilityRuleBuilder().implementationClass(IsDeletedRule.class)
						)
				);
	}

	@Override
	public boolean multiple() {
		return true;
	}
}
