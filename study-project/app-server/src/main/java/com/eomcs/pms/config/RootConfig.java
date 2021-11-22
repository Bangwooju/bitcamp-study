package com.eomcs.pms.config;

import javax.sql.DataSource;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@ComponentScan(
    basePackages = "com.eomcs.pms",
    excludeFilters = {
        @Filter(type = FilterType.REGEX, pattern = "com.eomcs.web.*")
    })

//3) JDBC 정보를 담고 있는 프로퍼티 파일을 로딩한다.
@PropertySource("classpath:com/eomcs/pms/config/jdbc.properties")

//4) 애노테이션을 이용하여 트랜잭션을 다루는 기능을 활성화시킨다.
@EnableTransactionManagement

//8) 지정된 패키지의 DAO 인터페이스에 대해 구현체를 자동으로 생성한다.
@MapperScan("com.eomcs.pms.dao")

public class RootConfig {

  //수동으로 생성할 객체가 있다면, 다음과 같이 그 객체를 만들어 리턴하는 메서드를 정의하라!
  // 단 메서드 선언에 @Bean을 붙여서 빈 컨테이너에게 이 메서드를 호출하라고 요구해야 한다.
  // 그리고 이 메서드의 리턴 값을 컨테이너에 보관해 두라고 요구해야 한다.

  // 5) DB 커넥션풀 객체 생성
  // => DB 커넥션을 생성한 후 내부 버퍼에 보관해 둔다.
  // => 요청할 때 빌려주고, 사용 후 반납 받는다.
  // => 그래서 DB 커넥션을 매번 생성하지 않게 한다.
  @Bean
  public DataSource dataSource(
      @Value("${jdbc.driver}") String jdbcDriver,
      @Value("${jdbc.url}") String jdbcUrl,
      @Value("${jdbc.username}") String jdbcUsername,
      @Value("${jdbc.password}") String jdbcPassword) {


    DriverManagerDataSource ds = new DriverManagerDataSource();
    ds.setDriverClassName(jdbcDriver);
    ds.setUrl(jdbcUrl);
    ds.setUsername(jdbcUsername);
    ds.setPassword(jdbcPassword);
    return ds;
  }

  // 6) 트랜잭션 관리자 생성
  // => commit/rollback 을 다룬다.
  @Bean 
  public PlatformTransactionManager transactionManager(DataSource dataSource) {
    return new DataSourceTransactionManager(dataSource);
  }

  // 7) SqlSessionFactory 객체 생성
  @Bean
  public SqlSessionFactory sqlSessionFactory(
      DataSource dataSource, // DB 커넥션풀
      ApplicationContext appCtx // Spring IoC 컨테이너
      ) throws Exception {

    // Log4J2 기능 활성화시키기
    // => 로그 출력 형식은 .properties 파일이나 .xml 파일로 설정한다.
    LogFactory.useLog4J2Logging();

    SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
    sqlSessionFactoryBean.setDataSource(dataSource);

    // mybatis 설정 파일을 XML 파일로 따로 두지 말고,
    // 다음과 같이 자바 코드로 설정하면 편하다.
    // 
    sqlSessionFactoryBean.setTypeAliasesPackage("com.eomcs.pms.domain");
    sqlSessionFactoryBean.setMapperLocations(
        appCtx.getResources("classpath:com/eomcs/pms/dao/*Dao.xml"));
    return sqlSessionFactoryBean.getObject();
  }


}