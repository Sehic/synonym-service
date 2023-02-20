package com.reeinvent.backend.synonym;

import com.reeinvent.backend.BaseApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class SynonymApplication extends BaseApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(SynonymApplication.class)
				.web(WebApplicationType.NONE)
				.build()
				.run(args);
	}
}
