package aop.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CarConfig {
    @Bean
    public Car laoSiJi() {
        return new QQcar();
    }

}
