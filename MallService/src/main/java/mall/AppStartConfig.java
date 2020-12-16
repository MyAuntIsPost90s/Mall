package mall;

import com.ch.web.gateway.boot.FastGatewayBoot;
import com.ch.web.gateway.boot.FastGatewayBootBuilder;
import com.ch.web.gateway.interceptor.AuthorityInterceptor;
import com.ch.web.gateway.interceptor.EncodeInterceptor;
import com.ch.web.gateway.session.LocalSessionPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;

@Configuration
@PropertySource("classpath:application.properties")
public class AppStartConfig {

    @Autowired
    private ApplicationContext applicationContext;

    @Bean
    public FastGatewayBoot fastGatewayBoot() {
        return FastGatewayBootBuilder
                .newBuilder(applicationContext)
                .allowCross(true)
                .registerSessionPool(new LocalSessionPool(false, 1000 * 60 * 60 * 24, 1000))
                .registerInterceptor(new InterceptorRegistration(new EncodeInterceptor())
                        .addPathPatterns("/**")
                )
                .registerInterceptor(new InterceptorRegistration(new AuthorityInterceptor())
                        .excludePathPatterns(
                                "/**/login*",
                                "/**/regist*",
                                "/**/Uploadfile/**/*",
                                "/**/upload*",
                                "/**/export*",
                                "/webapp/**/*"
                        )
                        .addPathPatterns("/**")
                )
                .build()
                .start();
    }
}
