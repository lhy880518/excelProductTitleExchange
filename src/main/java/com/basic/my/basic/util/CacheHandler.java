package com.basic.my.basic.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("CacheHandler")
public class CacheHandler {

    static Logger log = LoggerFactory.getLogger(CacheHandler.class);

    @Cacheable(value = "categoryCache")
    public Map<String, String> getCategoryCache(){
        try {
            return loadFile("csv\\category.csv");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Map<String, String> loadFile(String locationFilePath) throws IOException {
        String line = null;
        Map<String, String> params = new HashMap<String, String>();

        try {

            BufferedReader in = new BufferedReader(new InputStreamReader(new ClassPathResource(locationFilePath).getInputStream()));

            while( (line = in.readLine()) != null) {
                String[] arr = line.split(",");
                params.put(arr[0],line.substring(arr[0].length()));
            }
        }catch (Exception e) {
            e.printStackTrace();
        }

        log.debug("params={}",params);

        return params;
    }
}
