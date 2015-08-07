package com.webs.user.filter;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.SignedJWT;
import com.webs.user.AppSecurityConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
 * JWT Authentication service
 */
@Service
public class JwtFilter extends GenericFilterBean {

	private static final Logger logger = LoggerFactory.getLogger(JwtFilter.class);

	@Autowired
	private AppSecurityConfiguration config;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		final HttpServletRequest req = (HttpServletRequest) request;
		final HttpServletResponse resp = (HttpServletResponse)response;

		if (!isRouteSecured(req.getRequestURI())) {
			chain.doFilter(request, response);
			return;
		}

		String tokenParam = req.getParameter("token");

		if (tokenParam == null) {
			//If not on the query/post-body, try the auth header
			tokenParam = req.getHeader("Authorization");
		}

		if (tokenParam != null) {
			try {
				SignedJWT parsedToken = SignedJWT.parse(tokenParam);
				final String issuer = parsedToken.getJWTClaimsSet().getIssuer();

				if (issuer != null && this.config.hasPartner(issuer)) {
					JWSVerifier verifier = new MACVerifier(this.config.getPartnerSecret(issuer));
					if (parsedToken.verify(verifier)) {
						req.setAttribute("issuer", issuer);
						req.setAttribute("claims", parsedToken.getJWTClaimsSet());
						chain.doFilter(request, response);
						return;
					}
				} else {
					logger.warn("Unknown issuer");
				}
			} catch (ParseException e) { //Failed to parse token
				logger.warn("Error parsing token");
			} catch (JOSEException e) { //Error verifying
				logger.warn("Error verifying token");
			}
		}
		resp.sendError(401, "Authentication failed");
	}

	private boolean isRouteSecured(String uri) {
		for(String root : this.config.getSecureRoutes()) {
			if (uri.startsWith(root))
				return true;
		}
		return false;
	}
}
