package com.eomcs.pms;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collection;
import java.util.HashMap;
import com.eomcs.pms.server.DataProcessor;
import com.eomcs.pms.server.RequestProcessor;
import com.eomcs.pms.table.BoardTable;
import com.eomcs.pms.table.JsonDataTable;
import com.eomcs.pms.table.MemberTable;

public class ServerApp {


  public static void main(String[] args) throws Exception {
    System.out.println("[PMS 서버]");

    System.out.println("서버 실행 중");
    ServerSocket serverSocket= new ServerSocket(8888);

    Socket socket = serverSocket.accept(); //클라이언트가 접속하면 리턴한다.
    System.out.println("클라이언트가 접속했음");

    //RequestProcessor 가 사용할 DataProcessor 맵
    HashMap<String, DataProcessor> dataProcessorMap = new HashMap<>();

    dataProcessorMap.put("board.", new BoardTable());
    dataProcessorMap.put("member.", new MemberTable());

    RequestProcessor requestProcessor = new RequestProcessor(socket, dataProcessorMap);
    requestProcessor.service();
    requestProcessor.close();


    // 데이터를 파일에 저장한다.
    Collection<DataProcessor> dataProcessors = dataProcessorMap.values();
    for(DataProcessor dataProcessor : dataProcessors) {
      if(dataProcessor instanceof JsonDataTable) {
        // 만약 데이터 처리 담당자가 JSON테이블의 자손이라면
        ((JsonDataTable<?>) dataProcessor).save();
      }
    }

    System.out.println("서버 종료!");
    serverSocket.close(); // 더이상 클라이언트의 접속을 수용하지 않는다.


  }

}



