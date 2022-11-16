package org.expensive.time;

import org.expensive.common.utils.HtmlUtil;
import org.expensive.service.impl.StaticPageImageFinderServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class PageFileTest {

    private static final Logger logger = LoggerFactory.getLogger(PageFileTest.class);

    @Resource
    private StaticPageImageFinderServiceImpl service;

    /**
     * 测试成功，只需要引入上述两个注解即可；
     * @RunWith(SpringJUnit4ClassRunner.class)
     * @ContextConfiguration(locations = {"classpath:applicationContext.xml"})
     */
    @Test
    public void testService() {
        try {
            logger.info("" + service.getImagesPageFromWebsite(""));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getFilterImages() throws FileNotFoundException {
        String path = "F:\\TimelineResource\\6FA5F610CD4545D596B3B2AB5167E489.html";
        String[] arr = service.filterImagesFromFile(path);
        Pattern srcPattern = Pattern.compile("\"[^\"]*\"");
        Matcher matcher;
        try {
            List<String> strings = HtmlUtil.getSrcOfImg(arr);
            List<String> srcValue = new ArrayList<>();
            for (String s : strings) {
                matcher = srcPattern.matcher(s);
                if (matcher.find())
                    srcValue.add(matcher.group());
            }
            srcValue.forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void downloadRemoteImage() throws IOException {
        String pathPreffix = "F:\\PgDownload\\";
        String url = "https://erogazo.info/wp-content/uploads/2022/08/210918f_0009-580x870-1.jpg";
        URL destiny =  new URL(url);
        InputStream in = destiny.openStream();
        FileOutputStream fileOutputStream = new FileOutputStream(pathPreffix + "download.jpg");
        Scanner sc = new Scanner(in);
        int c;
        while ((c = in.read()) != -1){
            fileOutputStream.write(c);
        }
        fileOutputStream.close();
        in.close();
    }

    @Test
    @Deprecated
    public void printPageFileStringStream() throws IOException {
        String pagePath = "https://erogazo.info/archives/9164/10";
        String outFilePathPrefix = "F:\\TimelineResource\\";
        InputStream inputStream = null;
        FileOutputStream fileOutputStream = null;
        try {
            URL url = new URL(pagePath);
            url.openConnection();
            inputStream = url.openStream();
            Scanner sc = new Scanner(inputStream);
            fileOutputStream = new FileOutputStream(outFilePathPrefix + "Out.html");
            StringBuilder sber = new StringBuilder();
            while(sc.hasNextLine()) {
                sber.append(sc.nextLine());
            }
            byte[] pageBytes = sber.toString().getBytes(StandardCharsets.UTF_8);
            fileOutputStream.write(pageBytes);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null)
                inputStream.close();
            if (fileOutputStream != null)
                fileOutputStream.close();
        }
    }
}
