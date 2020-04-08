package com.temple.polymorphic.toolbox.services;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.Collections;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;

@Service
public class TransferService {

    public static void CnPSimpleBucket(){
        BasicAWSCredentials awsCreds = new BasicAWSCredentials(Credentials.access_key_id, Credentials.secret_access_key);

        AmazonS3Client s3Client = new AmazonS3Client(awsCreds);

        // BucketUtils.deleteAllBuckets(s3Client);


        String newBucketName = "matt" + System.currentTimeMillis();


        s3Client.createBucket(newBucketName);

        final String fileName = "sometext.txt";

//        File file = new File(S3JavaSDKExample.class.getResource(fileName).toURI());

        //        this is anew bucket and folders dont exist in S3, S3 is just a key value store.
//        {
//
//        }

    }

    //implement the services here!


}
