package com.icat.orboarding.user.adapters.configuration

import org.springframework.context.MessageSource
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean

@Configuration
class ValidationConfig {

    @Bean
    fun validation(messageSource: MessageSource):LocalValidatorFactoryBean {
        val bean = LocalValidatorFactoryBean()
        bean.setValidationMessageSource(messageSource)
        return bean
    }

}