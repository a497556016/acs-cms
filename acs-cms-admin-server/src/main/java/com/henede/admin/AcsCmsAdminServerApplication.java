package com.henede.admin;

import com.henede.auth.filter.AuthFilter;
import org.activiti.engine.IdentityService;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableEurekaClient
@EnableConfigurationProperties
@EnableTransactionManagement
@ComponentScan(basePackages = {"com.henede"})
public class AcsCmsAdminServerApplication {

	public static void main(String[] args) {

		SpringApplication.run(AcsCmsAdminServerApplication.class, args);
	}

	@Bean
	InitializingBean usersAndGroupsInitializer(final IdentityService identityService) {

		return new InitializingBean() {
			public void afterPropertiesSet() throws Exception {

//				Group group = identityService.newGroup("user");
//				group.setName("users");
//				group.setType("security-role");
//				identityService.saveGroup(group);
//
//				User admin = identityService.newUser("heshaowei");
//				admin.setPassword("123456");
//				identityService.saveUser(admin);

			}
		};
	}
}
