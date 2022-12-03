package org.expensive.service.impl;

import org.expensive.service.ImageFinderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

@Component
public class DynamicRequestImageFinderServiceImpl implements ImageFinderService {
    private static final Logger logger = LoggerFactory.getLogger(DynamicRequestImageFinderServiceImpl.class);

    /**
     * 请求远程路由，动态获取返回内容，然后进行图片发掘
     * @param pagePath 目标图片所在的页面路径
     * @return 存有目标文件的本地的文件路径
     * @throws IOException 异常
     */
    @Override
    public String getImagesPageFromWebsite(String pagePath) throws IOException {
        logger.info("动态加载图片方法实现！");
        return null;
    }

    @Override
    public String[] filterImagesFromFile(String filePath) throws FileNotFoundException {
        return new String[0];
    }

    @Override
    public void downloadRemoteImgFile(String url, String localePathPrefix, String fileName) throws IOException {

    }

    @Override
    public List<String> filterSrcValueFromImgLabel(String[] imgLabel) {
        return null;
    }
}
