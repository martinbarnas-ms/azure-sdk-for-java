// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.azure.resourcemanager.eventgrid.generated;

import com.azure.core.util.Context;

/** Samples for SystemTopicEventSubscriptions Get. */
public final class SystemTopicEventSubscriptionsGetSamples {
    /*
     * x-ms-original-file: specification/eventgrid/resource-manager/Microsoft.EventGrid/preview/2021-10-15-preview/examples/SystemTopicEventSubscriptions_Get.json
     */
    /**
     * Sample code: SystemTopicEventSubscriptions_Get.
     *
     * @param manager Entry point to EventGridManager.
     */
    public static void systemTopicEventSubscriptionsGet(com.azure.resourcemanager.eventgrid.EventGridManager manager) {
        manager
            .systemTopicEventSubscriptions()
            .getWithResponse("examplerg", "exampleSystemTopic1", "examplesubscription1", Context.NONE);
    }
}
