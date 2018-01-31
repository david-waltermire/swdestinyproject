package io.github.swdestinyproject.pricing.config;

import java.net.URI;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;

import io.github.swdestinyproject.pricing.PageHandler;

public class Source {
	private String id;
	@JsonProperty("firstPage")
	private List<URI> firstPages;
	private Class<? extends PageHandler> pageHandlerClass;

	public Source() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<URI> getFirstPages() {
		return firstPages;
	}

	public void setFirstPage(List<URI> firstPages) {
		this.firstPages = firstPages;
	}

	public Class<? extends PageHandler> getPageHandlerClass() {
		return pageHandlerClass;
	}

	public void setPageHandlerClass(Class<? extends PageHandler> handler) {
		this.pageHandlerClass = handler;
	}

	@SuppressWarnings("unchecked")
	@JsonSetter
	public void setPageHandlerClass(String handlerClassName) throws ClassNotFoundException {
		this.pageHandlerClass = (Class<? extends PageHandler>) Class.forName(handlerClassName);
	}

}
