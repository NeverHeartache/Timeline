package org.expensive.time;

import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class PageFileTest {
    @Test
    public void printPageFileStringStream() {
        String pagePath = "https://erogazo.info/archives/9164/10";
        try {
            URL url = new URL(pagePath);
            url.openConnection();
            InputStream inputStream = url.openStream();
//            File file = new File(inputStream);
//            FileOutputStream fileOutputStream = new FileOutputStream();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
