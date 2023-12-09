package com.cn.partnerservice.helpers;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.UUID;

/**
 * Helper class for interacting with Blob storage.
 *
 */
@Service
@RequiredArgsConstructor
public class BlobHelper {

    private final AzureProperties azureProperties;

    /**
     * Connection method for connecting to the blob Storage container.
     * @return
     */
    private BlobContainerClient containerClient() {
        BlobServiceClient serviceClient = new BlobServiceClientBuilder()
                .connectionString(azureProperties.getConnectionstring()).buildClient();
        BlobContainerClient container = serviceClient.getBlobContainerClient(azureProperties.getContainer());
        return container;
    }

    /**
     * Stores a given file into blob storage and then returns the path to that file.
     * This method is specifically for storing images belonging to a store.
     * @param filename
     * @param storeName
     * @param content
     * @param length
     * @return
     */
    public String storeFile(String filename, String storeName, InputStream content, long length) {
        BlobClient client = containerClient().getBlobClient(storeName.replaceAll("\\s+","")+filename);
        if (client.exists()) { //Check if file exists preventing duplicated data.
            return "The file was already located on azure";
        } else {
            client.upload(content, length);
            return client.getBlobUrl();
        }
    }

    /**
     * Stores a product image in the Blob storage container.
     * @param filename
     * @param content
     * @param length
     * @return
     */
    public String storeProductImage(String filename, InputStream content, long length) {
        final String uuid = UUID.randomUUID().toString().replace("-", "");
        BlobClient client = containerClient().getBlobClient(filename+uuid);
        if (client.exists()) {
            return "The file was already located on azure";
        } else {
            client.upload(content, length);
            return client.getBlobUrl();
        }
    }
}
