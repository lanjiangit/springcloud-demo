package com.demo.cloud.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 合并重名的properties文件,把所有的key-value添加到容器中
 */
public class CompositePropertySourceFactory implements PropertySourceFactory {
    private final static Logger log = LoggerFactory.getLogger(CompositePropertySourceFactory.class);

    //jar中存在重名的properties配置
    private final String DUPLICATION_PROPERTIES = "api-application.properties";

    @Override
    public PropertySource<?> createPropertySource(String name, EncodedResource resource) throws IOException {
        String fileName = resource.getResource().getFilename();
        log.error(fileName);
        if(DUPLICATION_PROPERTIES.equalsIgnoreCase(fileName)) {
            //将重名配置的内容合并
            Map<String, Object> map = new HashMap<>();
            List<Resource> list = this.getPackageInsideResourcesByPattern(fileName);
            list.forEach(res -> {
                try {
                    PropertiesPropertySource source = new ResourcePropertySource(res);
                    map.putAll(source.getSource());
                } catch (IOException e) {
                    log.error(e.getMessage(),e);
                }
            });
            return new MapPropertySource(fileName, map);
        }
        return name != null ? new ResourcePropertySource(name, resource) : new ResourcePropertySource(resource);
    }

    private List<Resource> getPackageInsideResourcesByPattern(String resourceName) throws IOException {
        String resourcePathPattern = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX + resourceName;
        return Arrays.asList(new PathMatchingResourcePatternResolver().getResources(resourcePathPattern));
    }
}
