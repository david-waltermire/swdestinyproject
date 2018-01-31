package io.github.swdestinyproject.pricing.config;

import java.util.LinkedList;
import java.util.List;

public class ScraperConfig {

	private List<Source> sources = new LinkedList<>();

	public ScraperConfig() {

	}

	public List<Source> getSources() {
		return sources;
	}

	public void addSource(Source source) {
		this.sources.add(source);
	}
}
