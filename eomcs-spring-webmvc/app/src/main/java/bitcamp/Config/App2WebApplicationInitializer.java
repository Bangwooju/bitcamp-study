package bitcamp.Config;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

// 서블릿컨테이너 ===> ServletContainerInitializer.onStartup() 호출 :
//                      = SpringServletContainerInitializer 구현체
//                          ==> WebApplicationInitializer.onStartup() 호출 : 
//                              = MyWebApplicationInitializer 구현체
public class App2WebApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

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
    return null;
  }

  protected Class<?>[] getServletConfigClasses() {
    return new Class<?>[] {App2Config.class};
  }

  @Override
  protected String[] getServletMappings() {
    // DispatcherServlet에 대해 URL 매핑 정보를 리턴한다.
    return new String[] {"/app2/*"};
  }


  @Override
  protected String getServletName() {
    // TODO Auto-generated method stub
    return "app2";
  }

}