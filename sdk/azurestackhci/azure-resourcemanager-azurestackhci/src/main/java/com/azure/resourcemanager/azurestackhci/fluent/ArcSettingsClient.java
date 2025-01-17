// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.azure.resourcemanager.azurestackhci.fluent;

import com.azure.core.annotation.ReturnType;
import com.azure.core.annotation.ServiceMethod;
import com.azure.core.http.rest.PagedIterable;
import com.azure.core.http.rest.Response;
import com.azure.core.management.polling.PollResult;
import com.azure.core.util.Context;
import com.azure.core.util.polling.SyncPoller;
import com.azure.resourcemanager.azurestackhci.fluent.models.ArcSettingInner;

/** An instance of this class provides access to all the operations defined in ArcSettingsClient. */
public interface ArcSettingsClient {
    /**
     * Get ArcSetting resources of HCI Cluster.
     *
     * @param resourceGroupName The name of the resource group. The name is case insensitive.
     * @param clusterName The name of the cluster.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws com.azure.core.management.exception.ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return arcSetting resources of HCI Cluster as paginated response with {@link PagedIterable}.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    PagedIterable<ArcSettingInner> listByCluster(String resourceGroupName, String clusterName);

    /**
     * Get ArcSetting resources of HCI Cluster.
     *
     * @param resourceGroupName The name of the resource group. The name is case insensitive.
     * @param clusterName The name of the cluster.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws com.azure.core.management.exception.ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return arcSetting resources of HCI Cluster as paginated response with {@link PagedIterable}.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    PagedIterable<ArcSettingInner> listByCluster(String resourceGroupName, String clusterName, Context context);

    /**
     * Get ArcSetting resource details of HCI Cluster.
     *
     * @param resourceGroupName The name of the resource group. The name is case insensitive.
     * @param clusterName The name of the cluster.
     * @param arcSettingName The name of the proxy resource holding details of HCI ArcSetting information.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws com.azure.core.management.exception.ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return arcSetting resource details of HCI Cluster.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    ArcSettingInner get(String resourceGroupName, String clusterName, String arcSettingName);

    /**
     * Get ArcSetting resource details of HCI Cluster.
     *
     * @param resourceGroupName The name of the resource group. The name is case insensitive.
     * @param clusterName The name of the cluster.
     * @param arcSettingName The name of the proxy resource holding details of HCI ArcSetting information.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws com.azure.core.management.exception.ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return arcSetting resource details of HCI Cluster along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    Response<ArcSettingInner> getWithResponse(
        String resourceGroupName, String clusterName, String arcSettingName, Context context);

    /**
     * Create ArcSetting for HCI cluster.
     *
     * @param resourceGroupName The name of the resource group. The name is case insensitive.
     * @param clusterName The name of the cluster.
     * @param arcSettingName The name of the proxy resource holding details of HCI ArcSetting information.
     * @param arcSetting Parameters supplied to the Create ArcSetting resource for this HCI cluster.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws com.azure.core.management.exception.ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return arcSetting details.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    ArcSettingInner create(
        String resourceGroupName, String clusterName, String arcSettingName, ArcSettingInner arcSetting);

    /**
     * Create ArcSetting for HCI cluster.
     *
     * @param resourceGroupName The name of the resource group. The name is case insensitive.
     * @param clusterName The name of the cluster.
     * @param arcSettingName The name of the proxy resource holding details of HCI ArcSetting information.
     * @param arcSetting Parameters supplied to the Create ArcSetting resource for this HCI cluster.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws com.azure.core.management.exception.ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return arcSetting details along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    Response<ArcSettingInner> createWithResponse(
        String resourceGroupName,
        String clusterName,
        String arcSettingName,
        ArcSettingInner arcSetting,
        Context context);

    /**
     * Delete ArcSetting resource details of HCI Cluster.
     *
     * @param resourceGroupName The name of the resource group. The name is case insensitive.
     * @param clusterName The name of the cluster.
     * @param arcSettingName The name of the proxy resource holding details of HCI ArcSetting information.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws com.azure.core.management.exception.ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the {@link SyncPoller} for polling of long-running operation.
     */
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    SyncPoller<PollResult<Void>, Void> beginDelete(String resourceGroupName, String clusterName, String arcSettingName);

    /**
     * Delete ArcSetting resource details of HCI Cluster.
     *
     * @param resourceGroupName The name of the resource group. The name is case insensitive.
     * @param clusterName The name of the cluster.
     * @param arcSettingName The name of the proxy resource holding details of HCI ArcSetting information.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws com.azure.core.management.exception.ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the {@link SyncPoller} for polling of long-running operation.
     */
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    SyncPoller<PollResult<Void>, Void> beginDelete(
        String resourceGroupName, String clusterName, String arcSettingName, Context context);

    /**
     * Delete ArcSetting resource details of HCI Cluster.
     *
     * @param resourceGroupName The name of the resource group. The name is case insensitive.
     * @param clusterName The name of the cluster.
     * @param arcSettingName The name of the proxy resource holding details of HCI ArcSetting information.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws com.azure.core.management.exception.ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    void delete(String resourceGroupName, String clusterName, String arcSettingName);

    /**
     * Delete ArcSetting resource details of HCI Cluster.
     *
     * @param resourceGroupName The name of the resource group. The name is case insensitive.
     * @param clusterName The name of the cluster.
     * @param arcSettingName The name of the proxy resource holding details of HCI ArcSetting information.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws com.azure.core.management.exception.ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    void delete(String resourceGroupName, String clusterName, String arcSettingName, Context context);
}
