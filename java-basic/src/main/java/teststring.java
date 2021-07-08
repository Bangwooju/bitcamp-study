
import java.util.Scanner;

public class teststring {
  public static void main(String[] args) {
    String a = "방우주";
    String b = "방우주";

    Scanner sc = new Scanner(System.in);

    String name = sc.nextLine();
    String name2 = sc.nextLine();


    System.out.println(a == b);
    System.out.println(name == name2);

  };

}
