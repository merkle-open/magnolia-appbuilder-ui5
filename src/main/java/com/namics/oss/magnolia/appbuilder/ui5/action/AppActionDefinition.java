package com.namics.oss.magnolia.appbuilder.ui5.action;

import info.magnolia.ui.api.action.ConfiguredActionDefinition;

public interface AppActionDefinition {

	ConfiguredActionDefinition action();

	/**
	 * Return true if this action should be shown if multiple nodes are selected
	 */
	default boolean multiple() {
		return false;
	}

	/**
	 * Return true if this action is a callback action, which shouldn't be shown. (e.g. Delete action, which is triggered by ConfirmDelete action)
	 */
	default boolean isCallback() {
		return false;
	}
}
