package com.rd.epam.autotasks.scopes.config;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


@Component
@ComponentScan(basePackages = "com.rd.epam.autotasks.scopes")
@SuppressWarnings("unused")
public class ThreeTimesScopeConfig implements Scope {
    private final Map<String, Object> scopedObjects
            = Collections.synchronizedMap(new HashMap<>());
    private final Map<String, Runnable> destructionCallbacks
            = Collections.synchronizedMap(new HashMap<>());

    private int count = 1;

    @Override
    public Object get(String beanName, ObjectFactory<?> objectFactory) {
        if (!scopedObjects.containsKey(beanName)) {
            count = 1;
        }

        if (count <= 3) {
            scopedObjects.putIfAbsent(beanName, objectFactory.getObject());
            count++;
        } else {
            scopedObjects.put(beanName, objectFactory.getObject());
            count = 2;
        }

        return scopedObjects.get(beanName);
    }

    @Override
    public Object remove(String name) {
        destructionCallbacks.remove(name);
        return scopedObjects.remove(name);
    }

    @Override
    public void registerDestructionCallback(String name, Runnable callback) {
        destructionCallbacks.put(name, callback);
    }

    @Override
    public Object resolveContextualObject(String s) {
        return null;
    }

    @Override
    public String getConversationId() {
        return "threeTimes";
    }
}
