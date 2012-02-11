package org.ktlab.filter;

public class News{
	private String url;
	private String content;
	private String title;
	
	public News() {}				//Must exist
	
	public News(String url, String content, String title) {
		this.url = url;
		this.content = content;
		this.title = title;
	}
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String toString() {
		return this.url + "---" + this.title + "---" + this.content;
		
	}
}
