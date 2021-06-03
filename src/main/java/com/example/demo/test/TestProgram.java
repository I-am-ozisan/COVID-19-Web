package com.example.demo.test;

import java.io.StringReader;

import javax.xml.bind.JAXB;

public class TestProgram {

	public static void main(String[] args) {
		String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>"
				+ "<rss version=\"2.0\" xmlns:media=\"http://search.yahoo.com/mrss/\">" + "<hoge>" + "  <id>20</id>"
				+ "  <value>hogehoge</value>" + "</hoge>" + "</rss>";

		Parent rss = JAXB.unmarshal(new StringReader(xml), Parent.class);

		System.out.println("id=" + rss.getHoge().getId() + ", value=" + rss.getHoge().getValue());

	}

}
