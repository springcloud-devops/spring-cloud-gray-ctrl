package serviceB.config;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.github.pagehelper.PageHelper;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.util.Properties;


/**
 * Created by :Guozhihua
 * Date： 2017/8/18
 */
//@Configuration
//@Slf4j
//@ComponentScan
//@MapperScan("sc.demo.serviceA.dao")
public class MyBatisConfig  {
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger("TestController");

    @Value("${spring.datasource.type}")
    private Class<? extends DataSource> dataSourceType;
    @Value("${jdbc.druid.loginUsername}")
    private String druidLoginName;
    @Value("${jdbc.druid.loginPassword}")
    private String druidLoginPass;
    @Value("${mybatis.mapperLocations}")
    private String mapperLocations ;
    @Value("${mybatis.typeAliasesPackage}")
    private String typeAliasesPackage ;

    @Bean(name="dataSource", destroyMethod = "close", initMethod="init")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource() {
        logger.info("-------------------- dataSource init ---------------------");
        return DataSourceBuilder.create().type(dataSourceType).build();
    }
    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource());
        sqlSessionFactoryBean.setTypeAliasesPackage(typeAliasesPackage);
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sqlSessionFactoryBean.setMapperLocations(resolver.getResources(mapperLocations));
        sqlSessionFactoryBean.getObject().getConfiguration().setMapUnderscoreToCamelCase(true);
        
        // 分页插件
        PageHelper pageHelper = new PageHelper();
        Properties props = new Properties();
//        <!-- 4.0.0以后版本可以不设置该参数 -->
        props.setProperty("dialect","mysql");
        props.setProperty("supportMethodsArguments", "true");
        props.setProperty("returnPageInfo", "check");
        props.setProperty("params", "count=countSql");
        props.setProperty("rowBoundsWithCount","true");
        props.setProperty("pageSizeZero","true");
        props.setProperty("offsetAsPageNum","true");
        props.setProperty("reasonable","false");
        props.setProperty("pageSizeZero","true");
        props.setProperty("params","pageNum=start;pageSize=limit;");
        pageHelper.setProperties(props);
        // 添加插件
        sqlSessionFactoryBean.setPlugins(new Interceptor[] { pageHelper });
        return sqlSessionFactoryBean.getObject();
    }

    @Bean
    public ServletRegistrationBean druidServlet() {
        ServletRegistrationBean reg = new ServletRegistrationBean();
        reg.setServlet(new StatViewServlet());
        reg.addUrlMappings("/druid/*");
        reg.addInitParameter("loginUsername", this.druidLoginName);
        reg.addInitParameter("loginPassword", this.druidLoginPass);
        return reg;
    }
    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new WebStatFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        filterRegistrationBean.addInitParameter("profileEnable", "true");
        filterRegistrationBean.addInitParameter("principalCookieName", "USER_COOKIE");
        filterRegistrationBean.addInitParameter("principalSessionName", "USER_SESSION");
        return filterRegistrationBean;
    }
    /**
     * 配置事务管理器
     */
    @Bean(name = "transactionManager")
    @Primary
    public DataSourceTransactionManager transactionManager(@Qualifier("dataSource") DataSource dataSource) throws Exception {
        return new DataSourceTransactionManager(dataSource);
    }
}
