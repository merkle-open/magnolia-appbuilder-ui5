package com.namics.oss.magnolia.appbuilder.ui5.builder.generated.action;

import info.magnolia.ui.api.action.Action;
import info.magnolia.ui.api.availability.AvailabilityDefinition;
import info.magnolia.ui.contentapp.browser.action.ExpandNodeActionDefinition;

import javax.annotation.processing.Generated;

@Generated(
		value = "DefinitionExtender",
		date = "2020-06-15T13:24:01.858146"
)
public class ExpandNodeActionBuilder extends ExpandNodeActionDefinition {
	public ExpandNodeActionBuilder name(String name) {
		this.setName(name);
		return this;
	}

	public ExpandNodeActionBuilder i18nBasename(String i18nBasename) {
		this.setI18nBasename(i18nBasename);
		return this;
	}

	public ExpandNodeActionBuilder availability(AvailabilityDefinition availability) {
		this.setAvailability(availability);
		return this;
	}

	public ExpandNodeActionBuilder successMessage(String successMessage) {
		this.setSuccessMessage(successMessage);
		return this;
	}

	public ExpandNodeActionBuilder failureMessage(String failureMessage) {
		this.setFailureMessage(failureMessage);
		return this;
	}

	public ExpandNodeActionBuilder errorMessage(String errorMessage) {
		this.setErrorMessage(errorMessage);
		return this;
	}

	public ExpandNodeActionBuilder label(String label) {
		this.setLabel(label);
		return this;
	}

	public ExpandNodeActionBuilder icon(String icon) {
		this.setIcon(icon);
		return this;
	}

	public ExpandNodeActionBuilder implementationClass(Class<? extends Action> implementationClass) {
		this.setImplementationClass(implementationClass);
		return this;
	}

	public ExpandNodeActionBuilder description(String description) {
		this.setDescription(description);
		return this;
	}
}
