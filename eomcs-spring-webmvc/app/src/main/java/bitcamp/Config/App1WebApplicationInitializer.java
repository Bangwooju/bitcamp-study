package bitcamp.Config;

import java.io.File;
import javax.servlet.MultipartConfigElement;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

// 서블릿컨테이너 ===> ServletContainerInitializer.onStartup() 호출 :
//                      = SpringServletContainerInitializer 구현체
//                          ==> WebApplicationInitializer.onStartup() 호출 : 
//                              = MyWebApplicationInitializer 구현체
public class App1WebApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

  // AbstractDispatcherServletInitializer 클래스에서 이미 DispatcherServlet 객체를 생성하여 등록했다.
  // 따라서 이 클래스를 상속 받는 서브 클래스에서 해야 할 일은
  // 1) ContextLoaderListener가 사용할 IoC 컨테이너를 설정한다.
  // -> createRootApplicationContext() 메서드 오버라이딩.
  // 2) DispatcherServlet 이 사용할 IoC 컨테이너 설정한다.
  // -> createServletApplicationContext() 메서드 오버라이딩
  // 3) DispatcherServlet에 적용할 URL 매핑을 설정한다.
  //    getServletMappings() 오버라이딩

  // 다음 메서드들은 수퍼 클래스에서 상속 받은 onStartup()에서 호출한다.
  // 즉 onStartup()은 DispatcherServlet을 준비할 때 다음 메서드들을 참조한다.


  @Override
  protected WebApplicationContext createRootApplicationContext() {
    // ContextLoaderListener가 사용할 IoC 컨테이너를 설정하고 싶지 않다면 그냥 null을 리턴한다.

    return null;
  }

  // 다음 메서드는 수퍼 클래스에서 이미 설정했다.
  // 따라서 서브 클래스에서 오버라이딩 할 필요가 없다.
  //  @Override
  //  protected WebApplicationContext createServletApplicationContext() {
  //    AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
  //    context.register(AppConfig.class);
  //
  //    return context;
  //  }
  // 대신 AnnotationConfigWebApplicationContext가 사용할 JavaConfig zmffotmaks dkffuwnjfk!

  protected Class<?>[] getRootConfigClasses() {
    return new Class<?>[] {RootConfig.class};
  }


  protected Class<?>[] getServletConfigClasses() {
    return new Class<?>[] {AppConfig.class};
  }

  @Override
  protected String[] getServletMappings() {
    // DispatcherServlet에 대해 URL 매핑 정보를 리턴한다.
    return new String[] {"/app1/*"};
  }


  @Override
  protected String getServletName() {
    // TODO Auto-generated method stub
    return "app1";
  }


  @Override
  protected void customizeRegistration(
      javax.servlet.ServletRegistration.Dynamic registration) {
    // Servlet 3.0 API의 파일 업로드를 사용하려면 다음과 같이
    // DispatcherServlet에 설정을 추가해야 한다.
    // => 즉 멀티파트 데이터를 Part 객체로 받을 때는
    //    이 설정을 추가해야 한다.
    // => 단 이 설정을 추가하면 Spring WebMVC의 MultipartResolver가 작동되지 않는다.
    //
    // 만약 Spring의 방식으로 파일 업로드를 처리하고 싶다면,
    // AppConfig.java의 MultipartResolver를 추가해야 한다.
    // => 즉 멀티파트 데이터를 MultipartFile 객체로 받으려면
    //    AppConfig에 별도로 MultipartResolver를 추가해야 한다.
    //
    // DispatcherServlet 이 multipart/form-data 으로 전송된 데이터를 처리하려면
    // 해당 콤포넌트를 등록해야 한다.
    registration.setMultipartConfig(
        new MultipartConfigElement(
            new File(System.getProperty("java.io.tmpdir")).getAbsolutePath(),  // 업로드 한 파일을 임시 보관할 위치
            10000000, // 최대 업로드할 수 있는 파일들의 총 크기
            15000000, // 요청 전체 데이터의 크기
            2000000 // 업로드 되고 있는 파일을 메모리에 임시 임시 보관하는 크기
            ));
  }



}