package com.rd.epam.autotasks.scopes.scopeRegistry;

import com.rd.epam.autotasks.scopes.config.*;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

@Component
public class ScopeRegister implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory factory) throws BeansException {
        factory.registerScope("justASecond", new JustASecondScopeConfig());
        factory.registerScope("thread", new ThreadScopeConfig());
        factory.registerScope("threeTimes", new ThreeTimesScopeConfig());
    }
}
