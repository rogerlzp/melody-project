package com.melody.web.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;

@Configuration
public class MessageConfig {

    @Value(value = "${spring.messages.basename}")
    private String basename;

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename(basename);
        messageSource.setCacheSeconds(10); //reload messages every 10 seconds
        return messageSource;
    }

    /**
     * 根据 key
     *
     * @param messageKey
     * @return
     */
    public String getMessage(String messageKey) {
        String message = messageSource().getMessage(messageKey, null, LocaleContextHolder.getLocale());
        return message;
    }

    /**
     * 根据 key,参数
     *
     * @param messageKey
     * @param args
     * @return
     */
    public String getMessage(String messageKey, Object... args) {
        String message = messageSource().getMessage(messageKey, args, LocaleContextHolder.getLocale());
        return message;
    }
}