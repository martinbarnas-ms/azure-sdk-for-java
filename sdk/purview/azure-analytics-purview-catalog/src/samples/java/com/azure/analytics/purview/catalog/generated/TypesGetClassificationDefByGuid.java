// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.azure.analytics.purview.catalog.generated;

import com.azure.analytics.purview.catalog.TypesClient;
import com.azure.analytics.purview.catalog.TypesClientBuilder;
import com.azure.core.http.rest.RequestOptions;
import com.azure.core.http.rest.Response;
import com.azure.core.util.BinaryData;
import com.azure.identity.DefaultAzureCredentialBuilder;

public class TypesGetClassificationDefByGuid {
    public static void main(String[] args) {
        // BEGIN:
        // com.azure.analytics.purview.catalog.generated.typesgetclassificationdefbyguid.typesgetclassificationdefbyguid
        TypesClient typesClient =
                new TypesClientBuilder()
                        .credential(new DefaultAzureCredentialBuilder().build())
                        .endpoint("{Endpoint}")
                        .buildClient();
        RequestOptions requestOptions = new RequestOptions();
        Response<BinaryData> response =
                typesClient.getClassificationDefByGuidWithResponse(
                        "e79878fa-adba-4ee5-adc5-328d8841cd49", requestOptions);
        // END:
        // com.azure.analytics.purview.catalog.generated.typesgetclassificationdefbyguid.typesgetclassificationdefbyguid
    }
}
