package com.namics.oss.magnolia.appbuilder.ui5.action.activation;

import info.magnolia.ui.api.action.ConfiguredActionDefinition;
import info.magnolia.ui.framework.availability.IsNotDeletedRule;
import info.magnolia.ui.framework.availability.IsPublishedRule;

import com.namics.oss.magnolia.appbuilder.ui5.MgnlIcon;
import com.namics.oss.magnolia.appbuilder.ui5.action.AppActionDefinition;
import com.namics.oss.magnolia.appbuilder.ui5.builder.generated.action.deprecated.DeactivationActionBuilder;
import com.namics.oss.magnolia.appbuilder.ui5.builder.generated.availability.AvailabilityBuilder;
import com.namics.oss.magnolia.appbuilder.ui5.builder.generated.availability.AvailabilityRuleBuilder;
import com.namics.oss.magnolia.appbuilder.ui5.builder.generated.permission.AccessBuilder;

public class DeactivateAppActionDefinition implements AppActionDefinition {

	@Override
	public ConfiguredActionDefinition action() {
		return new DeactivationActionBuilder()
				.name("deactivate")
				.label("actions.deactivate")
				.icon(MgnlIcon.UNPUBLISH)
				.command("deactivate")
				.successMessage("appbuilder.ui5.actions.deactivate.success")
				.errorMessage("appbuilder.ui5.actions.deactivate.failure")
				.availability(new AvailabilityBuilder()
						.access(new AccessBuilder().roles("editor", "publisher"))
						.writePermissionRequired(true)
						.rules(
								new AvailabilityRuleBuilder().implementationClass(IsNotDeletedRule.class),
								new AvailabilityRuleBuilder().implementationClass(IsPublishedRule.class)
						)
				);
	}

	@Override
	public boolean multiple() {
		return true;
	}
}
