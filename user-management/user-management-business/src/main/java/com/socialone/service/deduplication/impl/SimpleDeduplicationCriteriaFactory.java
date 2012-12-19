package com.socialone.service.deduplication.impl;

import java.util.Collection;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import com.google.common.collect.ImmutableList;
import com.socialone.service.deduplication.DeduplicationCriteria;
import com.socialone.service.deduplication.DeduplicationCriteriaFactory;


@Component
public class SimpleDeduplicationCriteriaFactory implements DeduplicationCriteriaFactory, ApplicationContextAware {

    private Collection<DeduplicationCriteria> deduplicationCriterias;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        // Step 1. Extracting all Deduplication Criterias
        deduplicationCriterias = ImmutableList.copyOf(applicationContext.getBeansOfType(DeduplicationCriteria.class).values());
    }

    @Override
    public Collection<DeduplicationCriteria> getRegisteredCriterias() {
        return deduplicationCriterias;
    }

}
