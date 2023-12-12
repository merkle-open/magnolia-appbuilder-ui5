package com.namics.oss.magnolia.appbuilder.ui5.builder.generated.actionbar;

import info.magnolia.ui.actionbar.definition.ActionbarItemDefinition;
import info.magnolia.ui.actionbar.definition.ConfiguredActionbarGroupDefinition;

import javax.annotation.processing.Generated;
import java.util.Arrays;
import java.util.List;

@Generated(
		value = "DefinitionExtender",
		date = "2020-06-15T13:24:01.266649"
)
public class ActionbarGroupBuilder extends ConfiguredActionbarGroupDefinition {
	public ActionbarGroupBuilder name(String name) {
		this.setName(name);
		return this;
	}

	public ActionbarGroupBuilder items(List<ActionbarItemDefinition> items) {
		this.setItems(items);
		return this;
	}

	public ActionbarGroupBuilder item(ActionbarItemDefinition item) {
		this.addItem(item);
		return this;
	}

	public ActionbarGroupBuilder items(ActionbarItemDefinition... items) {
		this.setItems(Arrays.asList(items));
		return this;
	}
}
