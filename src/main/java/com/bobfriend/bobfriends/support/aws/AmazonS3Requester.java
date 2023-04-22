package com.bobfriend.bobfriends.support.aws;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.bobfriend.bobfriends.support.utils.MultipartUtil;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.OutputStream;

@Component
@RequiredArgsConstructor
public class AmazonS3Requester {
    @Value("${cloud.aws.s3.bucket}")
    private String bucket;
    private final AmazonS3Client amazonS3Client;

    public void put(String fullPath, MultipartFile multipartFile) {
        File file = new File(MultipartUtil.getLocalHomeDirectory(), fullPath);
        try {
            multipartFile.transferTo(file);
            amazonS3Client.putObject(new PutObjectRequest(bucket, fullPath, file));
        } catch (Exception e) {
            throw new RuntimeException();
        } finally {
            if (file.exists()) {
                file.delete();
            }
        }
    }

    @SneakyThrows
    public void get(String filePath, HttpServletResponse httpServletResponse) {
        OutputStream outputStream = httpServletResponse.getOutputStream();
        amazonS3Client.getObject(bucket, String.format("%s", filePath))
                .getObjectContent().transferTo(outputStream);
        outputStream.flush();
        outputStream.close();
    }
}