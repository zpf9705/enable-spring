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

package top.osjf.sdk.http;

import top.osjf.sdk.core.support.NotNull;
import top.osjf.sdk.http.process.AbstractHttpResponse;
import top.osjf.sdk.http.process.HttpSdkEnum;
import top.osjf.sdk.http.process.JsonSerialSPILoggerHttpRequestParams;

import static top.osjf.sdk.http.CultivateSupportHttpRequestParams.InstanceHolder.getSdkEnumManager;

/**
 * Extends for {@code JsonSerialSPILoggerHttpRequestParams} to provider a {@code Client}
 * is {@code ServiceLoaderLoggerHttpClient} and json serialized and also support as
 * {@code CultivateSupportHttpRequestParams} to find {@link HttpSdkEnumCultivate} Annotations
 * transform {@link HttpSdkEnum} and benefit from {@link HttpSdkEnumManager} management.
 *
 * @author <a href="mailto:929160069@qq.com">zhangpengfei</a>
 * @since 1.0.2
 */
@SuppressWarnings({"unchecked"})
public abstract class CultivateSupportJsonSerialSPILoggerHttpRequestParams<R extends AbstractHttpResponse>
        extends JsonSerialSPILoggerHttpRequestParams<R> {

    private static final long serialVersionUID = -7551894800516076762L;

    /**
     * Get {@code HttpSdkEnum} managed by
     * {@code SdkEnumCultivateSupportHttpRequestParams#InstanceHolder#SDK_EUM_MANAGER}.
     *
     * @return {@inheritDoc}
     */
    @Override
    @NotNull
    public final HttpSdkEnum matchSdkEnum() {
        return getSdkEnumManager().getAndSetHttpSdkEnum(this);
    }
}