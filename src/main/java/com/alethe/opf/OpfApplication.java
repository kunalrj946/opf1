package com.alethe.opf;

import java.util.Properties;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * Created by Kunal Kumar
 */

@SpringBootApplication
public class OpfApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		new SpringApplicationBuilder(OpfApplication.class).sources(OpfApplication.class).properties(getProperties())
				.run(args);

	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder springApplicationBuilder) {
		return springApplicationBuilder.sources(OpfApplication.class).properties(getProperties());

	}

	static Properties getProperties() {
		Properties props = new Properties();
		props.put("spring.config.location", "classpath:/");
		return props;

	}
	
//	@Override
//	   protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
//	      return application.sources(OpfApplication.class);
//	   }
//	   public static void main(String[] args) {
//	      SpringApplication.run(OpfApplication.class, args);
//	   }
	
	

}
