package com.example.users.main;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;

import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import com.example.users.model.Catalog;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class MainClass {
	public static void main(String[] args) throws JsonGenerationException, JsonMappingException, IOException {

		Catalog catalog = JAXB.unmarshal(
				new File("D:\\STSWorkspace\\SpringBootJaxbWithBinding\\src\\main\\resources\\MyBooks.xml"),
				Catalog.class);

		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder = gsonBuilder.setPrettyPrinting().enableComplexMapKeySerialization();
		Gson gson = gsonBuilder.create();

		String data = gson.toJson(catalog);

		System.out.println(data);

		ObjectMapper mapper = new ObjectMapper();

		System.out.println("================================================");

		Catalog catalogObj = mapper.readValue(data, Catalog.class);

		jaxbObjectToXML(catalogObj);

	}

	private static void jaxbObjectToXML(Catalog catalog) {
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Catalog.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			StringWriter sw = new StringWriter();
			jaxbMarshaller.marshal(catalog, sw);
			String xmlContent = sw.toString();
			System.out.println(xmlContent);

		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

}
