package org.people.config;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.core.annotation.Order;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.thetransactioncompany.cors.CORSFilter;

@Order(2)
public class WebInitializer extends
		AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	public void onStartup(ServletContext servletContext)
			throws ServletException {
		FilterRegistration.Dynamic corsFilter = servletContext.addFilter(
				"CORS", new CORSFilter());
		/**
		 * Note: All parameters are options, if omitted the CORS Filter will
		 * fall back to the respective default values.
		 */
		corsFilter.setInitParameter("cors.allowGenericHttpRequests", "true");
		corsFilter.setInitParameter("cors.allowOrigin", "*");
		corsFilter.setInitParameter("cors.allowSubdomains", "false");
		corsFilter.setInitParameter("cors.supportedMethods",
				"GET, HEAD, POST, DELETE, OPTIONS");
		corsFilter.setInitParameter("cors.supportedHeaders", "*");
		corsFilter
				.setInitParameter("cors.exposedHeaders", "X-Test-1, X-Test-2");
		corsFilter.setInitParameter("cors.supportsCredentials", "true");
		corsFilter.setInitParameter("cors.maxAge", "3600");

		corsFilter.addMappingForUrlPatterns(null, false, "/*");

		super.onStartup(servletContext);
	}

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] { WebMvcConfig.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] { WebMvcConfig.class };
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}

}
