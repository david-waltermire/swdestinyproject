package destiny.chancecube_import.scraper;

import java.io.IOException;
import java.net.URI;
import java.util.List;

import org.jsoup.Connection;
import org.jsoup.nodes.Document;

public interface PageHandler {
	void setupConnection(Connection connection);
	URI getNextPage(URI pageUri, Document page) throws IOException;
	List<ParsedCard> parse(URI pageUri, Document page);
}
