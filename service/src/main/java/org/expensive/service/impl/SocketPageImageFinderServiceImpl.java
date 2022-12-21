package org.expensive.service.impl;

import org.expensive.common.pojo.GlobalPropertiesEntity;
import org.expensive.service.DynamicPageImageFinderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

@Service
@Qualifier("socketPageImageFinder")
public class SocketPageImageFinderServiceImpl implements DynamicPageImageFinderService {
    private static final Logger logger = LoggerFactory.getLogger(SocketPageImageFinderServiceImpl.class);
    @Autowired
    private GlobalPropertiesEntity properties;

    @Override
    public String getImagesPageFromWebsite(String pagePath) throws IOException {
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
