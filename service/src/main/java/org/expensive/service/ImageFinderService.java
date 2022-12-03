package org.expensive.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface ImageFinderService {

    /**
     * 通过网页路径来加载图片
     * @param pagePath 目标图片所在的页面路径
     * @return 本地网页地址
     */
    String getImagesPageFromWebsite(String pagePath) throws IOException;

    /**
     * 从本地html文件过滤img标签并将图片下载到本地
     * @param filePath html文件
     */
    String[] filterImagesFromFile(String filePath) throws FileNotFoundException;

    /**
     * 将远程资源下载道本地指定目录
     * @param url 远程文件url
     * @param localePathPrefix 本地文件url
     * @throws IOException 异常
     */
    void downloadRemoteImgFile(String url, String localePathPrefix, String fileName) throws IOException;

    /**
     * 从image标签中过滤src的值
     * @param imgLabel 标签字符串
     * @return src的字符串值
     */
    List<String> filterSrcValueFromImgLabel(String[] imgLabel);
}
