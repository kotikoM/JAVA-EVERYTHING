package com.rd.epam.autotasks.scopes.config;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.beans.factory.config.Scope;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Component
@ComponentScan(basePackages = "com.rd.epam.autotasks.scopes")
public class JustASecondScopeConfig implements Scope {

    private final Map<String, Object> scopedObjects
            = Collections.synchronizedMap(new HashMap<>());
    private final Map<String, Runnable> destructionCallbacks
            = Collections.synchronizedMap(new HashMap<>());
    private final Map<String, Long> lastAccessTimes
            = Collections.synchronizedMap(new HashMap<>());
    @Override
    public Object get(String name, ObjectFactory<?> objectFactory) {
        long currentTime = System.currentTimeMillis();
        long lastAccessTime = lastAccessTimes.getOrDefault(name, 0L);
        long difference = (currentTime - lastAccessTime) / 1000;

        if (!scopedObjects.containsKey(name) || difference >= 1) {
            scopedObjects.put(name, objectFactory.getObject());
            lastAccessTimes.put(name, currentTime);
        }

        return scopedObjects.get(name);
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
        return "justASecond";
    }
}
