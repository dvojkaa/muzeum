package cz.cvut.fel.muzeumSys.config.security;

import lombok.experimental.UtilityClass;


@UtilityClass
public class SecurityEndpoints {

    public static final String[] PUBLIC_URLS = {
            "/user/register",
            "/user/login",
            "/login",
            "/art"
    };

    public static final String[] ADMIN_URLS = {
            "/admin/**"
    };

    public static final String[] EMPLOYEE_URLS = {
            "/employee/**",
            "/group/create"
    };
}