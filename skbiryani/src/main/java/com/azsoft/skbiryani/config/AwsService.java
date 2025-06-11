package com.azsoft.skbiryani.config;

import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import lombok.AllArgsConstructor;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.HeadObjectRequest;
import software.amazon.awssdk.services.s3.model.NoSuchKeyException;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;

@Service
public class AwsService {
	
	private final S3Client s3Client;
	
	@Value("${aws.s3.bucketname}")
	private String bucketName;
	@Value("${aws.region}")
	private String region;
	
	public AwsService(S3Client s3Client) {
        this.s3Client = s3Client;
    }
	
	
	public String uploadImage(MultipartFile file) {
		String fileNameExtension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")+1);
		String key = UUID.randomUUID().toString()+"."+fileNameExtension;
		try {
			PutObjectRequest putObjectRequest = PutObjectRequest.builder()
					.bucket(bucketName)
					.key(key)
					.acl("public-read")
					.contentType(file.getContentType())
					.build();
			PutObjectResponse response = s3Client.putObject(putObjectRequest, RequestBody.fromBytes(file.getBytes()));
			if(response.sdkHttpResponse().isSuccessful()) {
				return "https://"+bucketName+".s3."+region+".amazonaws.com/"+key;
			}else {
				throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "File upload failed");
			}
		}catch (IOException ex) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "An error occured while uploading the file to AWS-s3");
		}
	}
	
	public Boolean deleteImageFile(String fileName) {
		HeadObjectRequest headObjectRequest = HeadObjectRequest.builder()
		        .bucket(bucketName)
		        .key(fileName)
		        .build();

		try {
		    s3Client.headObject(headObjectRequest); // will throw if not found
		    s3Client.deleteObject(DeleteObjectRequest.builder()
		        .bucket(bucketName)
		        .key(fileName)
		        .build());
		    return true;
		} catch (NoSuchKeyException e) {
		    System.out.println("File not found in S3: " + fileName);
		    return false;
		}
	}

}











