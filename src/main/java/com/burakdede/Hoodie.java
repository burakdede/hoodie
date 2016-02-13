package com.burakdede;/*
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
import org.glassfish.jersey.client.ClientConfig;

import java.lang.reflect.Proxy;

/**
 * Created by burakdede on 15.10.15.
 */
public class Hoodie {

    /**
     * Register new target
     *
     * @param clazz Interface type to register
     * @param baseUrl http url for remote
     * @param <T>
     * @return
     */
    public static <T> T registerNewTarget(Class<T> clazz, String baseUrl) {
        T target;

        HoodieMetadataParser.parse(clazz);
        target = (T) Proxy.newProxyInstance(clazz.getClassLoader(),
                new Class[] { clazz },
                new ReflectiveInvocationHandler(baseUrl));

        return target;
    }

    /**
     * Register new target with client config
     *
     * @param clazz Interface type to register
     * @param baseUrl http url for remote
     * @param config ClientConfig for jersey client
     * @param <T>
     * @return
     */
    public static <T> T registerNewTarget(Class<T> clazz, String baseUrl, ClientConfig config) {
        T target;

        HoodieMetadataParser.parse(clazz);
        target = (T) Proxy.newProxyInstance(clazz.getClassLoader(),
                new Class[]{clazz},
                new ReflectiveInvocationHandler(baseUrl, config));

        return target;
    }
}
