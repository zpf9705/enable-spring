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

package top.osjf.cron.spring.quartz;

import org.quartz.JobDetail;
import top.osjf.cron.core.repository.CronTaskRepository;
import top.osjf.cron.quartz.repository.QuartzCronTaskRepository;
import top.osjf.cron.spring.AbstractCronTaskRealRegistrant;
import top.osjf.cron.spring.Registrant;

/**
 * Quartz of scheduled task registration actors.
 *
 * @author <a href="mailto:929160069@qq.com">zhangpengfei</a>
 * @since 1.0.0
 */
public class QuartzCronTaskRealRegistrant extends AbstractCronTaskRealRegistrant {

    public QuartzCronTaskRealRegistrant(QuartzCronTaskRepository cronTaskRepository) {
        super(cronTaskRepository);
    }

    @Override
    public void register(Registrant registrant) throws Exception {
        if (registrant instanceof QuartzRegistrant) {
            CronTaskRepository<String, JobDetail> cronTaskRepository = getCronTaskRepository();
            QuartzRegistrant quartzRegistrant = (QuartzRegistrant) registrant;
            cronTaskRepository.register(quartzRegistrant.getCronExpression(), quartzRegistrant.getJobDetail());
        }
    }
}