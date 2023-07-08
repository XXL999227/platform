package cn.xxl.platform.system.security;

import cn.xxl.platform.system.exception.CommonException;
import cn.xxl.platform.system.utils.JwtUtil;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * jwt身份验证令牌过滤器
 *
 * @author xxl
 * @since 2023/06/27
 */
@Component
// OncePerRequestFilter: 保证在一次请求只通过一次filter，而不需要重复执行
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    public static final String AUTHORIZATION = "Authorization";

    @Resource
    private UserDetailsService userDetailsService;

    @Override
    @Transactional
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader(AUTHORIZATION);
        String requestURI = request.getRequestURI();
        if ("/api/v1/user/login".equals(requestURI)) {
            filterChain.doFilter(request, response);
            return;
        }

        if (token == null) {
            throw new CommonException("token不能为空");
        }
        // token过期,这个有问题，token过期了，就无法通过verify了
        if (JwtUtil.verify(token) && JwtUtil.isExpired(token)) {
            throw new CommonException("token已过期");
        }

        if (!JwtUtil.verify(token)) {
            throw new CommonException("token不合法");
        }

        String username = JwtUtil.getUsername(token);
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        filterChain.doFilter(request, response);
    }
}
