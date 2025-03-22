//package cz.cvut.fel.muzeumSys.config.security;
//
//import lombok.experimental.UtilityClass;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.web.util.matcher.OrRequestMatcher;
//import org.springframework.security.web.util.matcher.RequestMatcher;
//
//import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;
//
//@UtilityClass
//public class SecurityEndpoints {
//
//
//    final RequestMatcher PUBLIC_URLS = new OrRequestMatcher(
//            antMatcher(HttpMethod.POST,"/auth/**")
//    );
//
//    final RequestMatcher ADMIN_URLS = new OrRequestMatcher(
//            antMatcher(HttpMethod.GET,"/**")
//    );
//
//    final RequestMatcher EMPLOYEE_URLS = new OrRequestMatcher(
//            antMatcher(HttpMethod.GET,"/employees/**")
//    );
//
//    final RequestMatcher MULTI_ROLE_URLS = new OrRequestMatcher(
//            antMatcher(HttpMethod.GET,"/training/**"),
//            antMatcher(HttpMethod.GET,"/training")
//    );
//}

package cz.cvut.fel.muzeumSys.config.security;

import lombok.experimental.UtilityClass;
import org.springframework.http.HttpMethod;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

@UtilityClass
public class SecurityEndpoints {

    public static final RequestMatcher PUBLIC_URLS = new OrRequestMatcher(
            new AntPathRequestMatcher("/login", HttpMethod.POST.name()),
            new AntPathRequestMatcher("/register", HttpMethod.POST.name()),
            new AntPathRequestMatcher("/employee/login", HttpMethod.POST.name()),
            new AntPathRequestMatcher("/art", HttpMethod.GET.name())
    );

    public static final RequestMatcher ADMIN_URLS = new AntPathRequestMatcher("/admin/**");

    public static final RequestMatcher EMPLOYEE_URLS = new AntPathRequestMatcher("/employee/**");
}

