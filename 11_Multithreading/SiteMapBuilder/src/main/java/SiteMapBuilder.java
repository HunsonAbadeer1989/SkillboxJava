import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.RecursiveTask;

public class SiteMapBuilder extends RecursiveTask<ConcurrentHashMap<String, String>> {

    private String pageURL;
    private Set<String> allURLs = ConcurrentHashMap.newKeySet();

    public SiteMapBuilder(String pageURL) {
        this.pageURL = pageURL;
    }

    public SiteMapBuilder(String pageURL, Set<String> allURLs) {
        this.pageURL = pageURL;
        this.allURLs = allURLs;
    }

    static Set<String> getLinks(String pageURL) {
        System.out.println(Thread.currentThread() + " load " + pageURL);
        Set<String> linksFromPage = new TreeSet<>();

        try {
            Document document = Jsoup.connect(pageURL).maxBodySize(0)
                    .ignoreContentType(true)
                    .timeout(10 * 3000).get();
            Elements allLinks = document.select("a[href]");
            allLinks.forEach(l -> {
                String tempLink = l.absUrl("href");
                if (allLinks.isEmpty() || linksFromPage.contains(tempLink) || linksFromPage.contains(pageURL + "/")) {
                    return;
                }
                if (!tempLink.equals(pageURL) && tempLink.contains(pageURL)) {
                    linksFromPage.add(tempLink);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return linksFromPage;
    }

    @Override
    protected ConcurrentHashMap<String, String> compute() {
        ConcurrentHashMap<String, String> result = new ConcurrentHashMap<>();
        List<SiteMapBuilder> subTasks = new LinkedList<>();
        for (String subLink : getLinks(pageURL)) {
            if (!allURLs.contains(subLink) && !result.containsKey(subLink) && !subLink.equals(pageURL)) {
                result.put(subLink, "");

                SiteMapBuilder task = new SiteMapBuilder(subLink, allURLs);
                task.fork();
                subTasks.add(task);
            }
        }
        for (SiteMapBuilder task : subTasks) {
            result.putAll(task.join());
        }
        return result;
    }
}
