package com.namics.oss.magnolia.appbuilder.ui5.action.edit;

import com.namics.oss.magnolia.appbuilder.ui5.MgnlIcon;
import com.namics.oss.magnolia.appbuilder.ui5.action.AppActionDefinition;
import com.namics.oss.magnolia.appbuilder.ui5.action.rule.PermissionRequiredRuleDefinition;
import com.namics.oss.magnolia.appbuilder.ui5.builder.generated.action.DeleteItemActionBuilder;
import com.namics.oss.magnolia.appbuilder.ui5.builder.generated.availability.AvailabilityBuilder;
import com.namics.oss.magnolia.appbuilder.ui5.builder.generated.availability.AvailabilityRuleBuilder;
import info.magnolia.cms.security.Permission;
import info.magnolia.ui.api.action.ConfiguredActionDefinition;
import info.magnolia.ui.framework.availability.IsNotDeletedRule;

public class DeleteItemAppActionDefinition implements AppActionDefinition {
	private final String icon;

	public DeleteItemAppActionDefinition() {
		this(MgnlIcon.DELETE);
	}

	public DeleteItemAppActionDefinition(final String icon) {
		this.icon = icon;
	}

	@Override
	public ConfiguredActionDefinition action() {
		return new DeleteItemActionBuilder()
				.name("deleteItem")
				.label("actions.delete")
				.icon(icon)
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
}