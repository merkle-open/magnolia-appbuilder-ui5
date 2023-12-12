package com.namics.oss.magnolia.appbuilder.ui5.action.edit;

import com.namics.oss.magnolia.appbuilder.ui5.MgnlIcon;
import com.namics.oss.magnolia.appbuilder.ui5.action.AppActionDefinition;
import com.namics.oss.magnolia.appbuilder.ui5.action.rule.PermissionRequiredRuleDefinition;
import com.namics.oss.magnolia.appbuilder.ui5.builder.generated.action.deprecated.DeleteConfirmationActionBuilder;
import com.namics.oss.magnolia.appbuilder.ui5.builder.generated.availability.AvailabilityBuilder;
import com.namics.oss.magnolia.appbuilder.ui5.builder.generated.availability.AvailabilityRuleBuilder;
import info.magnolia.cms.security.Permission;
import info.magnolia.ui.api.action.ConfiguredActionDefinition;
import info.magnolia.ui.framework.availability.IsNotDeletedRule;

public class ConfirmDeleteAppActionDefinition implements AppActionDefinition {
	private final String icon;

	public ConfirmDeleteAppActionDefinition() {
		this(MgnlIcon.DELETE);
	}

	public ConfirmDeleteAppActionDefinition(final String icon) {
		this.icon = icon;
	}

	@Override
	public ConfiguredActionDefinition action() {
		return new DeleteConfirmationActionBuilder()
				.name("confirmDelete")
				.label("actions.confirmDeletion")
				.confirmationHeader("actions.confirmDeletion.confirmationHeader")
				.confirmationMessage("actions.confirmDeletion.confirmationMessage")
				.proceedLabel("actions.confirmDeletion.proceedLabel")
				.cancelLabel("actions.confirmDeletion.cancelLabel")
				.icon(icon)
				.successActionName("delete")
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
