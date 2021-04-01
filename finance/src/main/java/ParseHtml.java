import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.List;

public class ParseHtml {

    public static void singlePageParseImg(Document document, List<String> urls) {
        ////*[@id="cHBPH"]/table/tbody/tr[1]
        //#cHBPH > table > tbody > tr:nth-child(6)
        ////*[@id="cHBPH"]/table/tbody/tr[7]
        //Elements divElements = document.select("*[@id=\"cHBPH\"]/table/tbody");

        Elements fundTab_table_fblue = document.getElementsByClass("fundTab table fblue");


        Element cHBPH = document.getElementById("cHBPH");

        Elements table = document.getElementsByTag("table");


        Elements divElements = document.getElementsByTag("tr");

        //
        Elements elementsByClass = document.select("[class=red]");

        //<td class="colorize "><a href="http://biz.finance.sina.com.cn/suggest/lookup_n.php?q=150230&amp;country=fund" target="_blank">150230</a></td>
        Elements allElements = document.getAllElements();

        List<String> list = allElements.eachText();
        for (String s : list) {
            System.out.println(s);
        }

        Elements pElements = divElements.select("p");
        Elements imgElements = pElements.select("img");
        for (Element imgElement : imgElements) {
            urls.add(imgElement.attr("data-src"));
            //打印目标数据，查看是否正确
        }
        Elements spanElements = pElements.select("span");

        Elements imgElemnets = spanElements.select("img");
        for (Element imgElement : imgElemnets) {
            urls.add(imgElement.attr("data-src"));
            //打印目标数据，查看是否正确
            System.out.println(imgElement.attr("data-src"));
        }
    }
}
