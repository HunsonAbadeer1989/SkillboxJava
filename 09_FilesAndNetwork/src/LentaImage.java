import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class LentaImage {

    private static String IMAGE_DESTINATION_FOLDER = "/Users/hunsonabadeer/Desktop/LentaImages";

    public static void main(String[] args) throws IOException {

        String strURL = "https://lenta.ru";

        Document document = Jsoup
                .connect(strURL)
                .userAgent("Safary/13.0.5")
                .timeout(10 * 1000)
                .get();

        Elements imageElements = document.select("img");

        for (Element imageElement : imageElements) {
            String strImageURL = imageElement.attr("abs:src");
            downloadImage(strImageURL);
        }

    }

    private static void downloadImage(String strImageURL) {

        String strImageName =
                strImageURL.substring(strImageURL.lastIndexOf("/") + 1);

        System.out.println("Saving: " + strImageName + ", from: " + strImageURL);

        try {
            URL urlImage = new URL(strImageURL);
            InputStream in = urlImage.openStream();

            byte[] buffer = new byte[4096];
            int n = -1;

            OutputStream os =
                    new FileOutputStream(IMAGE_DESTINATION_FOLDER + "/" + strImageName);

            while ((n = in.read(buffer)) != -1) {
                os.write(buffer, 0, n);
            }

            os.close();

            System.out.println("Image saved");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

