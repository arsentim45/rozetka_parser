import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        String url = "https://rozetka.com.ua/ua/gps-navigators/c80047/";
    }
    public static void parse_category(String url) throws IOException {
        Document doc = Jsoup.connect(url).get();
        String pg = "";
        Elements nums = doc.select("paginator-catalog-l-link");
        int num = Integer.parseInt(nums.last().text());
        for (int i = 0; i < num; i++) {
            pg = url + String.format("page=%d", i++);
            parse_category_page(pg);
        }
    }
    public static void parse_category_page(String url) throws IOException{
            Document doc = Jsoup.connect(url).get();
            Elements tiles = doc.select("g-i-tile-i-title");
            for (Element tile:tiles) {
                Element link = tile.select("a").first();
                parse_reviews(link.attr("href") + "comments/");
            }

    }
    private static void parse_reviews(String url) throws IOException{
        Document doc = Jsoup.connect(url).get();
        Elements nums = doc.select("paginator-catalog-l-link");
        int num;
        if (nums.size() > 0){
            num = Integer.parseInt(nums.last().text());
        }
        else {
            num = 0;
        }
        ArrayList sentiments = new ArrayList();
        for (int i = 0; i < num; i++){
            String pg = url + String.format("page=%d", i++);
            sentiments.add(parse_reviews_page(pg));
        }

    }

    private static ArrayList parse_reviews_page(String url) throws IOException{
        Document doc = Jsoup.connect(url).get();
        Elements reviews = doc.select("pp-review-i");
        ArrayList sentiments_second = new ArrayList();
        ArrayList sentiments_first = new ArrayList();
        for (Element review:reviews){
            Elements rating = review.select("g-rating-stars-i");
            Elements review_text = review.select("pp-review-text");
            if (int(rating) > 0):
                sentiments_second.add(rating);
                sentiments_first.add(review_text);
                System.out.println(sentiments_first);
                System.out.println(sentiments_second);
        }


    }
}
