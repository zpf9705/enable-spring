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
 * Throwing indicates that the SDK method parameter or
 * return value conversion failed and an illegal or
 * inappropriate type was passed.
 *
 * @author <a href="mailto:929160069@qq.com">zhangpengfei</a>
 * @since 1.0.0
 */
public class DataConvertException extends SdkIllegalArgumentException {
    private static final long serialVersionUID = 2144997773083517532L;

    /**
     * Creates a {@code DataConvertException} by given need convent type
     * and the error message description.
     *
     * @param conventType the need convent type,such as "Json".
     * @param message     the convert error message.
     */
    public DataConvertException(String conventType, String message) {
        super(conventType + " : " + message);
    }
}
