package org.expensive.time;

import jdk.nashorn.internal.runtime.regexp.RegExp;
import org.expensive.common.utils.HtmlUtil;
import org.expensive.service.impl.ImageFinderServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.CollectionUtils;
import org.w3c.dom.Element;
import org.w3c.dom.html.HTMLElement;
import sun.plugin.dom.html.HTMLBRElement;

import javax.annotation.Resource;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class PageFileTest {

    private static final Logger logger = LoggerFactory.getLogger(PageFileTest.class);

    @Resource
    private ImageFinderServiceImpl service;

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
        String path = "F:\\TimelineResource\\A4B1052D16E14D5EAF8F78952F234E70.html";
        String[] arr = service.filterImagesFromFile(path);
        Pattern srcPattern = Pattern.compile("/<img[^>]+src=['\"]([^'\"]+)['\"]+/g");
        Matcher matcher = null;
        try {
            List<String> strings = HtmlUtil.getSrcOfImg(arr);
            strings.stream().forEach(e -> {
                System.out.println(e);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void downloadRemoteImage() throws IOException {
        String url = "https://tenfei04.cfp.cn/creative/vcg/400/version23/VCG217e72c7346.jpg";
        URL destiny =  new URL(url);
        InputStream in = destiny.openStream();
        FileOutputStream fileOutputStream = new FileOutputStream("download.jpg");
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
        } catch (MalformedURLException e) {
            e.printStackTrace();
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
