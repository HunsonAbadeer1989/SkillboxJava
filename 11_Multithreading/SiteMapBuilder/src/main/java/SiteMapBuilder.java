import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.RecursiveTask;

public class SiteMapBuilder extends RecursiveTask<Set<String>> {

    private String pageURL;
    Set<String> urls = new HashSet<>();

    public SiteMapBuilder(String pageURL) {
        this.pageURL = pageURL;
    }

     static Set<String> getLinks(String pageURL) {
        Set<String> linksFromPage = new HashSet<>();

        try {
            Document document = Jsoup.connect(pageURL).maxBodySize(0)
                    .ignoreContentType(true)
                    .timeout(10 * 1000).get();
            Elements allLinks = document.select("a[href]");
            allLinks.forEach(l -> {
                String tempLink = l.absUrl("href");
                if (tempLink.contains(pageURL)) {
                    linksFromPage.add(tempLink);
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        }

        return linksFromPage;
    }

    @Override
    protected Set<String> compute() { //https://habr.com/ru/post/128985/

        return null;
    }
}