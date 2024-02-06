package com.slalom.aem.prdsubscription.demo.core.models;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.resource.Resource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.engine.support.descriptor.ClasspathResourceSource;
import org.mockito.junit.jupiter.MockitoExtension;

import com.adobe.cq.dam.cfm.ContentFragment;
import com.day.cq.wcm.api.Page;
import com.slalom.aem.prdsubscription.demo.core.testcontext.AppAemContext;

import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;

/**
 * Simple JUnit test verifying the ProductDetailsModel
 */
@ExtendWith({ AemContextExtension.class, MockitoExtension.class })
class HerobannerModelTestClass {

	private final AemContext context = AppAemContext.newAemContext();

	private HerobannerModel herobanner;

	private Page page;
	private Resource resource;

	private ContentFragment cf;

	@BeforeEach
	public void setup() throws Exception {
		context.addModelsForClasses(HerobannerModel.class);
		context.load().json("/com/slalom/aem/prdsubscription/demo/core/models/homepage1.json",
				"/prdsubscription/components");
		context.currentResource("/prdsubscription/components/herobanner");
		herobanner = context.request().adaptTo(HerobannerModel.class);
	}

	@Test
	void testGetProductDataFromCF() throws Exception {

	}

}