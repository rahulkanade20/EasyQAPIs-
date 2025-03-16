package com.EasyQ.API.util;

import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.StringUtils;

@UtilityClass
public class TokenUtil {
    public static final String BEARER = "Bearer ";

    public static boolean containsBearerToken(String authorizationHeader) {
        return StringUtils.isNoneEmpty(authorizationHeader) && authorizationHeader.startsWith(BEARER);
    }

    public static String extractBearerToken(String authorizationHeader) {
        if(StringUtils.isEmpty(authorizationHeader)) {
            return StringUtils.EMPTY;
        }
        return authorizationHeader.substring(BEARER.length());
    }
}
