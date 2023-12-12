# Magnolia Appbuilder

The AppBuilder module is a builder for Magnolia apps in a Blossom 
context comparable to the Blossom DialogBuilder.

Instead of using YAML, it allows to define apps in Java which is less 
error prone than using YAML, especially for big apps.

## Requirements
* Java 11
* Spring >=5
* Magnolia >= 6.0
* Blossom >= 3.2

## Installation

* Add Maven dependency:
```xml
<dependency>
    <groupId>com.namics.oss.magnolia</groupId>
    <artifactId>magnolia-appbuilder</artifactId>
    <version>1.0.4</version>
</dependency>
```

* Import Spring configuration:
```java
[...]
@Configuration
@Import({AppBuilderConfiguration.class})
public class BlossomServletConfiguration {
	[...]
}
```

* Extend the component scan of the Spring configuration:
```java
@ComponentScan.Filter(AppFactory.class),
@ComponentScan.Filter(AppLauncherGroup.class)
```

## How to use

To create a new app, add a class with the `@AppFactory` annotation and at least one 
method annotated with `@SubApp` returning a `info.magnolia.ui.api.app.SubAppDescriptor`. Make sure the the class
in in a package which is scanned for `@AppFactory`s.

For a quick overview check the [examples](#examples) bellow.

### Annotations

#### AppFactory (Target: Class)
Marks a class as AppFactory. The annotation properties define the basic app properties
like 'name', 'label' and 'icon'.

This annotation also allows to place the app in a Launcher Group. It is possible
to create a new group, see ['Creating an App Launcher Group'](#creating-an-app-launcher-group),
and add the app to the newly created group, or to use an existing Launcher Group. 
The position of the app in the group can be configured with the 'order' annotation property.

#### SubApp (Target: Method)
The AppFactory requires at least one method marked with the `@SubApp` annotation
this method must return a `info.magnolia.ui.api.app.SubAppDescriptor`.

#### ChooseDialog, optional (Target: Method)
A method marked with `@ChooseDialog` must return a `info.magnolia.ui.dialog.definition.ChooseDialogDefinition`.

#### AppPermissions, optional (Target: Method)
A method marked with `@AppPermissions` must return a `info.magnolia.cms.security.operations.AccessDefinition`.

### Creating an App Launcher Group
Annotate a class with `@AppLauncherGroup`, and add the name as annotation property.
Optionally the class can be added a method with a `@GroupDefinition` annotation,
returning a `SimpleGroupDefinition`

### Multiple 'defaultActions' (double click actions)
The `NodeTypeToActionDelegatingAction` action wrapper allows to define 
'doubleclick-actions' per node type. Define the Action as follows:
* Define an action per node type
* Define a fallback action for not specified node types
* Set this action as defaultAction in the ActionBarDefinition
```java
[...]
"defaultAction", new NodeTypeToActionDelegatingActionBuilder()
		.fallbackAction("rename")
		.nodeTypeActionMapping(
				NodeTypes.Content.NAME, "rename",
				NodeTypes.ContentNode.NAME, "rename"
		)
[...]	
```
This action wrapper can be used in YAML files as well.

## How it works
The AppBuilder allows to create a app definition using the builder pattern.
The builder classes are automatically generated and extend the respective definition class.
This way, the whole AppBuilder is fully compatible to the definition classes
provided by Magnolia. 

## Examples
The following class is a demo app, made with the AppBuilder:
```java
import com.namics.oss.magnolia.appbuilder.ui5.MgnlIcon;
import com.namics.oss.magnolia.appbuilder.ui5.action.AppActionDefinitions;
import com.namics.oss.magnolia.appbuilder.ui5.action.AppActionGroupDefinition;
import com.namics.oss.magnolia.appbuilder.ui5.action.add.AddAppActionDefinition;
import com.namics.oss.magnolia.appbuilder.ui5.action.edit.EditAppActionDefinition;
import com.namics.oss.magnolia.appbuilder.ui5.annotations.AppFactory;
import com.namics.oss.magnolia.appbuilder.ui5.annotations.ChooseDialog;
import com.namics.oss.magnolia.appbuilder.ui5.annotations.SubApp;
import com.namics.oss.magnolia.appbuilder.ui5.builder.BrowserAppBuilder;
import com.namics.oss.magnolia.appbuilder.ui5.builder.generated.choosedialog.ChooseDialogBuilder;
import com.namics.oss.magnolia.appbuilder.ui5.builder.generated.column.MetaDataColumnBuilder;
import com.namics.oss.magnolia.appbuilder.ui5.builder.generated.column.PropertyColumnBuilder;
import com.namics.oss.magnolia.appbuilder.ui5.builder.generated.column.StatusColumnBuilder;
import com.namics.oss.magnolia.appbuilder.ui5.builder.generated.contentconnector.JcrContentConnectorBuilder;
import com.namics.oss.magnolia.appbuilder.ui5.builder.generated.contentconnector.NodeTypeBuilder;
import com.namics.oss.magnolia.appbuilder.ui5.launcher.group.LauncherGroup;
import info.magnolia.jcr.util.NodeTypes;
import info.magnolia.ui.api.app.SubAppDescriptor;
import info.magnolia.ui.dialog.definition.ChooseDialogDefinition;
import info.magnolia.ui.vaadin.integration.jcr.ModelConstants;
import info.magnolia.ui.workbench.column.DateColumnFormatter;
import info.magnolia.ui.workbench.column.StatusColumnFormatter;
import info.magnolia.ui.workbench.column.definition.ColumnDefinition;

@AppFactory(
		id = SampleApp.ID,
		name = SampleApp.NAME,
		label = SampleApp.NAME,
		icon = MgnlIcon.TAG_2_APP,
		launcherGroup = LauncherGroup.EDIT
)
public class SampleApp {
	public static final String NAME = "SampleApp";
	public static final String ID = "module:apps/" + NAME;

	private final ColumnDefinition[] columnDefinitions = new ColumnDefinition[]{
			new PropertyColumnBuilder()
					.name("name")
					.editable(false)
					.expandRatio(1)
					.propertyName(ModelConstants.JCR_NAME)
					.sortable(true),
			new StatusColumnBuilder()
					.name("status")
					.width(46)
					.displayInChooseDialog(false)
					.formatterClass(StatusColumnFormatter.class),
			new MetaDataColumnBuilder()
					.name("moddate")
					.displayInChooseDialog(false)
					.formatterClass(DateColumnFormatter.class)
					.propertyName(NodeTypes.LastModified.NAME)
					.sortable(true)
					.width(160)
	};

	@ChooseDialog
	public ChooseDialogDefinition getChooseDialog() {
		return new ChooseDialogBuilder().contentConnector(
				new JcrContentConnectorBuilder()
						.workspace("<WORKSPACE>")
						.defaultOrder("jcrName")
						.rootPath("/")
						.nodeTypes(
								new NodeTypeBuilder()
										.name("<NODE_TYPE>")
										.icon(MgnlIcon.OPEN_NEW_WINDOW)
						));
	}

	@SubApp
	public SubAppDescriptor getBrowser() {
		return new BrowserAppBuilder()
				.icon(MgnlIcon.TAG_2_APP)
				.columns(columnDefinitions)
				.dropConstraint(SampleNodeDropConstraint.class)
				.rootActions(
						new AppActionGroupDefinition("addingActions", AddAppActionDefinition.FOLDER),
						new AppActionGroupDefinition("activationActions", AppActionDefinitions.ACTIVATION),
						new AppActionGroupDefinition("importExportActions", AppActionDefinitions.IMPORT_EXPORT)
				)
				.nodeActions(
						NodeTypes.Folder.NAME,
						EditAppActionDefinition.FOLDER,
						new AppActionGroupDefinition("editActions", AppActionDefinitions.editActions(EditAppActionDefinition.FOLDER)),
						new AppActionGroupDefinition("activationActions", AppActionDefinitions.ACTIVATION),
						new AppActionGroupDefinition("importExportActions", AppActionDefinitions.IMPORT_EXPORT)
				)
				.build(
						"<WORKSPACE>",
						new NodeTypeBuilder()
								.name(NodeTypes.Folder.NAME)
								.icon(MgnlIcon.FOLDER)
				);
	}
}
```
DropConstraint sample:
```java
import com.namics.engagement.web.core.EngagementCoreTemplatingConstants;
import com.namics.oss.magnolia.appbuilder.ui5.dropconstraint.AbstractNodeDropConstraint;
import com.namics.oss.magnolia.powernode.PowerNodeService;
import info.magnolia.jcr.util.NodeTypes;

import javax.inject.Inject;
import java.util.Set;

public class SampleNodeDropConstraint extends AbstractNodeDropConstraint {

	@Inject
	public SampleNodeDropConstraint(final PowerNodeService powerNodeService) {
		super(
				powerNodeService,
				"<FOLDER_NODE_TYPE>",
				Set.of("<FILE_NODE_TYPE>")
		);
	}
}
```
ColumnFormatter sample:
```java
import com.namics.oss.magnolia.appbuilder.ui5.formatter.AbstractColumnFormatter;
import com.namics.oss.magnolia.powernode.PowerNode;
import com.namics.oss.magnolia.powernode.PowerNodeService;
import info.magnolia.ui.workbench.column.definition.PropertyColumnDefinition;

import javax.inject.Inject;
import java.util.Optional;

public class SampleColumnFormatter extends AbstractColumnFormatter {

	@Inject
	public SampleColumnFormatter(
			final PowerNodeService powerNodeService,
			final PropertyColumnDefinition definition) {
		super(powerNodeService, definition);
	}

	@Override
	protected Optional<String> format(final PowerNode item, final String columnId) {
		if (item.isNodeType("<SOME_NODE_TYPE>")) {
			return item.getPropertyValue("<SOME_FIELD>", String.class);
		}
		return Optional.empty();
	}
}
```
The following class creates an 'App Launcher Group':

```java
import com.namics.oss.magnolia.appbuilder.ui5.annotations.AppLauncherGroup;
import com.namics.oss.magnolia.appbuilder.ui5.annotations.GroupDefinition;
import com.namics.oss.magnolia.appbuilder.ui5.launcher.group.SimpleGroupDefinition;


@AppLauncherGroup(
		name = SampleGroup.NAME
)
public class SampleGroup {

	public static final String NAME = "sample";

	@GroupDefinition
	public SimpleGroupDefinition getDefinition() {
		return new SimpleGroupDefinition(NAME)
				.color("#e05343");
	}

}
```