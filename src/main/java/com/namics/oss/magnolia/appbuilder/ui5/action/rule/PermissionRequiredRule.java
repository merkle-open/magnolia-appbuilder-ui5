package com.namics.oss.magnolia.appbuilder.ui5.action.rule;

import com.machinezoo.noexception.Exceptions;
import info.magnolia.cms.security.PermissionUtil;
import info.magnolia.ui.api.availability.AbstractAvailabilityRule;
import info.magnolia.ui.vaadin.integration.jcr.JcrItemId;
import info.magnolia.ui.vaadin.integration.jcr.JcrItemUtil;

import javax.inject.Inject;
import javax.jcr.Item;
import javax.jcr.Node;
import javax.jcr.Property;
import javax.jcr.RepositoryException;

public class PermissionRequiredRule extends AbstractAvailabilityRule {
	private final PermissionRequiredRuleDefinition definition;

	@Inject
	public PermissionRequiredRule(final PermissionRequiredRuleDefinition definition) {
		this.definition = definition;
	}

	@Override
	protected boolean isAvailableForItem(final Object itemId) {
		if (itemId instanceof JcrItemId) {
			return Exceptions.wrap().getAsBoolean(() -> {
				final Item jcrItem = JcrItemUtil.getJcrItem((JcrItemId) itemId);
				final Node node = getNode(jcrItem);
				return PermissionUtil.isGranted(node, definition.getRequiredPermissions());
			});
		}
		return false;
	}

	private Node getNode(final Item jcrItem) throws RepositoryException {
		if(jcrItem instanceof Property){
			return jcrItem.getParent();
		}
		return (Node) jcrItem;
	}
}