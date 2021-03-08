import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.javascript.AbstractJavaScriptEngine;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.ArrayList;
import java.util.List;

public class FundSpider {
    //http://vip.stock.finance.sina.com.cn/fund_center/data/jsonp.php/IO.XSRV2.CallbackList['dxJ_DCLDxQnQYM8a']/NetValueReturn_Service.NetValueReturnOpen?page=1&num=10440&sort=form_year&asc=0&ccode=&type2=0&type3=


    //private static final String START_URL = "http://vip.stock.finance.sina.com.cn/fund_center/index.html#hbphall";
    //private static final String START_URL = "http://vip.stock.finance.sina.com.cn/fund_center/data/jsonp.php/IO.XSRV2.CallbackList['dxJ_DCLDxQnQYM8a']/NetValueReturn_Service.NetValueReturnOpen?page=1&num=10440&sort=form_year&asc=0&ccode=&type2=0&type3=";
    private static final String START_URL = "http://vip.stock.finance.sina.com.cn/fund_center/data/jsonp.php/IO.XSRV2.CallbackList['dxJ_DCLDxQnQYM8a']/NetValueReturn_Service.NetValueReturnOpen?page=1&num=10&sort=form_year&asc=0&ccode=&type2=0&type3=";

    public static void main(String[] args) throws Exception {
        //Htmlunit模拟的浏览器，设置css,js等支持及其它的一些简单设置
        WebClient browser = new WebClient();
        browser.getOptions().setCssEnabled(false);
        browser.getOptions().setJavaScriptEnabled(true);
        browser.getOptions().setThrowExceptionOnScriptError(false);


        //获取页面
        HtmlPage htmlPage = browser.getPage(START_URL);
        AbstractJavaScriptEngine<?> javaScriptEngine = browser.getJavaScriptEngine();

        //设置等待js的加载时间
        browser.waitForBackgroundJavaScript(3000);

        //使用xml的方式解析获取到jsoup的document对象
        Document doc = Jsoup.parse(htmlPage.asXml());
        System.out.println(doc);




    }


    //public static void main(String[] args) throws Exception {
    //
    //    Document rootdocument = Jsoup.connect(START_URL).get();
    //
    //    List<String> urlList = new ArrayList<String>();
    //    //将页面中的目标Url解析为列表
    //    ParseHtml.singlePageParseImg(rootdocument, urlList);
    //    //初始化HttpClient对象
    //    CloseableHttpClient httpClient = HttpClients.createDefault();
    //
    //
    //}
}
