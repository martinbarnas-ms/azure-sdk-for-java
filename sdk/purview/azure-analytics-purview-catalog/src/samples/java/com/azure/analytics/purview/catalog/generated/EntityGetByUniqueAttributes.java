// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.azure.analytics.purview.catalog.generated;

import com.azure.analytics.purview.catalog.EntityClient;
import com.azure.analytics.purview.catalog.EntityClientBuilder;
import com.azure.core.http.rest.RequestOptions;
import com.azure.core.http.rest.Response;
import com.azure.core.util.BinaryData;
import com.azure.identity.DefaultAzureCredentialBuilder;

public class EntityGetByUniqueAttributes {
    public static void main(String[] args) {
        // BEGIN: com.azure.analytics.purview.catalog.generated.entitygetbyuniqueattributes.entitygetbyuniqueattributes
        EntityClient entityClient =
                new EntityClientBuilder()
                        .credential(new DefaultAzureCredentialBuilder().build())
                        .endpoint("{Endpoint}")
                        .buildClient();
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.addQueryParam("attr:qualifiedName", "https://exampleaccount.core.windows.net");
        Response<BinaryData> response =
                entityClient.getByUniqueAttributesWithResponse("azure_storage_account", requestOptions);
        // END: com.azure.analytics.purview.catalog.generated.entitygetbyuniqueattributes.entitygetbyuniqueattributes
    }
}
