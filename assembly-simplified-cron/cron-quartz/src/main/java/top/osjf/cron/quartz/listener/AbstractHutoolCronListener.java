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

package top.osjf.cron.quartz.listener;

import org.quartz.JobExecutionContext;
import org.quartz.JobKey;
import top.osjf.cron.core.listener.IdCronListener;

/**
 * The abstract service class of the listener for {@code Hutool} includes the
 * stage method passing that specifies the task ID.
 *
 * @author <a href="mailto:929160069@qq.com">zhangpengfei</a>
 * @since 1.0.1
 */
public abstract class AbstractHutoolCronListener implements QuartzCronListener, IdCronListener<JobKey> {

    @Override
    public void onStart(JobExecutionContext value) {
        onStartWithId(value.getJobDetail().getKey());
    }

    @Override
    public void onSucceeded(JobExecutionContext value) {
        onSucceededWithId(value.getJobDetail().getKey());
    }

    @Override
    public void onFailed(JobExecutionContext value, Throwable exception) {
        onFailedWithId(value.getJobDetail().getKey(), exception);
    }

    @Override
    public void onStartWithId(JobKey id) {
    }

    @Override
    public void onSucceededWithId(JobKey id) {
    }

    @Override
    public void onFailedWithId(JobKey id, Throwable exception) {
    }
}
