package com.mtknack.agenda.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

	@Bean
	ModelMapper modelMapper() {
		var modelMapper = new ModelMapper();
		customizeMappers( modelMapper );
		return modelMapper;
	}

	/**
	 * Add your custom mapping to Entity <--> DTO here.
	 *
	 * @param modelMapper
	 */
	public static void customizeMappers( ModelMapper modelMapper ) {
		modelMapper.getConfiguration().setAmbiguityIgnored( true );
	}

}
