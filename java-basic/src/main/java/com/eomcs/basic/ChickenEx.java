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

@SuppressWarnings("serial")
class KaribuPanel extends JPanel{
  JLabel message = new JLabel("자바 치킨에 오신것을 환영합니다.");

  KaribuPanel(){
    this.add(message);
  }

  public void setMessage(String msg){
    message.setText("현재 주문한 금액: "+msg+"원 입니다.");
  }

}

class MenuPanel extends JPanel{
  String[] typeItem = {"후라이드","양념","반반"};
  JRadioButton[] typeRB = new JRadioButton[typeItem.length];

  MenuPanel(){
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
    int [] typeCost = {17000, 18000, 175000};
    for (int i=0; i<typeCost.length; i++) {
      if (typeRB[i].isSelected())
        return typeCost[i];
    }
    return 0;       
  }
}

class SidePanel extends JPanel{
  String[] sideItem = {"치킨무","뿌링소스","양념소스","소금"};
  JCheckBox[] sideCB = new JCheckBox[sideItem.length];

  SidePanel() {
    this.setLayout(new GridLayout(0,1));
    for (int i=0; i<sideItem.length; i++) {
      sideCB[i] = new JCheckBox(sideItem[i]);
      this.add(sideCB[i]);
    }
    this.setBorder(BorderFactory.createTitledBorder("사이드"));
  }

  public int calc() {
    int sideSum = 0;
    int [] sideCost = {500,700,500,500};
    for (int i=0; i< sideCB.length; i++) {
      if (sideCB[i].isSelected())
        sideSum += sideCost[i];
    }
    return sideSum;

  }

  public void reset() {
    // TODO Auto-generated method stub
    for (int i=0; i<sideItem.length; i++)
      sideCB[i].setSelected(false);
  }

}

class ExtraPanel extends JPanel{
  String[] sizeItem = {"소떡소떡","치즈볼","콜라500ml"};
  JRadioButton[] sizeRB = new JRadioButton[sizeItem.length];

  ExtraPanel(){
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
    this.setBorder(BorderFactory.createTitledBorder("추가 메뉴"));
  }

  public int calc() {
    // TODO Auto-generated method stub
    int [] sizeCost = {5000, 5000, 2000};
    for (int i=0; i<sizeCost.length; i++) {
      if (sizeRB[i].isSelected())
        return sizeCost[i];
    }
    return 0;
  }
}

class MyActionHandler2 implements ActionListener{
  public ChickenEx g;

  public MyActionHandler2(ChickenEx chickenEx) {
    this.g = chickenEx;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    int subTotal = 0;
    subTotal = (((SidePanel) g.panel[2]).calc()) + (((MenuPanel)g.panel[1]).calc()) + (((ExtraPanel) g.panel[3]).calc());
    if (e.getSource() == g.order) {
      ((KaribuPanel) g.panel[0]).setMessage(""+subTotal);
    }
    if (e.getSource() == g.cancel) {
      ((KaribuPanel) g.panel[0]).setMessage(""+0);
      ((SidePanel) g.panel[2]).reset();
    }
  }

}

public class ChickenEx extends JFrame {

  JPanel [] panel = new JPanel[5];
  JButton order = new JButton("주문");
  JButton cancel = new JButton("취소");

  ChickenEx(){
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setSize(300, 250);

    panel[0] = new KaribuPanel();
    panel[1] = new MenuPanel();
    panel[2] = new SidePanel();
    panel[3] = new ExtraPanel();
    panel[4] = new JPanel();

    panel[4].add(order);
    panel[4].add(cancel);

    MyActionHandler2 mah = new MyActionHandler2(this);
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
    ChickenEx f = new ChickenEx();
  }

}