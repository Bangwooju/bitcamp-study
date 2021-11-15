package com.eomcs.pms.web;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Retention(RetentionPolicy.RUNTIME)
public @interface Controller {

  String value() defalut "";
}
