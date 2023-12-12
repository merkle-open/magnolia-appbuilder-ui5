package com.namics.oss.magnolia.appbuilder.ui5.launcher;

import info.magnolia.ui.api.app.launcherlayout.ConfiguredAppLauncherGroupEntryDefinition;

public class OrderableAppLauncherGroupEntryDefinition extends ConfiguredAppLauncherGroupEntryDefinition {

	private int order = -1;

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}
}
