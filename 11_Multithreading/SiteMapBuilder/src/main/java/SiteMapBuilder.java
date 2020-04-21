import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.RecursiveTask;

public class SiteMapBuilder extends RecursiveTask<Set<String>> {

    private String pageURL;
    final static Set<String> urls = new HashSet<>();

    public SiteMapBuilder(String pageURL) {
        this.pageURL = pageURL;
    }

    static Set<String> getLinks(String pageURL) {
        Set<String> linksFromPage = new TreeSet<>();

        try {
            Document document = Jsoup.connect(pageURL).maxBodySize(0)
                    .ignoreContentType(true)
                    .timeout(10 * 1000).get();
            Elements allLinks = document.select("a[href]");
            allLinks.forEach(l -> {
                String tempLink = l.absUrl("href");
                if(allLinks.isEmpty()){
                    return;
                }
                if (!tempLink.equals(pageURL) && tempLink.contains(pageURL)) {
                    linksFromPage.add(tempLink);
                    System.out.println(tempLink);
                    getLinks(tempLink);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        return linksFromPage;
    }

    @Override
    protected Set<String> compute() { //https://habr.com/ru/post/128985/
        Set<String> result = new TreeSet<>();
        List<SiteMapBuilder> subTasks = new LinkedList<>();
        for (String subLink : getLinks(pageURL)) {
            if (!urls.contains(subLink)) {
                synchronized (urls) {
                    urls.add(subLink);
                }
                result.add(subLink);

                SiteMapBuilder task = new SiteMapBuilder(subLink);
                task.fork();
                subTasks.add(task);
            }
        }
        for (SiteMapBuilder task : subTasks) {
            result.addAll(task.join());
        }
        return result;
    }
}