/*
 * Copyright 2024-? the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package top.osjf.sdk.core.exception;

/**
 * The exception thrown by the body parameter verification of SDK is usually
 * the judgment of business logic parameters, which is thrown during the request.
 *
 * @author <a href="mailto:929160069@qq.com">zhangpengfei</a>
 * @since 1.0.0
 */
public class SdkException extends IllegalArgumentException {

    private static final long serialVersionUID = -7204419580156052252L;

    public SdkException() {
        super();
    }

    public SdkException(String s) {
        super(s);
    }

    public SdkException(String message, Throwable cause) {
        super(message, cause);
    }

    public SdkException(Throwable cause) {
        super(cause);
    }
}