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
/**
 * Created by burakdede on 28.10.15.
 */
public enum RequestType {

    HEAD, GET, POST, DELETE, UNSUPPORTED;

    public static RequestType findRequestType(String httpMethod) {
        switch (httpMethod.toLowerCase()) {
            case "get":
                return GET;
            case "post":
                return POST;
            case "delete":
                return DELETE;
            case "head":
                return HEAD;
            default:
                return UNSUPPORTED;
        }
    }
}
