package com.busoft.project1.Helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectAclRequest;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.GetObjectPresignRequest;

import java.time.Duration;

@Component
public class AwsHelper {
    @Autowired
    private S3Client s3Client;
    @Autowired
    private S3Presigner s3Presigner;
    @Autowired
    private Environment env;
    private Logger logger = LoggerFactory.getLogger(AwsHelper.class);

    public String generatePresignedUrl(String key) {
        try {
            GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                    .bucket(env.getProperty("aws.s3.bucketName"))
                    .key(key)
                    .build();

            GetObjectPresignRequest presignRequest = GetObjectPresignRequest.builder()
                    .signatureDuration(Duration.ofMinutes(15)) // URL valid for 15 mins
                    .getObjectRequest(getObjectRequest)
                    .build();

            return s3Presigner.presignGetObject(presignRequest).url().toString();
        } catch (Exception e) {
            logger.info("Exception is upload Image in s3 {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }
    public void uploadImage(String key, MultipartFile file) {
        try {
            PutObjectRequest putRequest = PutObjectRequest.builder()
                    .bucket(env.getProperty("aws.s3.bucketName"))
                    .key(key)
                    .contentType(file.getContentType())
                    .build();

            s3Client.putObject(putRequest, RequestBody.fromBytes(file.getBytes()));
        } catch (Exception e) {
            logger.info("Exception is upload Image in s3 {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
