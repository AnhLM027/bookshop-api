package ptit.edu.vn.bookshop.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.HandlerMapping;
import ptit.edu.vn.bookshop.domain.entity.Permission;
import ptit.edu.vn.bookshop.domain.entity.Role;
import ptit.edu.vn.bookshop.domain.entity.User;
import ptit.edu.vn.bookshop.exception.PermissionException;
import ptit.edu.vn.bookshop.service.UserService;
import ptit.edu.vn.bookshop.util.security.SecurityUtil;

import java.util.List;

@Service
public class PermissionIntercepter implements HandlerInterceptor {
    @Autowired
    private UserService userService;

    private static final Logger log = LoggerFactory.getLogger(PermissionIntercepter.class);

    @Override
    @Transactional
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String path = (String) request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE);
        String httpMethod = request.getMethod();
        log.debug("Checking permission for path: {}", path);
        log.debug("HTTP Method: {}", httpMethod);
        // check permission
        String email = SecurityUtil.getCurrentUserLogin().isPresent() ? SecurityUtil.getCurrentUserLogin().get() : "";
        if (email != null && !email.isEmpty()) {
            log.debug("Authenticated user: {}", email);
            User user = this.userService.getUserByUsername(email);
            if (user != null) {
                Role role = user.getRole();
                if (role != null) {
                    List<Permission> permissions = role.getPermissions();
                    boolean isAllow = permissions.stream().anyMatch(p ->
                            p.getApiPath().equals(path) && p.getMethod().equalsIgnoreCase(httpMethod));
                    // neu khong co quyen
                    if (!isAllow) {
                        log.warn("Permission DENIED for user: {} | path: {} | method: {}", email, path, httpMethod);
                        throw new PermissionException("User don't have permission to access this resource");
                    }
                    log.info("Permission GRANTED for user: {} | path: {} | method: {}", email, path, httpMethod);
                } else {
                    log.error("User {} has no role assigned!", email);
                    throw new PermissionException("User don't have permission to access this resource");
                }
            }
            else {
                log.error("User not found in DB: {}", email);
            }
        } else {
            log.warn("No authenticated user (email is empty)");
        }
        return true;
    }
}
