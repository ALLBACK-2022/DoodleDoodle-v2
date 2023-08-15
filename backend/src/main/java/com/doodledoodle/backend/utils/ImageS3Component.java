package com.doodledoodle.backend.utils;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.doodledoodle.backend.global.storage.S3StorageProperties;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ImageS3Component {
    private static final String MAIN_DIRECTORY = "drawimage/";
    private static final String FILE_EXTENSION_SPLITTER = ".";
    S3StorageProperties s3StorageProperties;
    AmazonS3Client amazonS3Client;

    public String uploadAndGetUrl(final MultipartFile file, final Long drawId) throws IOException {
        String fileName = getFileName(file, drawId);
        ObjectMetadata data = new ObjectMetadata();
        data.setContentType(fileName);
        data.setContentLength(file.getSize());

        amazonS3Client.putObject(
                new PutObjectRequest(s3StorageProperties.getBucket(), fileName, file.getInputStream(), data)
                        .withCannedAcl(CannedAccessControlList.PublicRead));

        return getImageUrl(fileName);
    }

    private String getImageUrl(final String fileName) {
        return amazonS3Client.getUrl(s3StorageProperties.getBucket(), fileName).toString();
    }

    private String getFileName(final MultipartFile file, final Long drawId) {
        return String.join("", MAIN_DIRECTORY, drawId.toString(),
                FILE_EXTENSION_SPLITTER, StringUtils.getFilenameExtension(file.getOriginalFilename()));
    }
}
