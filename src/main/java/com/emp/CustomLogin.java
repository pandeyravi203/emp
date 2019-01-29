/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.emp;

import java.io.IOException;
import java.util.Collection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

/**
 *
 * @author ravi
 */
@Configuration
public class CustomLogin extends SimpleUrlAuthenticationSuccessHandler{
    
    protected void handle(HttpServletRequest request,HttpServletResponse response,Authentication auth) throws IOException
    {
        String targetUrl=determineTargetUrl(auth);
        
        if(response.isCommitted())
            return;
        
        RedirectStrategy redirectStrategy =new DefaultRedirectStrategy();
        redirectStrategy.sendRedirect(request, response, targetUrl);
        
    }
    
    protected String determineTargetUrl(Authentication auth) {
        boolean isUser = false;
        boolean isAdmin = false;
        
        Collection<? extends GrantedAuthority> authorities
         = auth.getAuthorities();
        for (GrantedAuthority grantedAuthority : authorities) {
            if (grantedAuthority.getAuthority().equals("user")) {
                isUser = true;
                 
                break;
            } else if (grantedAuthority.getAuthority().equals("admin")) {
                isAdmin = true;
                break;
            }
        }
 
        if (isUser) {
            return "/homePage";
        } else if (isAdmin) {
           
            return "/adminPage";
        } else {
            throw new IllegalStateException();
        }
    }
 
    
}
