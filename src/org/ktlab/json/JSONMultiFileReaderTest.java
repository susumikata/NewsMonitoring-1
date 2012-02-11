package org.ktlab.json;

import org.ktlab.filter.News;

public class JSONMultiFileReaderTest {
	
	public static void main(String[] args) throws Exception {
		JSONMultiFileReader reader = new JSONMultiFileReader("dataTest");
		
		News news = null;
		
		while((news = reader.next(News.class)) != null) {
			System.out.println(news.getTitle());
		}
		
		
	}
}
