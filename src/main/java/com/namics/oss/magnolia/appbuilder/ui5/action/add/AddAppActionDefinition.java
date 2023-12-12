package com.namics.oss.magnolia.appbuilder.ui5.action.add;

import com.namics.oss.magnolia.appbuilder.ui5.MgnlIcon;
import com.namics.oss.magnolia.appbuilder.ui5.action.AppActionDefinition;
import com.namics.oss.magnolia.appbuilder.ui5.action.rule.PermissionRequiredRuleDefinition;
import com.namics.oss.magnolia.appbuilder.ui5.builder.generated.action.OpenCreateDialogActionBuilder;
import com.namics.oss.magnolia.appbuilder.ui5.builder.generated.availability.AvailabilityBuilder;
import com.namics.oss.magnolia.appbuilder.ui5.builder.generated.availability.AvailabilityRuleBuilder;
import info.magnolia.cms.security.Permission;
import info.magnolia.jcr.util.NodeTypes;
import info.magnolia.ui.api.action.ConfiguredActionDefinition;
import info.magnolia.ui.framework.availability.IsNotDeletedRule;

import javax.annotation.Nullable;

public class AddAppActionDefinition implements AppActionDefinition {
	public static AddAppActionDefinition FOLDER = new AddAppActionDefinition(
			"addFolder",
			NodeTypes.Folder.NAME,
			"ui-framework:folder",
			MgnlIcon.ADD_FOLDER,
			"actions.addFolder"
	);
	private final String name;
	private final String nodeType;
	private final String dialogName;
	private final String icon;
	@Nullable
	private final String label;

	public AddAppActionDefinition(
			final String name,
			final String nodeType,
			final String dialogName,
			final String icon) {
		this(name, nodeType, dialogName, icon, null);
	}

	public AddAppActionDefinition(
			final String name,
			final String nodeType,
			final String dialogName,
			final String icon,
			@Nullable final String label) {
		this.name = name;
		this.nodeType = nodeType;
		this.dialogName = dialogName;
		this.icon = icon;
		this.label = label;
	}

	@Override
	public ConfiguredActionDefinition action() {
		return new OpenCreateDialogActionBuilder()
				.name(name)
				.label(label)
				.dialogName(dialogName)
				.icon(icon)
				.nodeType(nodeType)
				.availability(new AvailabilityBuilder()
						.rules(
								new PermissionRequiredRuleDefinition(Permission.ADD),
								new AvailabilityRuleBuilder().implementationClass(IsNotDeletedRule.class)
						)
				);
	}
}
