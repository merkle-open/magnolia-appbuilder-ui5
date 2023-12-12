package com.namics.oss.magnolia.appbuilder.ui5.action.importexport;

import com.namics.oss.magnolia.appbuilder.ui5.MgnlIcon;
import com.namics.oss.magnolia.appbuilder.ui5.action.AppActionDefinition;
import com.namics.oss.magnolia.appbuilder.ui5.action.rule.PermissionRequiredRuleDefinition;
import com.namics.oss.magnolia.appbuilder.ui5.builder.generated.action.OpenCreateDialogActionBuilder;
import com.namics.oss.magnolia.appbuilder.ui5.builder.generated.availability.AvailabilityBuilder;
import com.namics.oss.magnolia.appbuilder.ui5.builder.generated.availability.AvailabilityRuleBuilder;
import info.magnolia.cms.security.Permission;
import info.magnolia.ui.api.action.ConfiguredActionDefinition;
import info.magnolia.ui.framework.availability.IsNotDeletedRule;

public class ImportAppActionDefinition implements AppActionDefinition {

	@Override
	public ConfiguredActionDefinition action() {
		return new OpenCreateDialogActionBuilder()
				.name("import")
				.label("actions.import")
				.icon(MgnlIcon.IMPORT)
				.dialogName("ui-admincentral:import")
				.availability(new AvailabilityBuilder()
						.rules(
								new PermissionRequiredRuleDefinition(Permission.WRITE),
								new AvailabilityRuleBuilder().implementationClass(IsNotDeletedRule.class)
						)
				);
	}

	@Override
	public boolean multiple() {
		return true;
	}
}
