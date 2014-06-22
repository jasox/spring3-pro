// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.apress.prospring3.ch20.web.controller;

import com.apress.prospring3.ch20.domain.Contact;
import com.apress.prospring3.ch20.service.ContactService;
import com.apress.prospring3.ch20.web.controller.ApplicationConversionServiceFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;

privileged aspect ApplicationConversionServiceFactoryBean_Roo_ConversionService {
    
    declare @type: ApplicationConversionServiceFactoryBean: @Configurable;
    
    @Autowired
    ContactService ApplicationConversionServiceFactoryBean.contactService;
    
    public Converter<Contact, String> ApplicationConversionServiceFactoryBean.getContactToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<com.apress.prospring3.ch20.domain.Contact, java.lang.String>() {
            public String convert(Contact contact) {
                return new StringBuilder().append(contact.getFirstName()).append(" ").append(contact.getLastName()).append(" ").append(contact.getBirthDate()).toString();
            }
        };
    }
    
    public Converter<Long, Contact> ApplicationConversionServiceFactoryBean.getIdToContactConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, com.apress.prospring3.ch20.domain.Contact>() {
            public com.apress.prospring3.ch20.domain.Contact convert(java.lang.Long id) {
                return contactService.findContact(id);
            }
        };
    }
    
    public Converter<String, Contact> ApplicationConversionServiceFactoryBean.getStringToContactConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, com.apress.prospring3.ch20.domain.Contact>() {
            public com.apress.prospring3.ch20.domain.Contact convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), Contact.class);
            }
        };
    }
    
    public void ApplicationConversionServiceFactoryBean.installLabelConverters(FormatterRegistry registry) {
        registry.addConverter(getContactToStringConverter());
        registry.addConverter(getIdToContactConverter());
        registry.addConverter(getStringToContactConverter());
    }
    
    public void ApplicationConversionServiceFactoryBean.afterPropertiesSet() {
        super.afterPropertiesSet();
        installLabelConverters(getObject());
    }
    
}
