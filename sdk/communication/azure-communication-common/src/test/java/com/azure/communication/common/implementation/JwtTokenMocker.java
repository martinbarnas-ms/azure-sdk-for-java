// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
package com.azure.communication.common.implementation;

import com.nimbusds.jwt.JWT;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.PlainJWT;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class JwtTokenMocker {

    public String generateRawToken(String resourceId, String userIdentity, int validForSeconds) {
        String skypeId = generateMockId(resourceId, userIdentity);
        JWTClaimsSet.Builder builder = new JWTClaimsSet.Builder();
        builder.claim("skypeid", skypeId);
        LocalDateTime expiresOnTimestamp = LocalDateTime.now().plusSeconds(validForSeconds);
        ZonedDateTime ldt = expiresOnTimestamp.atZone(ZoneId.systemDefault());
        long expSeconds = ldt.toInstant().toEpochMilli() / 1000;
        builder.claim("exp", expSeconds);

        JWTClaimsSet claims =  builder.build();
        JWT idToken = new PlainJWT(claims);
        return idToken.serialize();
    }

    public String generateMockId(String resourceId, String userIdentity) {
        return "communication:" + resourceId + "." + userIdentity;
    }

}
