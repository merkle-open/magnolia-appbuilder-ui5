package com.namics.oss.magnolia.appbuilder.ui5.formatter;

import com.vaadin.v7.ui.Table;
import info.magnolia.jcr.util.NodeUtil;
import info.magnolia.ui.workbench.column.definition.PropertyColumnDefinition;
import org.apache.commons.lang3.StringUtils;

import javax.jcr.Item;
import javax.jcr.Node;
import java.util.Optional;

public abstract class AbstractColumnFormatter extends info.magnolia.ui.workbench.column.AbstractColumnFormatter<PropertyColumnDefinition> {

	protected AbstractColumnFormatter(final PropertyColumnDefinition definition) {
		super(definition);
	}

	@Override
	public final Object generateCell(final Table table, final Object itemParam, final Object columnId) {
		return Optional
				.ofNullable(getJcrItem(table, itemParam))
				.filter(Item::isNode)
				.map(item -> (Node) item)
				.map(item ->
						format(item, (String) columnId).orElseGet(() -> NodeUtil.getName(item))
				)
				.orElse(StringUtils.EMPTY);
	}

	/**
	 * Formats an item at a column
	 *
	 * @return formatted item. If empty optional is returned, the node name is used.
	 */
	protected abstract Optional<String> format(Node item, String columnId);
}
