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
package com.burakdede.http;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.client.*;
import javax.ws.rs.core.Response;
import java.util.Map;

/**
 * Created by burakdede on 18.10.15.
 */
public class JerseyClient implements HttpClient {

    private final static Logger LOGGER = LoggerFactory.getLogger(JerseyClient.class);

    private static Client client;

    public JerseyClient() {
        client = ClientBuilder.newClient().register(JacksonFeature.class);
    }

    public JerseyClient(ClientConfig clientConfig) {
        client = ClientBuilder.newClient(clientConfig).register(JacksonFeature.class);;
    }

    private WebTarget addHeaders(Map<String, String> headers, WebTarget target) {
        for (Map.Entry<String, String> header : headers.entrySet()) {
            target.request().header(header.getKey(), header.getValue());
        }
        return target;
    }

    @Override
    public Response head(Map<String, String> headers, String url) {
        WebTarget target = client.target(url);
        target = addHeaders(headers, target);
        Response response = target.request().head();

        return response;
    }

    @Override
    public <T> T get(Map<String, String> headers, Map<String, String> queryParams, String url, Class c) {
        WebTarget target = client.target(url);
        target = addHeaders(headers, target);
        for (Map.Entry param : queryParams.entrySet()) {
            target = target.queryParam((String) param.getKey(), param.getValue());
        }
        T t = (T) target.request().get(c);

        return t;
    }

    @Override
    public <T> T post(Map<String, String> headers,
                         Entity entity,
                         String url,
                         Class c) {
        WebTarget target = client.target(url);
        target = addHeaders(headers, target);
        T t = (T) target.request().post(entity, c);

        return t;


    }

    @Override
    public <T> T delete(Map<String, String> headers, String url, Class c) {
        WebTarget target = client.target(url);
        target = addHeaders(headers, target);
        T t = (T) target.request().delete(c);

        return t;
    }
}
