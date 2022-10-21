package org.expensive.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.stream.Stream;

public interface ImageFinderService {

    /**
     * 通过网页路径来加载图片
     * @param pagePath 目标图片所在的页面路径（）
     * @return 返回页面的字符数据流
     */
    String getImagesPageFromWebsite(String pagePath) throws IOException;

    /**
     * 从本地html文件过滤img标签并将图片下载到本地
     * @param filePath html文件
     */
    void filterImagesFromFile(String filePath) throws FileNotFoundException;
}
