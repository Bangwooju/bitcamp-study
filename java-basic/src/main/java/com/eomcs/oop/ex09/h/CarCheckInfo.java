package com.eomcs.oop.ex09.h;

// 이 인터페이스의 용도
// - 자동차 점검할 때 호출하는 메서드 규칙을 정의하였다.
// - 자동차를 점검할 때 체크해야 할 사항을 정의하였다.
// - 따라서 이 규칙에 따라 만든 자동차라면 자동점검을 수행할 수 있다.
public interface CarCheckInfo {
  int getGas();
  int getBrakeOil();
  int getEngineOil();

  // 인터페이스에서 스태틱 메서드는 보통 
  // 그 인터페이스를 구현한 객체를 다루는 일을 한다.
  static boolean validate(CarCheckInfo carInfo) {
    if (carInfo.getBrakeOil() == 0 ||
        carInfo.getEngineOil() == 0 ||
        carInfo.getGas() == 0) {
      return false;
    }
    return true;
  }
}
