package com.namics.oss.magnolia.appbuilder.ui5;

import com.namics.oss.magnolia.appbuilder.ui5.annotations.AppFactory;
import info.magnolia.module.blossom.support.AbstractBeanDetector;
import info.magnolia.ui.api.app.registry.AppDescriptorRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;

import javax.inject.Inject;
import java.lang.invoke.MethodHandles;

/**
 * Scans for app classes annotated with {@link AppFactory},
 * builds the app descriptions and registers the app.
 */
@Component("ui5-AppExporter")
public class AppExporter extends AbstractBeanDetector {
	private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	private final AppDescriptorRegistry appDescriptorRegistry;

	@Inject
	public AppExporter(final AppDescriptorRegistry appDescriptorRegistry) {
		this.appDescriptorRegistry = appDescriptorRegistry;
	}

	@Override
	protected boolean include(final Class<?> beanType, final String beanName) {
		return (ClassUtils.isCglibProxyClass(beanType) ? beanType.getSuperclass() : beanType).isAnnotationPresent(AppFactory.class);
	}

	@Override
	protected void onBeanDetection(final Object appFactory, final String beanName) {
		LOG.info("Detected app bean with name '{}'", beanName);
		// build app descriptor from detected factory bean
		final AppDescriptorProvider appDescriptorProvider = new AppDescriptorProvider(appFactory);
		// register app descriptor
		appDescriptorRegistry.register(appDescriptorProvider);
	}
}
