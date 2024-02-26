package com.slalom.aem.prdsubscription.demo.core.models;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.cq.dam.cfm.ContentElement;
import com.adobe.cq.dam.cfm.ContentFragment;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Model(adaptables = { Resource.class,
		SlingHttpServletRequest.class }, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class HerobannerModel {

	private static final Logger LOGGER = LoggerFactory.getLogger(HerobannerModel.class);

	@ValueMapValue
	private String fileReference;

	@SlingObject
	private ResourceResolver resourceResolver;

	@PostConstruct
	public void init() {

		LOGGER.debug("Inside ProductDetailsModel : **");

	}

	public Map<String, String> getProductDataAsMap() {

		String jsonString = getProdDetailsJson();

		// Initialize ObjectMapper from Jackson
		ObjectMapper objectMapper = new ObjectMapper();

		// Parse JSON string into a List<Map<String, Object>>
		List<Map<String, Object>> dataList;

		// Create a map of category types to JSON nodes
		Map<String, String> categoryJsonMap = new LinkedHashMap<>();
		try {
			dataList = objectMapper.readValue(jsonString, List.class);

			// Convert each map to a JSON node
			List<JsonNode> jsonNodes = new ArrayList<JsonNode>();
			for (Map<String, Object> map : dataList) {
				JsonNode jsonNode = objectMapper.valueToTree(map);
				jsonNodes.add(jsonNode);
			}

			for (JsonNode jsonNode : jsonNodes) {
				categoryJsonMap.put(jsonNode.get("categoryType").asText(), objectMapper.writeValueAsString(jsonNode));
			}
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return categoryJsonMap;

	}

	
	//This method can be moved to a service class
	 String getProdDetailsJson() {

		String str = "";
		ContentFragment cf = resourceResolver.getResource(fileReference).adaptTo(ContentFragment.class);
		if (cf.hasElement("product_data")) {
			ContentElement fragmentElement = cf.getElement("product_data");
			str = fragmentElement.getContent();
		}
		return str;

	}
	
}
