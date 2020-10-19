package com.softraysolutions.api.util;

import com.softraysolutions.PropertiesReader;

public class ReusableStrings {
    public static final String AUTHORIZATION_API_PATH = "/auth";
    public static final String LOGIN_API_PATH = "/login";
    public static final String NEWS_API_PATH = "/news";
    public static final String TYPE = "/type";
    public static final String NEW_USER = "/register";
    public static final String SECRET_KEY = PropertiesReader.getProperty("jwt.secret-key");
    public static final String ISSUER = PropertiesReader.getProperty("jwt.issuer");
    public static final String HEADER_STRING = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";
}
