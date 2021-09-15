package com.eomcs.csv;

public interface CsvValue {

  // 객체 스스로 CSV 형식의 문자열을 리턴하는 메서드를 갖고 있어야 한다.
  String toCsvString();

  void loadCsv(String csv);

}
