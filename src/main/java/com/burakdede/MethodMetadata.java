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
import com.burakdede.http.HttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.client.Entity;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by burakdede on 15.10.15.
 */
public class MethodMetadata<T> {

    private final static Logger LOGGER = LoggerFactory.getLogger(MethodMetadata.class);

    private Method m;

    private Type returnType;

    private String path;

    private RequestType httpRequestType;

    private Map<Integer, String> queryParams = new HashMap<>();
    private Map<String, String> headers = new HashMap<>();
    private Map<Integer, String> pathParams = new HashMap<>();

    private Type body;

    private Class returnClass;

    public MethodMetadata(Method m, String path, RequestType httpRequestType) {
        this.m = m;
        this.path = path;
        this.httpRequestType = httpRequestType;
    }

    public void setReturnClass(Class returnClass) {
        this.returnClass = returnClass;
    }

    public Type getBody() {
        return body;
    }

    public void setBody(Type body) {
        this.body = body;
    }

    public void addNewPathParam(Integer integer, String name) {
        pathParams.putIfAbsent(integer, name);
    }

    public void addNewHeader(String key, String value) {
        headers.putIfAbsent(key, value);
    }

    public void addNewQueryParam(Integer index, String name) {
        queryParams.putIfAbsent(index, name);
    }

    public void setReturnType(Type returnType) {
        this.returnType = returnType;
    }


    public <T> Object invoke(String url, HttpClient httpClient, Object[] args) {

        Object t = null;
        String fullPath = url + path;

        switch (httpRequestType) {
            case GET:
                 t = httpClient.get(
                        headers,
                        HoodieMetadataParser.parseQueryParams(queryParams, args),
                        HoodieMetadataParser.replacePathParams(fullPath, pathParams, args),
                        returnClass
                    );
                break;
            case POST:
                 t = httpClient.post(
                        headers,
                        Entity.json(body),
                        HoodieMetadataParser.replacePathParams(fullPath, pathParams, args),
                        returnClass
                    );
                break;
            case HEAD:
                t = httpClient.head(
                        headers,
                        HoodieMetadataParser.replacePathParams(fullPath, pathParams, args)
                    );
                break;
            case DELETE:
                httpClient.delete(
                        headers,
                        HoodieMetadataParser.replacePathParams(fullPath, pathParams, args),
                        returnClass
                    );
                break;
            default:
                LOGGER.error("Unsupported http method type: " + httpRequestType);
                break;
        }

        return t;
    }
}
