package com.project.preferences.config.converter;

import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.support.ConfigurableConversionService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class ConverterConfiguration {
    private final Set<Converter<?, ?>> converters;
    private final ConfigurableConversionService configurableConversionService;

    @PostConstruct
    public void setUp() {
        converters.forEach(configurableConversionService::addConverter);
    }

}