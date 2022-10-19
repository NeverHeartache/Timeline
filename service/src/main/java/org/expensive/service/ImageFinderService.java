package org.expensive.service;

import java.util.stream.Stream;

public interface ImageFinderService {

    /**
     * 通过网页路径来加载图片
     * @param pagePath 目标图片所在的页面路径（）
     * @return 返回页面的字符数据流
     */
    Stream<String> getImagesFromWebsite(String pagePath);
}
