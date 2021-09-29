package com.eomcs.pms.server;

public interface DataProcessor {

  void execute(Request request, Response response) throws Exception;


}
