package com.tkart.ecommerce.middleware;

import org.springframework.stereotype.Component;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
public class JwtFilter implements Filter {
     @Override
     public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
               throws IOException, ServletException {
          HttpServletRequest req = (HttpServletRequest) request;
          // TODO: Add JWT validation logic here
          chain.doFilter(request, response);
     }
}
