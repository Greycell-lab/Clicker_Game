import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MyFrame extends JFrame implements MouseListener{
    public static Image donutImage = new ImageIcon(MyFrame.class.getResource("/donut.png"))
            .getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
    public static JLabel donutLabel = new JLabel();
    public static JLabel moneyLabel = new JLabel();
    public static ImageIcon donutIcon;
    public static double money = 0;
    public static double multiplier = 1;
    public static double upgradeCost = 10;
    public static String[] array = {"", "K", "M"};
    public static String temp = array[0];
    JButton upgrade = new JButton("Upgrade: " + upgradeCost);
    public MyFrame(){
        setSize(250,250);
        setResizable(false);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        upgrade.setBounds(100, 0, 150, 25);
        upgrade.addActionListener(e ->{
            if(money >= upgradeCost) {
                multiplier += 1;
                money -= upgradeCost;
                upgradeCost *= 2;
                convertMoney();
                updateLabel();
            }
        });
        moneyLabel.setBounds(0, 0, 100, 25);
        donutIcon = new ImageIcon(donutImage);
        updateLabel();
        donutLabel.setBounds(70, 50, 100, 100);
        donutLabel.setIcon(donutIcon);
        donutLabel.addMouseListener(this);
        add(donutLabel);
        add(moneyLabel);
        add(upgrade);
        setVisible(true);
    }
    public void updateLabel(){
        if(temp.equals(""))moneyLabel.setText("Money: " + (int)money);
        else moneyLabel.setText("Money: " + String.format("%.3f", money) + temp);
        upgrade.setText("Upgrade: " + upgradeCost);
    }
    public void convertMoney(){
        if(temp.equals("K") && money <1){
            temp = array[0];
            money *= 1000;
            multiplier *= 1000;
            upgradeCost *= 1000;
        }
        if(temp.equals("M") && money < 1) {
            temp = array[1];
            money *= 1000;
            multiplier *= 1000;
            upgradeCost *= 1000;
        }
        if(money >= 1000 && temp.equals("")) {
            money /= 1000;
            multiplier /= 1000;
            upgradeCost /=1000;
            temp = array[1];
        }
        if(money >= 1000 && temp.equals("K")) {
            money /= 1000;
            multiplier /= 1000;
            upgradeCost /=1000;
            temp = array[2];
        }
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        money += multiplier;
        convertMoney();
        updateLabel();
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

}
