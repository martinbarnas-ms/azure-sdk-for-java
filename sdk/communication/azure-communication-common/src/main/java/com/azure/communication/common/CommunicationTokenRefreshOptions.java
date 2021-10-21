// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
package com.azure.communication.common;

import reactor.core.publisher.Mono;

import java.util.function.Supplier;

/**
 * Options for refreshing CommunicationTokenCredential
 */
public final class CommunicationTokenRefreshOptions {
    public static final int DEFAULT_EXPIRING_OFFSET_MINUTES = 5;
    private final Supplier<Mono<String>> tokenRefresher;
    private final boolean refreshProactively;
    private final String initialToken;
    private final int refreshOffsetMinutes;

    /**
     * Creates a CommunicationTokenRefreshOptions object
     *
     * @param tokenRefresher the token refresher to provide capacity to fetch fresh token
     * @param refreshProactively when set to true, turn on proactive fetching to call
     *                           tokenRefresher before token expiry by minutes set
     *                           with setCallbackOffsetMinutes or default value of
     *                           two minutes
     */
    public CommunicationTokenRefreshOptions(Supplier<Mono<String>> tokenRefresher, boolean refreshProactively) {
        this.tokenRefresher = tokenRefresher;
        this.refreshProactively = refreshProactively;
        this.initialToken = null;
        refreshOffsetMinutes = DEFAULT_EXPIRING_OFFSET_MINUTES;
    }

     /**
     * Creates a CommunicationTokenRefreshOptions object
     *
     * @param tokenRefresher the token refresher to provide capacity to fetch fresh token
     * @param refreshProactively when set to true, turn on proactive fetching to call
     *                           tokenRefresher before token expiry by minutes set
     *                           with setCallbackOffsetMinutes or default value of
     *                           two minutes
     * @param initialToken the optional serialized JWT token
     */
    public CommunicationTokenRefreshOptions(Supplier<Mono<String>> tokenRefresher, boolean refreshProactively, String initialToken) {
        this.tokenRefresher = tokenRefresher;
        this.refreshProactively = refreshProactively;
        this.initialToken = initialToken;
        refreshOffsetMinutes = DEFAULT_EXPIRING_OFFSET_MINUTES;
    }

    /**
     * Creates a CommunicationTokenRefreshOptions object
     *
     * @param tokenRefresher the token refresher to provide capacity to fetch fresh token
     * @param refreshProactively when set to true, turn on proactive fetching to call
     *                           tokenRefresher before token expiry by minutes set
     *                           with setCallbackOffsetMinutes or default value of
     *                           two minutes
     * @param initialToken the optional serialized JWT token
     * @param refreshOffsetMinutes Refresh token before expiration
     */
    public CommunicationTokenRefreshOptions(Supplier<Mono<String>> tokenRefresher, boolean refreshProactively, String initialToken, int refreshOffsetMinutes) {
        this.tokenRefresher = tokenRefresher;
        this.refreshProactively = refreshProactively;
        this.initialToken = initialToken;
        this.refreshOffsetMinutes = refreshOffsetMinutes;
    }

    /**
     * @return the token refresher to provide capacity to fetch fresh token
     */
    public Supplier<Mono<String>> getTokenRefresher() {
        return tokenRefresher;
    }

    /**
     * @return whether or not to refresh token proactively
     */
    public boolean isRefreshProactively() {
        return refreshProactively;
    }

    /**
     * @return the initial token
     */
    public String getInitialToken() {
        return initialToken;
    }

    /**
     * @return the refresh offset in minutes
     */
    public int getRefreshOffsetMinutes() {
        return refreshOffsetMinutes;
    }
}
