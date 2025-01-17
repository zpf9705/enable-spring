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

package top.osjf.sdk.http.client;

import top.osjf.sdk.core.exception.SdkException;
import top.osjf.sdk.core.Request;
import top.osjf.sdk.http.HttpRequest;

import java.util.function.Supplier;

/**
 * The {@code HttpResultSolver} interface is designed to handle different scenarios of HTTP
 * request results,including SDK exceptions, unknown exceptions, and the final processing flow.
 * <p>
 * This interface defines four main parts:
 * <ul>
 *     <li>{@link #handlerSdkError}Handles SDK exceptions. When an SDK exception
 *     occurs during request processing,this method is used for handling.</li>
 *     <li>{@link #handlerUnKnowError}Handles unknown exceptions. When an unknown
 *     exception (such as a network exception)occurs during request processing,
 *     this method is used for handling.</li>
 *     <li>{@link #finallyHandler}Called in the finally block of try-catch to
 *     execute the final processing flow,such as logging, statistics, etc.</li>
 *     <li>ExecuteInfo interface and its builder ExecuteInfoBuild: Encapsulates
 *     the metadata of the request call,including the request duration, whether
 *     an error occurred, request parameters, response content, and error message.</li>
 * </ul>
 * <p>
 * By implementing this interface, developers can customize exception handling
 * and final processing flows to meet different business requirements.
 *
 * @author <a href="mailto:929160069@qq.com">zhangpengfei</a>
 * @since 1.0.0
 */
public interface HttpResultSolver {

    /**
     * The type of {@link SdkException} exception that runs during the
     * request processing is usually manually checked and thrown in the
     * reference method {@link Request#validate()}.
     *
     * @param request Request parameters.
     * @param e       Exception {@link SdkException}.
     */
    void handlerSdkError(HttpRequest<?> request, SdkException e);

    /**
     * The type of {@link Throwable} exception that runs during the
     * request processing is usually an exception thrown by the requester
     * or other unknown exceptions such as network exceptions.
     *
     * @param request Request parameters.
     * @param e       UnKnow Exception {@link Throwable}.
     */
    void handlerUnKnowError(HttpRequest<?> request, Throwable e);

    /**
     * The final process is to call the interface implementation
     * class {@link ExecuteInfo} encapsulated by the metadata of
     * this request call in the finally of try catch.
     *
     * @param info {@link ExecuteInfo}.
     */
    void finallyHandler(ExecuteInfo info);

    /**
     * Usually collected for general information after execution.
     */
    interface ExecuteInfo {

        /**
         * @return Returns the number of milliseconds spent on this request.
         */
        long getSpendTotalTimeMillis();

        /**
         * @return Return the {@link Supplier} package type indicating whether the request was successful.
         */
        Supplier<Boolean> noHappenError();

        /**
         * @return Returns the parameters for this request.
         */
        HttpRequest<?> getHttpRequest();

        /**
         * @return Returns the body of this request.
         */
        String getResponse();

        /**
         * @return Returns the error response information for this request.
         */
        String getErrorMessage();
    }

    /**
     * The builder class for interface {@link ExecuteInfo}.
     */
    class ExecuteInfoBuild {

        long spendTotalTimeMillis;

        Throwable error;

        HttpRequest<?> httpRequest;

        String response;

        public static ExecuteInfoBuild builder() {
            return new ExecuteInfoBuild();
        }

        public ExecuteInfoBuild spend(long spendTotalTimeMillis) {
            this.spendTotalTimeMillis = spendTotalTimeMillis;
            return this;
        }

        public ExecuteInfoBuild requestAccess(HttpRequest<?> httpRequest) {
            this.httpRequest = httpRequest;
            return this;
        }

        public ExecuteInfoBuild maybeError(Throwable error) {
            this.error = error;
            return this;
        }

        public ExecuteInfoBuild response(String response) {
            this.response = response;
            return this;
        }

        public DefaultExecuteInfo build() {
            return new DefaultExecuteInfo(
                    spendTotalTimeMillis,
                    error,
                    httpRequest,
                    response
            );
        }
    }
}
