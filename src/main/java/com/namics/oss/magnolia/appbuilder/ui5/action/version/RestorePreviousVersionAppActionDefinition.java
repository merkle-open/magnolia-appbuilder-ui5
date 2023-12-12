package com.namics.oss.magnolia.appbuilder.ui5.action.version;

import com.namics.oss.magnolia.appbuilder.ui5.action.AppActionDefinition;
import com.namics.oss.magnolia.appbuilder.ui5.action.rule.PermissionRequiredRuleDefinition;
import com.namics.oss.magnolia.appbuilder.ui5.builder.generated.availability.AvailabilityBuilder;
import com.namics.oss.magnolia.appbuilder.ui5.builder.generated.availability.AvailabilityRuleBuilder;
import info.magnolia.cms.security.Permission;
import info.magnolia.ui.api.action.ConfiguredActionDefinition;
import info.magnolia.ui.contentapp.detail.action.RestorePreviousVersionActionDefinition;
import info.magnolia.ui.framework.availability.IsDeletedRule;

public class RestorePreviousVersionAppActionDefinition implements AppActionDefinition {

	@Override
	public ConfiguredActionDefinition action() {
		ConfiguredActionDefinition actionDefinition = new RestorePreviousVersionActionDefinition();
		actionDefinition.setName("restorePreviousVersion");
		actionDefinition.setLabel("actions.restorePreviousVersion");
		actionDefinition.setIcon("icon-undo");
		actionDefinition.setAvailability(new AvailabilityBuilder()
				.writePermissionRequired(true)
				.rules(
						new PermissionRequiredRuleDefinition(Permission.WRITE),
						new AvailabilityRuleBuilder().implementationClass(IsDeletedRule.class)
				)
		);
		return actionDefinition;
	}
}
