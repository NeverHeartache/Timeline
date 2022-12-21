package org.expensive.service.impl;

import org.expensive.common.utils.HtmlUtil;
import org.expensive.service.ImageFinderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Qualifier("staticPageImageFinder")
public class StaticPageImageFinderServiceImpl implements ImageFinderService {
    private static final Logger log = LoggerFactory.getLogger(StaticPageImageFinderServiceImpl.class);

    private final String outFilePathPrefix = "F:\\PgDownload\\";

    private String outFilePath = "";
    /**
     * 从网站下载带有目标图片的网页，并写入本地；
     * @param pagePath 目标图片所在的页面路径（）
     * @return 本地网页地址
     * @throws IOException IO异常
     */
    @Override
    public String getImagesPageFromWebsite(String pagePath) throws IOException {
        outFilePath = outFilePathPrefix + UUID.randomUUID().toString().replace("-", "").toUpperCase(Locale.ROOT) + "\\";
        File path = new File(outFilePath);
        if (!path.exists()) path.mkdir();
        InputStream inputStream = null;
        FileOutputStream fileOutputStream = null;
        String filePath = outFilePath + UUID.randomUUID().toString().replace("-", "").toUpperCase(Locale.ROOT) + ".html";
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
    public void downloadRemoteImgFile(String url, String localePathPrefix, String fileName) throws IOException {
        if (StringUtils.isEmpty(localePathPrefix)) localePathPrefix = outFilePath;
        url = url.substring(1, url.length() -1);
        URL destiny = new URL(url);
        destiny.openConnection();
        InputStream in = destiny.openStream();
        if (StringUtils.isEmpty(fileName)) fileName = url.substring(url.lastIndexOf("/"));
        String filename = localePathPrefix + fileName;
        FileOutputStream fileOutputStream = new FileOutputStream(filename);
        Scanner sc = new Scanner(in);
        int c;
        while ((c = in.read()) != -1){
            fileOutputStream.write(c);
        }
        fileOutputStream.close();
        in.close();
    }

    @Override
    public List<String> filterSrcValueFromImgLabel(String[] imgLabel) {
        Pattern srcPattern = Pattern.compile("\"[^\"]*\"");
        List<String> srcValue = new ArrayList<>();
        Matcher matcher;
        try {
            List<String> strings = HtmlUtil.getSrcOfImg(imgLabel);
            for (String s : strings) {
                matcher = srcPattern.matcher(s);
                if (matcher.find()) {
                    String value = matcher.group();
                    if (value.startsWith("//")) {
                        value = "https:" + value;
                    }
                    srcValue.add(value);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return srcValue;
    }
}
