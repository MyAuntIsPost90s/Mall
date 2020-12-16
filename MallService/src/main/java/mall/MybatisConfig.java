package mall;

import com.alibaba.druid.pool.DruidDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.annotation.Resource;
import javax.sql.DataSource;

@Configuration
public class MybatisConfig {

    @Resource
    private volatile Environment environment;

    @Bean("dataSource")
    public DataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(environment.getProperty("datasource.driver-class-name"));
        dataSource.setUrl(environment.getProperty("datasource.url"));
        dataSource.setUsername(environment.getProperty("datasource.username"));
        dataSource.setPassword(environment.getProperty("datasource.password"));
        dataSource.setMaxActive(Integer.valueOf(environment.getProperty("datasource.maxActive")));
        dataSource.setInitialSize(Integer.valueOf(environment.getProperty("datasource.initialSize")));
        dataSource.setMaxWait(Integer.valueOf(environment.getProperty("datasource.maxWait")));
        dataSource.setMinIdle(Integer.valueOf(environment.getProperty("datasource.minIdle")));
        dataSource.setValidationQuery(environment.getProperty("datasource.validationQuery"));
        dataSource.setPoolPreparedStatements(Boolean.valueOf(environment.getProperty("datasource.poolPreparedStatements")));
        dataSource.setMaxOpenPreparedStatements(Integer.valueOf(environment.getProperty("datasource.maxOpenPreparedStatements")));
        return dataSource;
    }

    @Bean("sqlSessionFactory")
    public SqlSessionFactoryBean sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sqlSessionFactoryBean.setConfigLocation(resolver.getResource(environment.getProperty("mybatis.config-location")));
        sqlSessionFactoryBean.setMapperLocations(resolver.getResources(environment.getProperty("mybatis.mapper-locations")));
        return sqlSessionFactoryBean;
    }

    @Bean("dataSourceTransactionManager")
    public DataSourceTransactionManager dataSourceTransactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}
