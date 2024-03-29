package com.namics.oss.magnolia.appbuilder.ui5.builder.generated.action;

import info.magnolia.ui.api.action.Action;
import info.magnolia.ui.api.availability.AvailabilityDefinition;
import info.magnolia.ui.framework.action.AddPropertyActionDefinition;

import javax.annotation.processing.Generated;

@Generated(
		value = "DefinitionExtender",
		date = "2020-06-15T13:24:01.665113"
)
public class AddPropertyActionBuilder extends AddPropertyActionDefinition {
	public AddPropertyActionBuilder name(String name) {
		this.setName(name);
		return this;
	}

	public AddPropertyActionBuilder i18nBasename(String i18nBasename) {
		this.setI18nBasename(i18nBasename);
		return this;
	}

	public AddPropertyActionBuilder availability(AvailabilityDefinition availability) {
		this.setAvailability(availability);
		return this;
	}

	public AddPropertyActionBuilder successMessage(String successMessage) {
		this.setSuccessMessage(successMessage);
		return this;
	}

	public AddPropertyActionBuilder failureMessage(String failureMessage) {
		this.setFailureMessage(failureMessage);
		return this;
	}

	public AddPropertyActionBuilder errorMessage(String errorMessage) {
		this.setErrorMessage(errorMessage);
		return this;
	}

	public AddPropertyActionBuilder label(String label) {
		this.setLabel(label);
		return this;
	}

	public AddPropertyActionBuilder icon(String icon) {
		this.setIcon(icon);
		return this;
	}

	public AddPropertyActionBuilder implementationClass(Class<? extends Action> implementationClass) {
		this.setImplementationClass(implementationClass);
		return this;
	}

	public AddPropertyActionBuilder description(String description) {
		this.setDescription(description);
		return this;
	}
}
