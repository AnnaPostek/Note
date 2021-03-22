package pl.postek.webservice.notes.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.envers.repository.support.EnversRevisionRepositoryFactoryBean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@Configuration
@EnableJpaRepositories(repositoryFactoryBeanClass = EnversRevisionRepositoryFactoryBean.class,
        basePackages = "pl.postek.webservice.notes.repository")
public class EnversDemoConfiguration {

}
