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

package top.osjf.sdk.core.process;

import top.osjf.sdk.core.client.PreProcessingResponseHandler;
import top.osjf.sdk.core.client.ResponseConvert;
import top.osjf.sdk.core.exception.SdkException;

/**
 * When requesting an exception, a corresponding message is generated,
 * and the corresponding field is often not found through JSON conversion.
 * <p>At this point, this interface defines two standard methods.
 *
 * <p>In the event of an exception, the code and message are directly set through
 * {@link DefaultErrorResponse#parseErrorResponse} conversion.
 *
 * <p>The user needs to rewrite these two methods to obtain the corresponding
 * exception information.
 *
 * <p>The code is exclusively defined in {@link DefaultErrorResponse}.</p>
 *
 * @author <a href="mailto:929160069@qq.com">zhangpengfei</a>
 * @since 1.0.0
 */
public interface ErrorResponse {

    /**
     * There are three fixed types defined here:
     * <dl>
     *     <dt>{@link DefaultErrorResponse#SDK_ERROR_CODE}Sent {@link SdkException}
     *     when SDK is abnormal.</dt>
     *     <dt>{@link DefaultErrorResponse#DATA_ERROR_CODE}Unknown, inherited from {@link Exception}
     *     when an exception is sent, rather than a known exception.</dt>
     *     <dt>{@link DefaultErrorResponse#UNKNOWN_ERROR_CODE}When there are exceptions during data conversion,
     *     you can focus on the
     *     {@link PreProcessingResponseHandler#preResponseStrHandler(Request, String)}
     *     or {@link ResponseConvert#convertToResponse(Request, String)} methods.</dt>
     * </dl>
     *
     * <p>When and only when the request fails, the success of the request can be determined based
     * on whether this method sends data.</p>
     *
     * @param code Defined as an int type, it is fixed.
     */
    void setErrorCode(Object code);

    /**
     * The setting of abnormal conversion information, with the help of tool
     * {@link cn.hutool.core.exceptions.ExceptionUtil#stacktraceToOneLineString(Throwable)},
     * converts the abnormal information after conversion.
     *
     * <p>When and only when the request fails, the success of the request can be determined based
     * on whether this method sends data.</p>
     *
     * @param message Real message stack of {@link Throwable}.
     */
    void setErrorMessage(String message);
}