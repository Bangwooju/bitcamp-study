package com.eomcs.pms.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

// 프론트 컨트롤러는 객체를 보관할 수 있는 bean container를 갖고 있다.  
// 빈컨테이너에는 페이지 컨트롤러 객체를 보관한다.
// 또한 페이지 컨트롤러가 의존하는 객체도 보관한다.
// 
// 다음은 이런 빈컨테이너의 행동을 설정하는 클래스이다.
//

//1) 빈 컨테이너가 자동으로 객체를 생성해야 하는 패키지를 등록한다.
@ComponentScan("com.eomcs.pms.web")

//2) Spring WebMVC 관련 객체를 찾아서 등록하는 기능을 활성화시킨다.
@EnableWebMvc

public class AppConfig {

  @Bean
  public MultipartResolver multipartResolver() {
    // 스프링 WebMVC에서 multipart/form-data 콘텐트를 요청 핸들러의 파라미터로 받고 싶다면,
    // MultipartResolver 구현체를 스프링 빈 컨테이너에 등록해야 한다.
    // 그래야 request handler는 프론트 컨트롤러로부터 MultipartFile/Part 객체를 받을 수 있다.
    // MultipartResolver 객체를 등록하지 않는다면,
    // HttpServletRequest를 이용하여 직접 업로드 파일의 콘텐트를 꺼내야 한다.
    // 즉 편리하게 파라미터로 받을 수 없다.
    //
    return new StandardServletMultipartResolver(); // Servlet 3.0 API의 멀티파트 처리 기능을 사용할 경우
  }

  @Bean
  public ViewResolver viewResolver() {
    // 기존의 기본 ViewResolver를 이 메서드가 리턴하는 객체로 대체한다.
    // - 요청핸들러가 리턴한 jsp 이름을 가지고 앞뒤로 경로를 붙여서 찾는다.
    InternalResourceViewResolver vr = new InternalResourceViewResolver();
    vr.setViewClass(JstlView.class); // JSTL을 처리해줄 클래스 지정
    vr.setPrefix("/WEB-INF/jsp/");
    vr.setSuffix(".jsp");
    return vr;
  }


}