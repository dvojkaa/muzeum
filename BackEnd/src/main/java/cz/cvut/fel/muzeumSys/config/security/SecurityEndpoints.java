package cz.cvut.fel.muzeumSys.config.security;

import lombok.experimental.UtilityClass;
import org.springframework.http.HttpMethod;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

@UtilityClass
public class SecurityEndpoints {


    final RequestMatcher PUBLIC_URLS = new OrRequestMatcher(
            antMatcher(HttpMethod.POST,"/auth/**")
    );

    final RequestMatcher ADMIN_URLS = new OrRequestMatcher(
            antMatcher(HttpMethod.GET,"/**")
    );

    final RequestMatcher EMPLOYEE_URLS = new OrRequestMatcher(
            antMatcher(HttpMethod.GET,"/employees/**")
    );

    final RequestMatcher MULTI_ROLE_URLS = new OrRequestMatcher(
            antMatcher(HttpMethod.GET,"/training/**"),
            antMatcher(HttpMethod.GET,"/training")
    );
}