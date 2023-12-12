package com.namics.oss.magnolia.appbuilder.ui5.action.activation;

import com.namics.oss.magnolia.appbuilder.ui5.MgnlIcon;
import com.namics.oss.magnolia.appbuilder.ui5.action.AppActionDefinition;
import com.namics.oss.magnolia.appbuilder.ui5.builder.generated.action.ActivationActionBuilder;
import com.namics.oss.magnolia.appbuilder.ui5.builder.generated.availability.AvailabilityBuilder;
import com.namics.oss.magnolia.appbuilder.ui5.builder.generated.availability.AvailabilityRuleBuilder;
import com.namics.oss.magnolia.appbuilder.ui5.builder.generated.permission.AccessBuilder;
import info.magnolia.ui.api.action.ConfiguredActionDefinition;
import info.magnolia.ui.framework.availability.IsNotDeletedRule;
import info.magnolia.ui.framework.availability.IsPublishableRule;

public class ActivateRecursiveAppActionDefinition implements AppActionDefinition {

	@Override
	public ConfiguredActionDefinition action() {
		return new ActivationActionBuilder()
				.name("activateRecursive")
				.label("actions.activateRecursive")
				.icon(MgnlIcon.PUBLISH_INCL_SUB)
				.command("activate")
				.recursive(true)
				.asynchronous(true)
				.successMessage("appbuilder.actions.activateRecursive.success")
				.errorMessage("appbuilder.actions.activateRecursive.failure")
				.availability(new AvailabilityBuilder()
						.access(new AccessBuilder().roles("editor", "publisher"))
						.writePermissionRequired(true)
						.rules(
								new AvailabilityRuleBuilder().implementationClass(IsPublishableRule.class),
								new AvailabilityRuleBuilder().implementationClass(IsNotDeletedRule.class)
						)
				);
	}

	@Override
	public boolean multiple() {
		return true;
	}
}
