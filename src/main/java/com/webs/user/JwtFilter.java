package com.webs.user;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.SignedJWT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;

/**
 * Created by chris on 8/5/15.
 */
public class JwtFilter extends GenericFilterBean {

    private static final Logger logger = LoggerFactory.getLogger(JwtFilter.class);

    @Autowired
    private AppConfig config;

    public JwtFilter() {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        final HttpServletRequest req = (HttpServletRequest) request;
        final HttpServletResponse resp = (HttpServletResponse)response;

        String tokenParam = req.getParameter("token");

        if (tokenParam == null)
        {
            //If not on the query/post-body, try the auth header
            tokenParam = req.getHeader("Authorization");
        }

        if (tokenParam != null) {
            try {
                JWSVerifier verifier = new MACVerifier("asdfalksjdflkajsdflkjaslkdfjlasjdflkasjdflkjasldfjlasjdflkajsdlfkjaslkdfj");
                SignedJWT parsedToken = SignedJWT.parse(tokenParam);

                if (parsedToken.verify(verifier)) {
                    req.setAttribute("claims", parsedToken.getJWTClaimsSet());
                    chain.doFilter(request, response);
                    return;
                }
            } catch (ParseException e) { //Failed to parse token
                logger.warn("Error parsing token");
            } catch (JOSEException e) { //Error verifying
                logger.warn("Error verifying token");
            }
        }
        resp.sendError(401, "Authentication failed");
    }
}
