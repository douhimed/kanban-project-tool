package org.adex.security;

import org.adex.services.impl.CustomUserDetailsServices;
import org.adex.web.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

import static org.adex.security.SecurityConstants.HEADER_AUTH;
import static org.adex.security.SecurityConstants.JWT_PREFIX;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	@Autowired
	private CustomUserDetailsServices customUserDetailsServices;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
			String jwt = this.getTokenFromRequest(request);
			if (StringUtils.hasText(jwt) && this.jwtTokenProvider.validateToken(jwt)) {
				int userId = this.jwtTokenProvider.getUserIdFromToken(jwt);
				User user = this.customUserDetailsServices.loadUserById(userId);

				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null,
						Collections.emptyList());

				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}

		} catch (Exception ex) {
			logger.error("Could not set user authnetication in security context : ", ex);
		}

		filterChain.doFilter(request, response);
	}

	private String getTokenFromRequest(HttpServletRequest request) {
		String bearerToken = request.getHeader(HEADER_AUTH);
		return StringUtils.hasText(bearerToken) && bearerToken.startsWith(JWT_PREFIX)
				? bearerToken.substring(7, bearerToken.length())
				: null;
	}

}
