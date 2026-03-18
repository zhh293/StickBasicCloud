package com.tmd.common.filter;

import com.tmd.common.util.BaseContext;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

public class UserContextHeaderFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        BaseContext.remove();
        String userId = request.getHeader("X-User-Id");
        if (StringUtils.hasText(userId)) {
            try {
                BaseContext.set(Long.parseLong(userId));
            } catch (NumberFormatException ignored) {
            }
        }
        try {
            filterChain.doFilter((jakarta.servlet.ServletRequest) request, (jakarta.servlet.ServletResponse) response);
        } finally {
            BaseContext.remove();
        }
    }
}
