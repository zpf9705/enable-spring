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

package top.osjf.cron.spring.hutool;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Role;
import top.osjf.cron.hutool.lifestyle.HutoolCronLifeStyle;
import top.osjf.cron.hutool.repository.HutoolCronTaskRepository;

/**
 * Regarding the configuration classes related to scheduled task
 * registration for hutool.
 *
 * @see EnableHutoolCronTaskRegister
 * @author <a href="mailto:929160069@qq.com">zhangpengfei</a>
 * @since 1.0.0
 */
@Configuration(proxyBeanMethods = false)
@Role(BeanDefinition.ROLE_INFRASTRUCTURE)
public class HutoolCronTaskConfiguration {

    @Bean(destroyMethod = "stop")
    @Role(BeanDefinition.ROLE_INFRASTRUCTURE)
    public HutoolCronLifeStyle hutoolCronLifeStyle() {
        return new HutoolCronLifeStyle();
    }

    @Bean
    @Role(BeanDefinition.ROLE_INFRASTRUCTURE)
    public HutoolCronTaskRegistrant cronTaskRegistrant() {
        HutoolCronTaskRepository hutoolCronTaskRepository = new HutoolCronTaskRepository();
        return new HutoolCronTaskRegistrant(hutoolCronTaskRepository);
    }

    @Bean
    public HutoolCronTaskRepository cronTaskRepository(HutoolCronTaskRegistrant cronTaskRegistrant) {
        return cronTaskRegistrant.getCronTaskRepository();
    }
}