package org.superbiz.moviefun.blobstore;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.Optional;

public class S3Store implements BlobStore{

    private AmazonS3Client s3Client;
    private String bucketName;

    public S3Store(AmazonS3Client s3Client,String bucketName){
        this.s3Client = s3Client;
        this.bucketName = bucketName;
    }

    @Override
    public void put(Blob blob) throws IOException {
        ObjectMetadata putMetadata = new ObjectMetadata();
        putMetadata.setContentType(blob.contentType);
        s3Client.putObject(bucketName, blob.name, blob.inputStream, putMetadata);
    }

    @Override
    public Optional<Blob> get(String name) throws IOException {
        S3Object s3Object = s3Client.getObject(bucketName, name);
        ObjectMetadata getMetadata = s3Object.getObjectMetadata();
        Blob blob = new Blob(name, s3Object.getObjectContent(),getMetadata.getContentType());
        return Optional.ofNullable(blob);
    }

    @Override
    public void deleteAll() {

    }
}
