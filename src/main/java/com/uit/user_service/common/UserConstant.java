package com.uit.user_service.common;

import com.uit.microservice_base_project.common.BaseConstant;

public class UserConstant extends BaseConstant {

    public static final String CREATE_USER="/create";

    public static final String LOGIN_USER = "/login";
    public static final String BECOME_A_HOST = "/host/become-a-host";

    public static final String ERROR = "ERROR";


    public static final String SERVICE_NAME ="user" ;
    public static final String VALIDATE_TOKEN ="/validate" ;
    public static final String GET_USER_ID = "/get-id";
    public static final String GET_RECENT_PROPERTY = "/get-recent-property";
}
