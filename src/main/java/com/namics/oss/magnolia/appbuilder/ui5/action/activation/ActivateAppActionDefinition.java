package com.namics.oss.magnolia.appbuilder.ui5.action.activation;

import info.magnolia.ui.api.action.ConfiguredActionDefinition;
import info.magnolia.ui.framework.availability.IsNotDeletedRule;
import info.magnolia.ui.framework.availability.IsPublishableRule;

import com.namics.oss.magnolia.appbuilder.ui5.MgnlIcon;
import com.namics.oss.magnolia.appbuilder.ui5.action.AppActionDefinition;
import com.namics.oss.magnolia.appbuilder.ui5.builder.generated.action.ActivationActionBuilder;
import com.namics.oss.magnolia.appbuilder.ui5.builder.generated.availability.AvailabilityBuilder;
import com.namics.oss.magnolia.appbuilder.ui5.builder.generated.availability.AvailabilityRuleBuilder;
import com.namics.oss.magnolia.appbuilder.ui5.builder.generated.permission.AccessBuilder;

public class ActivateAppActionDefinition implements AppActionDefinition {

	@Override
	public ConfiguredActionDefinition action() {
		return new ActivationActionBuilder()
				.name("activate")
				.label("actions.activate")
				.icon(MgnlIcon.PUBLISH)
				.command("activate")
				.successMessage("appbuilder.ui5.actions.activate.success")
				.errorMessage("appbuilder.ui5.actions.activate.failure")
				.availability(new AvailabilityBuilder()
						.access(new AccessBuilder().roles("editor", "publisher"))
						.writePermissionRequired(true)
						.rules(
								new AvailabilityRuleBuilder().implementationClass(IsNotDeletedRule.class),
								new AvailabilityRuleBuilder().implementationClass(IsPublishableRule.class)
						)
				);
	}

	@Override
	public boolean multiple() {
		return true;
	}
}
