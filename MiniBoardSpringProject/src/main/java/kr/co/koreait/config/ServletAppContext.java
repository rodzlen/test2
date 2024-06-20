package kr.co.koreait.config;
import kr.co.koreait.service.TopMenuService;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import kr.co.koreait.Interceptor.TopMenuInterceptor;
import kr.co.koreait.mapper.BoardMapper;


@Configuration
@EnableWebMvc
@ComponentScan("kr.co.koreait.dao")
@ComponentScan("kr.co.koreait.controller")
@PropertySource("/WEB-INF/properties/oracle.properties")
public class ServletAppContext implements WebMvcConfigurer{

	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		// TODO Auto-generated method stub
		WebMvcConfigurer.super.configureViewResolvers(registry);
		registry.jsp("/WEB-INF/views/", ".jsp");
	}
	
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// TODO Auto-generated method stub
		WebMvcConfigurer.super.addResourceHandlers(registry);
		registry.addResourceHandler("/**").addResourceLocations("/WEB-INF/resources/");
	}
	
	// properties 파일을 message로 등록하는 부분.(예외처리)
	@Bean
	public ReloadableResourceBundleMessageSource messageSource() {
		
		ReloadableResourceBundleMessageSource res = new ReloadableResourceBundleMessageSource();
		res.setBasename("/WEB-INF/properties/error_message");
		
		return res;
		
	}
	
	//oracle.properties
	
	@Value("${oracle.classname}")
	private String oracle_classname;
	
	@Value("${oracle.url}")
	private String oracle_url;
	
	@Value("${oracle.username}")
	private String oracle_username;
	
	@Value("${oracle.password}")
	private String oracle_password;
	
	
	@Autowired
	private TopMenuService TopMenuService;
	// db접속 정보에 대한 Bean 관리 설정
	
	@Bean
	public BasicDataSource dataSource() {
		BasicDataSource source = new BasicDataSource();
		source.setDriverClassName(oracle_classname);
		source.setUrl(oracle_url);
		source.setUsername(oracle_username);
		source.setPassword(oracle_password);
		
		return source;
	}
	
	// Query문 접속정보 연결하는 객체 생성 
	@Bean
	public SqlSessionFactory factory(BasicDataSource source) throws Exception {
		SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
		factoryBean.setDataSource(source);
		SqlSessionFactory factory = factoryBean.getObject();
		
		return factory;
		
	}
	
	//Query문 실행을 위한 객체 관리 (Mapper)
	@Bean
	public MapperFactoryBean<BoardMapper> test_mapper(SqlSessionFactory factory){
		MapperFactoryBean<BoardMapper> factoryBean = new MapperFactoryBean<BoardMapper>(BoardMapper.class);
		factoryBean.setSqlSessionFactory(factory);
		return factoryBean;
	}
	//Interceptor 등록 
	public void AddInterceptors(InterceptorRegistry registry) {
		
		WebMvcConfigurer.super.addInterceptors(registry);
		TopMenuInterceptor TopMenuInterceptor = new TopMenuInterceptor(TopMenuService);
		InterceptorRegistration reg1 = registry.addInterceptor(TopMenuInterceptor);
		reg1.addPathPatterns("/**");
		
	}
}












