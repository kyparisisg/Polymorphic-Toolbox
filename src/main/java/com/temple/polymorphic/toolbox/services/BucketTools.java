package com.temple.polymorphic.toolbox.services;
import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.*;
import com.temple.polymorphic.toolbox.dto.FileInfoDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class BucketTools {
    private static final Logger LOG = LoggerFactory.getLogger(BucketTools.class);
    //checking os of user
    private static final boolean IS_WINDOWS = System.getProperty( "os.name" ).contains( "indow" );


    // tyler Remember you might have to change println out to logger
    //this deletes a bucket and all of the objects within the bucket by iterating through its object list
    public static void deleteBucket(String bucketName,AmazonS3 s3client) throws IOException {
        try {
            System.out.println("Deleting S3 bucket: " + bucketName);
            ObjectListing objectListing = s3client.listObjects(bucketName);

            while (true) {
                for ( Iterator<?> iterator = objectListing.getObjectSummaries().iterator(); iterator.hasNext(); ) {
                    S3ObjectSummary objectSummary = (S3ObjectSummary) iterator.next();
                    s3client.deleteObject(bucketName, objectSummary.getKey());
                }
                if (objectListing.isTruncated()) {
                    objectListing = s3client.listNextBatchOfObjects(objectListing);
                } else {
                    break;
                }
            };
            VersionListing list = s3client.listVersions(new ListVersionsRequest().withBucketName(bucketName));
            for ( Iterator<?> iterator = list.getVersionSummaries().iterator(); iterator.hasNext(); ) {
                S3VersionSummary s = (S3VersionSummary)iterator.next();
                s3client.deleteVersion(bucketName, s.getKey(), s.getVersionId());
            }
            s3client.deleteBucket(bucketName);


        } catch (AmazonServiceException ase) {
            System.out.println("Caught an AmazonServiceException, which " +
                    "means your request made it " +
                    "to Amazon S3, but was rejected with an error response" +
                    " for some reason.");
            System.out.println("Error Message:    " + ase.getMessage());
            System.out.println("HTTP Status Code: " + ase.getStatusCode());
            System.out.println("AWS Error Code:   " + ase.getErrorCode());
            System.out.println("Error Type:       " + ase.getErrorType());
            System.out.println("Request ID:       " + ase.getRequestId());
        } catch (AmazonClientException ace) {
            System.out.println("Caught an AmazonClientException, which " +
                    "means the client encountered " +
                    "an internal error while trying to " +
                    "communicate with S3, " +
                    "such as not being able to access the network.");
            System.out.println("Error Message: " + ace.getMessage());
        }
    }

    public static List<FileInfoDto> getBucketItemList(String bucketName, AmazonS3 s3client){
        ObjectListing objectListing = s3client.listObjects(bucketName);
        List<FileInfoDto> s3filesList = new ArrayList<FileInfoDto>();

        for ( Iterator<?> iterator = objectListing.getObjectSummaries().iterator(); iterator.hasNext(); ) {
            FileInfoDto tempfileobj = new FileInfoDto();
            S3ObjectSummary objectSummary = (S3ObjectSummary) iterator.next();
            String temp = objectSummary.getKey();
            if(temp.charAt(temp.length() - 1) != '/'){

                String [] parts = temp.split("/");

                String actualFileName = parts[parts.length - 1];

                tempfileobj.setFile_name(actualFileName);

                tempfileobj.setBucket(objectSummary.getBucketName());

                int dirEnd = temp.lastIndexOf("/");

                temp.replace(actualFileName+"","");

                tempfileobj.setS3dir(temp);

                s3filesList.add(tempfileobj);


            }else{ continue;}

        }
        return s3filesList;
    }


    public static void transferobj(String filename,String bucketNamefrom,String dirFrom, String bucketNameTo, String dirTo,AmazonS3 s3client){
        if(s3client.doesBucketExistV2(bucketNamefrom) == false || s3client.doesBucketExistV2(bucketNameTo) == false){
            //user logger class to log error for bukkets not existing then throw an exception
            System.out.println("Buckets listed do not exist");



        }else if(s3client.doesObjectExist(bucketNamefrom,dirFrom+"/"+filename) == false){


        }else{

        }
    }

}
