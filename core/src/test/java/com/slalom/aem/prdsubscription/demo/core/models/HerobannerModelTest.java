package com.slalom.aem.prdsubscription.demo.core.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.net.URI;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.adobe.cq.dam.cfm.ContentElement;
import com.adobe.cq.dam.cfm.ContentFragment;
import com.slalom.aem.prdsubscription.demo.core.testcontext.AppAemContext;

import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;

/**
 * Simple JUnit test verifying the ProductDetailsModel
 */
@ExtendWith({ AemContextExtension.class, MockitoExtension.class })
class HerobannerModelTest {

	private final AemContext context = AppAemContext.newAemContext();

	private Resource resource;

	@Mock
	private ResourceResolver resourceResolver;

	@Mock
	private ContentElement contentElement;

	private HerobannerModel herobannerModel;

	@BeforeEach
	public void setup() throws Exception {

		context.addModelsForClasses(HerobannerModel.class);

		context.load().json("/hero_banner_component.json",
				"/content/prdsubscription/en/home/jcr:content/root/container");
		context.load().json("/contentfragment_productsubscription.json",
				"/content/dam/prdsubscription/product-catalog-cf");

		herobannerModel = context
				.currentResource("/content/prdsubscription/en/home/jcr:content/root/container/herobanner")
				.adaptTo(HerobannerModel.class);
	}

	@Test
	void testGetProdDetailsJson() throws Exception {

		URL url = getClass().getResource("/product_data.json");
		URI uri = new URI(url.toString());
		Path path = Paths.get(uri.getPath());
		String Expected_JSON = Files.readString(path, StandardCharsets.UTF_8);

		// Assertions
		assertEquals(Expected_JSON, herobannerModel.getProdDetailsJson());
	}

	@Test
	void testProdDataAsMapIsEmpty() throws Exception {
		// Assertions
		Map<String, String> dataMap = herobannerModel.getProductDataAsMap();
		assertFalse(dataMap.isEmpty());
	}

	@Test
	public void testContentFragmentNotNull() {

		resource = context.resourceResolver()
				.getResource("/content/dam/prdsubscription/product-catalog-cf/product-subscription");

		ContentFragment contentFragment = resource.adaptTo(ContentFragment.class);
		assertNotNull(contentFragment);
	}

}