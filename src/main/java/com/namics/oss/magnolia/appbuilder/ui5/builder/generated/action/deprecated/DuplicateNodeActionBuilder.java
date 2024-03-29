package com.namics.oss.magnolia.appbuilder.ui5.builder.generated.action.deprecated;

import info.magnolia.ui.api.action.Action;
import info.magnolia.ui.api.availability.AvailabilityDefinition;
import info.magnolia.ui.framework.action.DuplicateNodeActionDefinition;

import javax.annotation.processing.Generated;

/**
 * @deprecated see {@link info.magnolia.ui.framework.action.DuplicateNodeActionDefinition} for details
 */
@Generated(
		value = "DefinitionExtender",
		date = "2020-06-15T13:24:01.299533"
)
@Deprecated
public class DuplicateNodeActionBuilder extends DuplicateNodeActionDefinition {
	public DuplicateNodeActionBuilder name(String name) {
		this.setName(name);
		return this;
	}

	public DuplicateNodeActionBuilder i18nBasename(String i18nBasename) {
		this.setI18nBasename(i18nBasename);
		return this;
	}

	public DuplicateNodeActionBuilder availability(AvailabilityDefinition availability) {
		this.setAvailability(availability);
		return this;
	}

	public DuplicateNodeActionBuilder successMessage(String successMessage) {
		this.setSuccessMessage(successMessage);
		return this;
	}

	public DuplicateNodeActionBuilder failureMessage(String failureMessage) {
		this.setFailureMessage(failureMessage);
		return this;
	}

	public DuplicateNodeActionBuilder errorMessage(String errorMessage) {
		this.setErrorMessage(errorMessage);
		return this;
	}

	public DuplicateNodeActionBuilder label(String label) {
		this.setLabel(label);
		return this;
	}

	public DuplicateNodeActionBuilder icon(String icon) {
		this.setIcon(icon);
		return this;
	}

	public DuplicateNodeActionBuilder implementationClass(
			Class<? extends Action> implementationClass) {
		this.setImplementationClass(implementationClass);
		return this;
	}

	public DuplicateNodeActionBuilder description(String description) {
		this.setDescription(description);
		return this;
	}
}
