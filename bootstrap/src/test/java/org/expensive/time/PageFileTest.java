package org.expensive.time;

import org.expensive.service.impl.ImageFinderServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

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
//            File file = new File(inputStream);
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
