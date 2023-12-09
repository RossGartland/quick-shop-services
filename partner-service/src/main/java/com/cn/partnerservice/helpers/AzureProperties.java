package com.cn.partnerservice.helpers;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Configuration class contain properties for connecting to Azure Blob storage.
 */
@Data
@ConfigurationProperties("azure.myblob")
public class AzureProperties {
    private String connectionstring; //Connection string for Blob storage.
    private String container; //Name of the Blob storage container.
}