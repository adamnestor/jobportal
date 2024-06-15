package com.luv2code.jobportal.config;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// This configuration class will map requests for /photos to serve files from a directory on our file system
@Configuration
public class MvcConfig implements WebMvcConfigurer{

	private static final String UPLOAD_DIR = "photos";

	//Override the default implementation to set up a custom resource handler
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		exposeDirectory(UPLOAD_DIR, registry);
	}

	// Converts the uploadDir string to a Path
	// Maps requests starting with "/photos?**" to a file system location file:<absolute path to photos directory>
	// The ** will match on all sub-directories
	private void exposeDirectory(String uploadDir, ResourceHandlerRegistry registry) {
		Path path = Paths.get(uploadDir);
		registry.addResourceHandler("/" + uploadDir + "/**").addResourceLocations("file:" + path.toAbsolutePath() + "/");
	}
	
	
}
