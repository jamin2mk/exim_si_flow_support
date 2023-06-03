package com.si.helper;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.CDATASection;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.google.gson.Gson;
import com.si.consts.Error;
import com.si.exception.SIException;

public class XmlHelper {

	public static String convertDateString(String dateString, String originFormat, String targetFormat) throws ParseException {

		String result = null;
		DateTimeFormatter targetFormatter = DateTimeFormatter.ofPattern(targetFormat);

		LocalDateTime ldt = LocalDateTime.parse(dateString, DateTimeFormatter.ofPattern(originFormat));
		ZonedDateTime zdt = ldt.atZone(ZoneId.systemDefault());

		result = targetFormatter.format(zdt);
		return result;
	}

	public static String convertObjectToXml(Class<?> cls, Object soaobj) {
		String message = null;
		try {
			JAXBContext ctx = JAXBContext.newInstance(cls);
			Marshaller m = ctx.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, false);
			m.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);

			StringWriter sw = new StringWriter();
			m.marshal(soaobj, sw);
			message = sw.toString();

		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return message;
	}

	@SuppressWarnings("unchecked")
	public static <T> T convertXmlToObject(Class<T> clazz, String xml) throws Exception {

		T result = null;

		JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

		XMLStreamReader reader = XMLInputFactory.newInstance().createXMLStreamReader(new StringReader(xml));

		result = (T) unmarshaller.unmarshal(reader);

		return result;
	}

	public static Document convertXmlToDocument(String in) throws SIException {
		try {
//			System.out.println("[INFO] DocumentBuilderFactory LIB PATH: " + DocumentBuilderFactory.class.getProtectionDomain().getCodeSource().getLocation().getPath());
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			InputSource is = new InputSource(new StringReader(in));
			return db.parse(is);
		} catch (ParserConfigurationException | SAXException | IOException e) {
			throw new SIException(Error.EX_99, "Error in convert xml-document", e);
		} catch (Exception e) {
			e.printStackTrace();
			throw new SIException(Error.EX_99, "Error in convert xml-document", e);
		} 
	}

	public static String convertDocumentToXmlString(Document document) throws TransformerFactoryConfigurationError, TransformerException {

		String result = null;

		DOMSource domSource = new DOMSource(document);
		Transformer transformer = TransformerFactory.newInstance().newTransformer();
		StringWriter sw = new StringWriter();
		StreamResult sr = new StreamResult(sw);
		transformer.transform(domSource, sr);

		result = sw.toString();
		return result;
	}

	public static String convertNodeToString(Node node) throws Exception {
		StringWriter sw = new StringWriter();

		Transformer t = TransformerFactory.newInstance().newTransformer();
		t.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
		t.setOutputProperty(OutputKeys.INDENT, "yes");
		t.transform(new DOMSource(node), new StreamResult(sw));

		return sw.toString();
	}

	public static String fetchSubElementAsString(String xml, String elementName) throws SIException {

		Document document = XmlHelper.convertXmlToDocument(xml);
		Node node = document.getElementsByTagName(elementName).item(0);
		try {
			return XmlHelper.convertNodeToString(node);
		} catch (Exception e) {
			throw new SIException(Error.EX_99, String.format("Error in fetching element: [%s]", elementName), e);
		}
	}

	public static String fetchTextFromElement(String elementName, String xml) throws SIException {

		Document parseXmlToDocument = XmlHelper.convertXmlToDocument(xml);
		String textContent = parseXmlToDocument.getElementsByTagName(elementName).item(0).getTextContent();

		return textContent;
	}

	public static String setCDataToElement(String elementName, String value, String xml) throws TransformerFactoryConfigurationError, TransformerException, SIException {

		Document document = XmlHelper.convertXmlToDocument(xml);
		Node element = document.getElementsByTagName(elementName).item(0);
		CDATASection cData = document.createCDATASection(value);
		element.appendChild(cData);

		return XmlHelper.convertDocumentToXmlString(document);
	}

	public static String setTextContentToElement(String elementName, String value, String xml) throws TransformerFactoryConfigurationError, TransformerException, SIException {

		Document document = XmlHelper.convertXmlToDocument(xml);
		Node element = document.getElementsByTagName(elementName).item(0);
		element.setTextContent(value);

		return XmlHelper.convertDocumentToXmlString(document);
	}

	public static String fetchTextFromElement(String elementName, Document document) {
		NodeList elementsByTagName = document.getElementsByTagName(elementName);
		return elementsByTagName.item(0).getTextContent();
	}

	public static String fetchTextFromElement(String elementName, int index, Document document) {
		NodeList elementsByTagName = document.getElementsByTagName(elementName);
		return elementsByTagName.item(index).getTextContent();
	}

	// WITH JSON
	public static <T> String convertXmlToJson(Class<T> clazz, String xml) throws Exception {
		T object = convertXmlToObject(clazz, xml);
		return new Gson().toJson(object, clazz);
	}

	public static String decodedXml(String content) {
		return content.replaceAll("&quot;", "\"").replaceAll("&apos;", "'").replaceAll("&lt;", "<").replaceAll("&gt;", ">").replaceAll("&amp;", "&");
	}

}
