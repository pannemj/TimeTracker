package com.allianz.tracker.util;

import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.stereotype.Component;

@Component
public class CommonBeansUtil {
	
	public void copy(Object source, Object target) {
		String[] nullProperties = getNullPropertyNames(source);
		BeanUtils.copyProperties(source, target, nullProperties);
	}

	private String[] getNullPropertyNames(Object source, String... extraFields) {

		final BeanWrapper beanWrapper = new BeanWrapperImpl(source);
		PropertyDescriptor[] propertyDescriptors = beanWrapper.getPropertyDescriptors();
		Set<String> propertyNames = new HashSet<>();

		for (java.beans.PropertyDescriptor propertyDescriptor : propertyDescriptors) {
			Object srcValue = beanWrapper.getPropertyValue(propertyDescriptor.getName());
			if (srcValue == null) {
				propertyNames.add(propertyDescriptor.getName());
			}
		}

		for (String field : extraFields) {
			propertyNames.add(field);
		}
		return propertyNames.toArray(new String[propertyNames.size()]);
	}

}
