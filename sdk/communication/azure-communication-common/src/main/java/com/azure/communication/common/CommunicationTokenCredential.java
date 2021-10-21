// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
package com.azure.communication.common;

import com.azure.communication.common.implementation.TokenParser;
import com.azure.core.credential.AccessToken;
import com.azure.core.util.FluxUtil;
import com.azure.core.util.logging.ClientLogger;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.time.OffsetDateTime;
import java.util.Date;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;
import java.util.function.Supplier;

import static com.azure.communication.common.CommunicationTokenRefreshOptions.DEFAULT_EXPIRING_OFFSET_MINUTES;

/**
 * Provide user credential for Communication service user
 */
public final class CommunicationTokenCredential implements AutoCloseable {
    private final ClientLogger logger = new ClientLogger(CommunicationTokenCredential.class);
    private final CommunicationTokenRefreshOptions tokenRefreshOptions;

    private AccessToken accessToken;
    private final TokenParser tokenParser = new TokenParser();
    private Supplier<Mono<String>> refresher;
    private FetchingTask fetchingTask;
    private boolean isClosed = false;

    /**
     * Create with serialized JWT token
     *
     * @param token serialized JWT token
     */
    public CommunicationTokenCredential(String token) {
        Objects.requireNonNull(token, "'token' cannot be null.");
        tokenRefreshOptions = null;
        setToken(token);
    }

    /**
     * Create with tokenRefreshOptions, which includes a token supplier and optional serialized JWT token.
     * If refresh proactively is true, callback function tokenRefresher will be called
     * ahead of the token expiry by the number of minutes specified by
     * CallbackOffsetMinutes defaulted to ten minutes. To modify this default, call
     * setCallbackOffsetMinutes after construction
     *
     * @param tokenRefreshOptions implementation to supply fresh token when reqested
     */
    public CommunicationTokenCredential(CommunicationTokenRefreshOptions tokenRefreshOptions) {
        Supplier<Mono<String>> tokenRefresher = tokenRefreshOptions.getTokenRefresher();
        Objects.requireNonNull(tokenRefresher, "'tokenRefresher' cannot be null.");
        this.tokenRefreshOptions = tokenRefreshOptions;
        refresher = tokenRefresher;
        if (tokenRefreshOptions.getInitialToken() != null) {
            setToken(tokenRefreshOptions.getInitialToken());
            if (tokenRefreshOptions.isRefreshProactively()) {
                OffsetDateTime nextFetchTime = getNextFetchTime();
                fetchingTask = new FetchingTask(this, nextFetchTime);
            }
        }
    }

    private OffsetDateTime getNextFetchTime() {
        int offsetMinutes = (tokenRefreshOptions != null)
            ? tokenRefreshOptions.getRefreshOffsetMinutes()
            : DEFAULT_EXPIRING_OFFSET_MINUTES;
        return accessToken.getExpiresAt().minusMinutes(offsetMinutes);
    }

    /**
     * Get Azure core access token from credential
     *
     * @return Asynchronous call to fetch actual token
     */
    public Mono<AccessToken> getToken() {
        if (isClosed) {
            return FluxUtil.monoError(logger,
                new RuntimeException("getToken called on closed CommunicationTokenCredential object"));
        }
        if ((accessToken == null || accessToken.isExpired()) && refresher != null) {
            synchronized (this) {
                // no valid token to return and can refresh
                if ((accessToken == null || accessToken.isExpired()) && refresher != null) {
                    return fetchFreshToken()
                        .map(token -> {
                            accessToken = tokenParser.parseJWTToken(token);
                            return accessToken;
                        });
                }
            }
        }
        return Mono.just(accessToken);
    }

    @Override
    public void close() throws IOException {
        isClosed = true;
        if (fetchingTask != null) {
            fetchingTask.stopTimer();
            fetchingTask = null;
        }
        refresher = null;
    }

    // For test verification usage only
    boolean hasProactiveFetcher() {
        return fetchingTask != null;
    }

    private void setToken(String freshToken) {
        accessToken = tokenParser.parseJWTToken(freshToken);

        if (fetchingTask != null) {
            OffsetDateTime nextFetchTime = getNextFetchTime();
            fetchingTask.setNextFetchTime(nextFetchTime);
        }
    }

    private Mono<String> fetchFreshToken() {
        Mono<String> tokenAsync = refresher.get();
        if (tokenAsync == null) {
            return FluxUtil.monoError(logger,
                new RuntimeException("get() function of the token refresher should not return null."));
        }
        return tokenAsync;
    }

    private static class FetchingTask {
        private final CommunicationTokenCredential host;
        private Timer expiringTimer;
        private OffsetDateTime nextFetchTime;

        FetchingTask(CommunicationTokenCredential tokenHost,
            OffsetDateTime nextFetchAt) {
            host = tokenHost;
            nextFetchTime = nextFetchAt;
            startTimer();
        }

        private synchronized void setNextFetchTime(OffsetDateTime newFetchTime) {
            nextFetchTime = newFetchTime;
            stopTimer();
            startTimer();
        }

        private synchronized void startTimer() {
            expiringTimer = new Timer();
            Date expiring = Date.from(nextFetchTime.toInstant());
            expiringTimer.schedule(new TokenExpiringTask(this), expiring);
        }

        private synchronized void stopTimer() {
            if (expiringTimer == null) {
                return;
            }

            expiringTimer.cancel();
            expiringTimer.purge();
            expiringTimer = null;
        }

        private Mono<String> fetchFreshToken() {
            return host.fetchFreshToken();
        }

        private void setToken(String freshTokenString) {
            host.setToken(freshTokenString);
        }

        private class TokenExpiringTask extends TimerTask {
            private final ClientLogger logger = new ClientLogger(TokenExpiringTask.class);
            private final FetchingTask tokenCache;

            TokenExpiringTask(FetchingTask host) {
                tokenCache = host;
            }

            @Override
            public void run() {
                try {
                    Mono<String> tokenAsync = tokenCache.fetchFreshToken();
                    tokenCache.setToken(tokenAsync.block());
                } catch (Exception exception) {
                    logger.logExceptionAsError(new RuntimeException(exception));
                }

            }
        }
    }
}
