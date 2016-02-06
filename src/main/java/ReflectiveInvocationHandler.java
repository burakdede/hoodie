/*
 * Copyright (C) Burak Dede.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
import http.HttpClient;
import http.JerseyClient;
import org.glassfish.jersey.client.ClientConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by burakdede on 15.10.15.
 */
public class ReflectiveInvocationHandler implements InvocationHandler {

    private final static Logger LOGGER = LoggerFactory.getLogger(ReflectiveInvocationHandler.class.getSimpleName());

    private static Map<Method, MethodMetadata> methodCache = new HashMap<>();

    private String baseUrl;
    private HttpClient httpClient;

    public ReflectiveInvocationHandler(String baseUrl) {
        this.baseUrl = baseUrl;
        httpClient = new JerseyClient();
    }

    public ReflectiveInvocationHandler(String baseUrl, ClientConfig config) {
        this.baseUrl = baseUrl;
        httpClient = new JerseyClient(config);
    }

    public static void putInCache(Method m, MethodMetadata metadata) {
        methodCache.putIfAbsent(m, metadata);
    }

    public static MethodMetadata getMethodFromCache(Method method) {
        return methodCache.getOrDefault(method, null);
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        MethodMetadata methodMetadata = getMethodFromCache(method);

        try {
            if (methodMetadata != null) {
                return methodMetadata.invoke(baseUrl, httpClient, args);
            } else {
                return method.invoke(this, args);
            }
        } catch (InvocationTargetException e) {
            LOGGER.trace("Problem while invoking method", e);
        }

        return null;
    }
}
