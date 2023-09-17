package com.rd.epam.autotasks.scopes.config;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Component
@ComponentScan(basePackages = "com.rd.epam.autotasks.scopes")
@SuppressWarnings("unused")
public class ThreadScopeConfig implements Scope {
    private final Map<String, Object> scopedObjects
            = Collections.synchronizedMap(new HashMap<>());
    private final Map<String, Runnable> destructionCallbacks
            = Collections.synchronizedMap(new HashMap<>());
    private final ThreadLocal<Map<String, Object>> threadScope =
            ThreadLocal.withInitial(ConcurrentHashMap::new);

    @Override
    public Object get(String name, ObjectFactory<?> objectFactory) {
        return threadScope.get()
                .computeIfAbsent(name, key -> objectFactory.getObject());
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
        return "thread";
    }
}
