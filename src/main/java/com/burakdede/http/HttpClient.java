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

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;
import java.util.Map;

/**
 * Created by burakdede on 18.10.15.
 */
public interface HttpClient {

    Response head(Map<String, String> headers, String url);

    <T> T get(Map<String, String> headers, Map<String, String> queryParams, String url, Class c);

    <T> T post(Map<String, String> headers, Entity entity, String url, Class c);

    <T> T delete(Map<String, String> headers, String url, Class c);
}
