package com.namics.oss.magnolia.appbuilder.ui5.action.activation.workflow;

import com.namics.oss.magnolia.appbuilder.ui5.MgnlIcon;
import com.namics.oss.magnolia.appbuilder.ui5.action.AppActionDefinition;
import com.namics.oss.magnolia.appbuilder.ui5.builder.generated.availability.AvailabilityBuilder;
import com.namics.oss.magnolia.appbuilder.ui5.builder.generated.availability.AvailabilityRuleBuilder;
import com.namics.oss.magnolia.appbuilder.ui5.builder.generated.permission.AccessBuilder;
import info.magnolia.module.workflow.action.OpenPublicationDialogActionDefinition;
import info.magnolia.ui.api.action.ConfiguredActionDefinition;
import info.magnolia.ui.framework.availability.IsDeletedRule;

import java.util.Map;

public class WorkflowActivateDeletionAppActionDefinition implements AppActionDefinition {
    private final Map<String, Class<?>> formTypes;

    public WorkflowActivateDeletionAppActionDefinition() {
        this(FormTypeProvider.DEFAULT_FORM_TYPES);
    }

    public WorkflowActivateDeletionAppActionDefinition(final Map<String, Class<?>> formTypes) {
        this.formTypes = formTypes;
    }

    @Override
    public ConfiguredActionDefinition action() {
        final OpenPublicationDialogActionDefinition action = new OpenPublicationDialogActionDefinition();
        action.setCommand("activate");
        action.setCatalog("workflow");
        action.setName("activateDeletion");
        action.setLabel("actions.activateDeleted");
        action.setDialogName("workflow:publishDeletion");
        action.setIcon(MgnlIcon.PUBLISH);
        action.setFormTypes(FormTypeProvider.getFormTypes(formTypes));
        action.setAvailability(new AvailabilityBuilder()
                .access(new AccessBuilder().roles("editor", "publisher"))
                .writePermissionRequired(true)
                .rules(
                        new AvailabilityRuleBuilder().implementationClass(IsDeletedRule.class)
                )
        );
        return action;
    }

    @Override
    public boolean multiple() {
        return true;
    }
}
