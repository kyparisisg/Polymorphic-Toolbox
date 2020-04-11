package com.temple.polymorphic.toolbox.services;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.apache.commons.logging.LogFactory;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.nio.file.Files;
import java.util.Iterator;
import java.nio.file.Paths;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.*;
import sun.rmi.runtime.Log;


@Service
public class TransferService {


    public static void createS3b(String bcknm){
        BasicAWSCredentials awsCreds = new BasicAWSCredentials(Credentials.access_key_id, Credentials.secret_access_key);

        AmazonS3 s3Client = AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(awsCreds)).build();

        // BucketUtils.deleteAllBuckets(s3Client);


        String newBucketName = ""+bcknm;

        if(s3Client.doesBucketExistV2(newBucketName)){


        }else{
            s3Client.createBucket(newBucketName);
        }

//        final String fileName = "sometext.txt";

//        File file = new File(S3JavaSDKExample.class.getResource(fileName).toURI());
    }

    //Use This to Delete 'Serevers' aka aws buckets from lists for admins

    public static void deleteRequest(String bcknm) throws IOException {
        BasicAWSCredentials awsCreds = new BasicAWSCredentials(Credentials.access_key_id, Credentials.secret_access_key);
        AmazonS3 s3Client = AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(awsCreds)).build();

        BucketTools.deleteBucket(bcknm,s3Client);
    }



}
