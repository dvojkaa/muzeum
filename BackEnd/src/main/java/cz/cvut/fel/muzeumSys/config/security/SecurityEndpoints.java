package cz.cvut.fel.muzeumSys.config.security;

import lombok.Getter;
import lombok.experimental.UtilityClass;


@UtilityClass
public class SecurityEndpoints {

        public static final String[] PUBLIC_URLS = {
                "/qrcodes/**",
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



//    final RequestMatcher PUBLIC_URLS = new OrRequestMatcher(
//            antMatcher(HttpMethod.POST,"/user/register"),
////            antMatcher(HttpMethod.POST,"/art/**"),
//            antMatcher(HttpMethod.POST,"/user/login")
//    );
//
//    final RequestMatcher ADMIN_URLS = new OrRequestMatcher(
//            antMatcher(HttpMethod.GET,"/**")
//    );
//
//    final RequestMatcher EMPLOYEE_URLS = new OrRequestMatcher(
//            antMatcher(HttpMethod.GET,"/employees/**"),
//            antMatcher(HttpMethod.POST,"/group/create")
//    );
//
//    final RequestMatcher MULTI_ROLE_URLS = new OrRequestMatcher(
//   );
}