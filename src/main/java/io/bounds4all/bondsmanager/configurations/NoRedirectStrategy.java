package io.bounds4all.bondsmanager.configurations;

import org.springframework.security.web.RedirectStrategy;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NoRedirectStrategy  implements RedirectStrategy {
    @Override
    public void sendRedirect(HttpServletRequest request, HttpServletResponse response, String url)  {
    }
}
