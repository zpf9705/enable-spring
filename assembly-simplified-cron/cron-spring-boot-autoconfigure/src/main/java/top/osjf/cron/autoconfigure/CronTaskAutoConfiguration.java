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

package top.osjf.cron.autoconfigure;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Role;
import top.osjf.cron.core.repository.CronListenerRepository;

/**
 * {@link org.springframework.boot.autoconfigure.EnableAutoConfiguration}
 * for Cron Task , include Hutool ,Quartz ,Cron4j ,enhance Scheduling .
 *
 * @author <a href="mailto:929160069@qq.com">zhangpengfei</a>
 * @since 1.0.0
 */
@Configuration(proxyBeanMethods = false)
@Role(BeanDefinition.ROLE_INFRASTRUCTURE)
@EnableConfigurationProperties(CronProperties.class)
@Import({HutoolCronTaskAutoConfiguration.class,
        QuartzCronTaskAutoConfiguration.class,
        Cron4jCronTaskAutoConfiguration.class,
        SchedulingAutoConfiguration.class})
public class CronTaskAutoConfiguration {

    @Bean
    @ConditionalOnBean(CronListenerRepository.class)
    @Role(BeanDefinition.ROLE_INFRASTRUCTURE)
    @SuppressWarnings("rawtypes")
    public CronListenerRegistrant cronListenerRegistrant(CronListenerRepository cronListenerRepository) {
        return new CronListenerRegistrant(cronListenerRepository);
    }
}