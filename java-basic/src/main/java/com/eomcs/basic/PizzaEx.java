package com.eomcs.basic;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

class WelcomePanel extends JPanel{
  JLabel message = new JLabel("자바 피자에 오신것을 환영합니다.");

  WelcomePanel(){
    this.add(message);
  }

  public void setMessage(String msg){
    message.setText("현재 주문한 금액: "+msg+"원 입니다.");
  }

}

class TypePanel extends JPanel{
  String[] typeItem = {"콤보","포테이토","불고기"};
  JRadioButton[] typeRB = new JRadioButton[typeItem.length];

  TypePanel(){
    this.setLayout(new GridLayout(0,1));
    ButtonGroup g = new ButtonGroup();
    for (int i=0; i<typeItem.length; i++) {
      //생성, 붙이고, 그룹...., 이벤트 리스너...
      typeRB[i] = new JRadioButton(typeItem[i]);
      g.add(typeRB[i]);
      this.add(typeRB[i]);
      //typeRB[i].addItemListener(this);
    }
    typeRB[0].setSelected(true);
    this.setBorder(BorderFactory.createTitledBorder("종류"));
  }
  public int calc() {
    int [] typeCost = {20000, 17000, 15000};
    for (int i=0; i<typeCost.length; i++) {
      if (typeRB[i].isSelected())
        return typeCost[i];
    }
    return 0;       
  }
}

class ToppingPanel extends JPanel{
  String[] toppingItem = {"피망","치즈","페페로니","베이컨"};
  JCheckBox[] toppingCB = new JCheckBox[toppingItem.length];

  ToppingPanel() {
    this.setLayout(new GridLayout(0,1));
    for (int i=0; i<toppingItem.length; i++) {
      toppingCB[i] = new JCheckBox(toppingItem[i]);
      this.add(toppingCB[i]);
    }
    this.setBorder(BorderFactory.createTitledBorder("추가 토핑"));
  }

  public int calc() {
    int toppingSum = 0;
    int [] toppingCost = {100,200,300,400};
    for (int i=0; i< toppingCB.length; i++) {
      if (toppingCB[i].isSelected())
        toppingSum += toppingCost[i];
    }
    return toppingSum;

  }

  public void reset() {
    // TODO Auto-generated method stub
    for (int i=0; i<toppingItem.length; i++)
      toppingCB[i].setSelected(false);
  }

}

class SizePanel extends JPanel{
  String[] sizeItem = {"Small","Medium","Large"};
  JRadioButton[] sizeRB = new JRadioButton[sizeItem.length];

  SizePanel(){
    this.setLayout(new GridLayout(0,1));
    ButtonGroup g = new ButtonGroup();
    for (int i=0; i<sizeItem.length; i++) {
      //생성, 붙이고, 그룹...., 이벤트 리스너...
      sizeRB[i] = new JRadioButton(sizeItem[i]);
      g.add(sizeRB[i]);
      this.add(sizeRB[i]);
      //typeRB[i].addItemListener(this);
    }
    sizeRB[0].setSelected(true);
    this.setBorder(BorderFactory.createTitledBorder("크기"));
  }

  public int calc() {
    // TODO Auto-generated method stub
    int [] sizeCost = {0, 2000, 4000};
    for (int i=0; i<sizeCost.length; i++) {
      if (sizeRB[i].isSelected())
        return sizeCost[i];
    }
    return 0;
  }
}

class MyActionHandler implements ActionListener{
  public PizzaEx g;

  public MyActionHandler(PizzaEx pizzaEx) {
    this.g = pizzaEx;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    int subTotal = 0;
    subTotal = (((ToppingPanel) g.panel[2]).calc()) + (((TypePanel)g.panel[1]).calc()) + (((SizePanel) g.panel[3]).calc());
    if (e.getSource() == g.order) {
      ((WelcomePanel) g.panel[0]).setMessage(""+subTotal);
    }
    if (e.getSource() == g.cancel) {
      ((WelcomePanel) g.panel[0]).setMessage(""+0);
      ((ToppingPanel) g.panel[2]).reset();
    }
  }

}

class OurActionHandler{

}
public class PizzaEx extends JFrame {

  JPanel [] panel = new JPanel[5];
  JButton order = new JButton("주문");
  JButton cancel = new JButton("취소");

  PizzaEx(){
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setSize(300, 250);

    panel[0] = new WelcomePanel();
    panel[1] = new TypePanel();
    panel[2] = new ToppingPanel();
    panel[3] = new SizePanel();
    panel[4] = new JPanel();

    panel[4].add(order);
    panel[4].add(cancel);

    MyActionHandler mah = new MyActionHandler(this);
    order.addActionListener(mah);
    cancel.addActionListener(mah);

    this.add(panel[0], BorderLayout.NORTH);
    this.add(panel[1], BorderLayout.WEST);
    this.add(panel[2], BorderLayout.CENTER);
    this.add(panel[3], BorderLayout.EAST);
    this.add(panel[4], BorderLayout.SOUTH);


    this.setVisible(true);

  }

  public static void main(String[] args) {
    PizzaEx f = new PizzaEx();
  }

}