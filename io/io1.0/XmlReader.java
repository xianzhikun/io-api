package io;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
/*
 * zkXmlReader
 * 实现xml文件读写操作
 */
public class XmlReader {
	SAXReader reader;
	File file;
	Document doc;
	Element root;
	InputStream input;
	public XmlReader()
	{
		reader=new SAXReader();
	}
	public XmlReader(String filePath)
	{
		input=getClass().getResourceAsStream(filePath);
		reader=new SAXReader();
	}
	public XmlReader read()
	{
		try {
			doc=reader.read(input);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return this;
	}
	public Element getRoot()
	{
		return doc.getRootElement();
	}
	public Iterator<Element> getIter(Element el)
	{
		return el.elementIterator();
	}
	public ArrayList<Element> getArrlist(Element el)
	{
		ArrayList<Element> elements=new ArrayList<Element>();
		Iterator<Element> iner=getIter(el);
		while(iner.hasNext())
		{
			Element e=iner.next();
			elements.add(e);
		}
		return elements;
	}
	public Element[] getArr(Element el)
	{
		ArrayList<Element> elements=getArrlist(el);
		Element[] ee=new Element[elements.size()];
		Element[] eles=(Element[]) elements.toArray(ee);
		return eles;
	}
}
