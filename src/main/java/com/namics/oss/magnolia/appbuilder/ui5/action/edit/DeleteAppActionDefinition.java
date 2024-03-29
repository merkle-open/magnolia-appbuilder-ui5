package com.namics.oss.magnolia.appbuilder.ui5.action.edit;

import com.namics.oss.magnolia.appbuilder.ui5.action.AppActionDefinition;
import com.namics.oss.magnolia.appbuilder.ui5.action.rule.PermissionRequiredRuleDefinition;
import com.namics.oss.magnolia.appbuilder.ui5.builder.generated.action.DeleteActionBuilder;
import com.namics.oss.magnolia.appbuilder.ui5.builder.generated.availability.AvailabilityBuilder;
import com.namics.oss.magnolia.appbuilder.ui5.builder.generated.availability.AvailabilityRuleBuilder;
import info.magnolia.cms.security.Permission;
import info.magnolia.ui.api.action.ConfiguredActionDefinition;
import info.magnolia.ui.framework.availability.IsNotDeletedRule;

public class DeleteAppActionDefinition implements AppActionDefinition {

	@Override
	public ConfiguredActionDefinition action() {
		return new DeleteActionBuilder()
				.name("delete")
				.label("actions.delete")
				.asynchronous(true)
				.availability(new AvailabilityBuilder()
						.rules(
								new PermissionRequiredRuleDefinition(Permission.REMOVE),
								new AvailabilityRuleBuilder().implementationClass(IsNotDeletedRule.class)
						)
				);
	}

	@Override
	public boolean multiple() {
		return true;
	}

	@Override
	public boolean isCallback() {
		return true;
	}
}
