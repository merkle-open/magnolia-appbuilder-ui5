package com.namics.oss.magnolia.appbuilder.ui5.action.activation.workflow;

import com.namics.oss.magnolia.appbuilder.ui5.MgnlIcon;
import com.namics.oss.magnolia.appbuilder.ui5.action.AppActionDefinition;
import com.namics.oss.magnolia.appbuilder.ui5.builder.generated.availability.AvailabilityBuilder;
import com.namics.oss.magnolia.appbuilder.ui5.builder.generated.availability.AvailabilityRuleBuilder;
import com.namics.oss.magnolia.appbuilder.ui5.builder.generated.permission.AccessBuilder;
import info.magnolia.module.workflow.action.OpenPublicationDialogActionDefinition;
import info.magnolia.ui.api.action.ConfiguredActionDefinition;
import info.magnolia.ui.framework.availability.IsNotDeletedRule;
import info.magnolia.ui.framework.availability.IsPublishableRule;

import java.util.Map;

public class WorkflowActivateAppActionDefinition implements AppActionDefinition {
    private final Map<String, Class<?>> formTypes;

    public WorkflowActivateAppActionDefinition() {
        this(FormTypeProvider.DEFAULT_FORM_TYPES);
    }

    public WorkflowActivateAppActionDefinition(final Map<String, Class<?>> formTypes) {
        this.formTypes = formTypes;
    }

    @Override
    public ConfiguredActionDefinition action() {
        final OpenPublicationDialogActionDefinition action = new OpenPublicationDialogActionDefinition();
        action.setCommand("activate");
        action.setCatalog("workflow");
        action.setName("activate");
        action.setLabel("actions.activate");
        action.setDialogName("workflow:publish");
        action.setIcon(MgnlIcon.PUBLISH);
        action.setFormTypes(FormTypeProvider.getFormTypes(formTypes));
        action.setAvailability(new AvailabilityBuilder()
                .access(new AccessBuilder().roles("editor", "publisher"))
                .writePermissionRequired(true)
                .rules(
                        new AvailabilityRuleBuilder().implementationClass(IsNotDeletedRule.class),
                        new AvailabilityRuleBuilder().implementationClass(IsPublishableRule.class)
                )
        );
        return action;
    }

    @Override
    public boolean multiple() {
        return true;
    }
}
