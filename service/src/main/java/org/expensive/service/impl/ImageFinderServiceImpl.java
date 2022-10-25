package org.expensive.service.impl;

import org.expensive.common.utils.HtmlUtil;
import org.expensive.service.ImageFinderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.xml.DomUtils;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.Scanner;
import java.util.UUID;

@Service
public class ImageFinderServiceImpl implements ImageFinderService {

    private String outFilePathPrefix = "F:\\TimelineResource\\";

    /**
     * 从网站下载带有目标图片的网页，并写入本地；
     * @param pagePath 目标图片所在的页面路径（）
     * @return 本地网页地址
     * @throws IOException IO异常
     */
    @Override
    public String getImagesPageFromWebsite(String pagePath) throws IOException {
//        pagePath = "https://erogazo.info/archives/9164/10";
        pagePath = "https://www.vcg.com/creative-image/gaoqingbizhi/";
        InputStream inputStream = null;
        FileOutputStream fileOutputStream = null;
        String filePath = outFilePathPrefix + UUID.randomUUID().toString().replace("-", "").toUpperCase(Locale.ROOT) + ".html";
        System.out.println(filePath);
        try {
            URL url = new URL(pagePath);
            url.openConnection();
            inputStream = url.openStream();
            fileOutputStream = new FileOutputStream(filePath);
            int ch;
            while ((ch = inputStream.read()) != -1) {
                fileOutputStream.write(ch);
            }
            return filePath;
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
        return filePath;
    }

    /**
     * 从本地html文件过滤img标签并将图片下载到本地
     * @param path html文件路径
     * @throws FileNotFoundException 异常
     */
    @Override
    public String[] filterImagesFromFile(String path) throws FileNotFoundException {
        File file = new File(path);
        FileInputStream fileInputStream = new FileInputStream(file);
        Scanner sc = new Scanner(fileInputStream);
        StringBuilder sber = new StringBuilder();
        while (sc.hasNextLine()) {
            sber.append(sc.nextLine());
        }
        String htmlContent = sber.toString();
        return HtmlUtil.getImgs(htmlContent);
    }

    /**
     * 将远程资源下载道本地指定目录
     * @param url 远程文件url
     * @param localePathPrefix 本地文件url
     * @throws IOException 异常
     */
    private void downloadRemoteImgFile(String url, String localePathPrefix) throws IOException {
        URL destiny =  new URL(url);
        InputStream in = destiny.openStream();
        FileOutputStream fileOutputStream = new FileOutputStream(localePathPrefix + "download.jpg");
        Scanner sc = new Scanner(in);
        int c;
        while ((c = in.read()) != -1){
            fileOutputStream.write(c);
        }
        fileOutputStream.close();
        in.close();
    }
}
