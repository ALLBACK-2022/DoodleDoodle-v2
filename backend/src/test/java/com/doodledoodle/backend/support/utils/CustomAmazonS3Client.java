package com.doodledoodle.backend.support.utils;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import java.net.MalformedURLException;
import java.net.URL;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Primary
@Component
public class CustomAmazonS3Client extends AmazonS3Client {

    @Override
    public PutObjectResult putObject(PutObjectRequest putObjectRequest) {
        return null;
    }

    @Override
    public URL getUrl(String bucketName, String key) {
        URL parentUrl = null;
        try {
            parentUrl = new URL("http","1","2");
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        return parentUrl;
    }
}
