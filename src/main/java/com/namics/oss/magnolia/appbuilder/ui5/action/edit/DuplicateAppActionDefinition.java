package com.namics.oss.magnolia.appbuilder.ui5.action.edit;

import com.namics.oss.magnolia.appbuilder.ui5.MgnlIcon;
import com.namics.oss.magnolia.appbuilder.ui5.action.AppActionDefinition;
import com.namics.oss.magnolia.appbuilder.ui5.action.rule.PermissionRequiredRuleDefinition;
import com.namics.oss.magnolia.appbuilder.ui5.builder.generated.action.deprecated.DuplicateNodeActionBuilder;
import com.namics.oss.magnolia.appbuilder.ui5.builder.generated.availability.AvailabilityBuilder;
import com.namics.oss.magnolia.appbuilder.ui5.builder.generated.availability.AvailabilityRuleBuilder;
import info.magnolia.cms.security.Permission;
import info.magnolia.ui.api.action.ConfiguredActionDefinition;
import info.magnolia.ui.framework.availability.IsNotDeletedRule;

public class DuplicateAppActionDefinition implements AppActionDefinition {
	private final String icon;
	private final String label;

	public DuplicateAppActionDefinition() {
		this(MgnlIcon.DUPLICATE, "actions.duplicate");
	}

	public DuplicateAppActionDefinition(final String icon, final String label) {
		this.icon = icon;
		this.label = label;
	}

	@Override
	public ConfiguredActionDefinition action() {
		return new DuplicateNodeActionBuilder()
				.name("duplicate")
				.label(label)
				.icon(icon)
				.availability(new AvailabilityBuilder()
						.rules(
								new PermissionRequiredRuleDefinition(Permission.ADD),
								new AvailabilityRuleBuilder().implementationClass(IsNotDeletedRule.class)
						)
				);
	}
}
