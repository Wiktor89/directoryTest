package net.directory.utilits;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.Collections;
import java.util.List;

/**
 *
 */
//@Configuration
//@EnableWebMvc
//@ComponentScan()
public class WebConfig extends WebMvcConfigurerAdapter {
	
	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		converter.setObjectMapper(new ObjectMapper());
		converter.setSupportedMediaTypes(Collections.singletonList(MediaType.APPLICATION_JSON));
		converters.add(converter);
	}
}
