package org.expensive.service.impl;

import org.expensive.service.ImageFinderService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.Scanner;
import java.util.UUID;
import java.util.stream.Stream;

@Service
public class ImageFinderServiceImpl implements ImageFinderService {

    private String outFilePathPrefix = "F:\\TimelineResource\\";

    /**
     * 从网站下载带有目标图片的网页，并写入本地；
     * @param pagePath 目标图片所在的页面路径（）
     * @return 本地网页地址
     * @throws IOException
     */
    @Override
    public String getImagesPageFromWebsite(String pagePath) throws IOException {
        pagePath = "https://erogazo.info/archives/9164/10";
        InputStream inputStream = null;
        FileOutputStream fileOutputStream = null;
        String filePath = outFilePathPrefix + UUID.randomUUID().toString().replace("-", "").toUpperCase(Locale.ROOT) + ".html";
        try {
            URL url = new URL(pagePath);
            url.openConnection();
            inputStream = url.openStream();
            Scanner sc = new Scanner(inputStream);
            fileOutputStream = new FileOutputStream(filePath);
            StringBuilder sber = new StringBuilder();
            while (sc.hasNextLine()) {
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
            return filePath;
        }
    }
}
