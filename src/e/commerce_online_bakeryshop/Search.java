/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e.commerce_online_bakeryshop;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static java.lang.Math.ceil;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

/**
 *
 * @author Muhammad Ahmad Khan
 */
public class Search extends JFrame {

    ArrayList<Products> list = Products.readAllData();
    JTextField[] displaytext = new JTextField[list.size()];
    JPanel[] p = new JPanel[12];
    JButton[] b = new JButton[3];
    JTextField t1, t2;
    JLabel l1, l2;
    //JScrollPane sc;
    //ImageIcon image = new ImageIcon("C:\\Users\\Muhammad Ahmad Khan\\Documents\\NetBeansProjects\\E-Commerce_Online_BakeryShop\\src\\e\\commerce_online_bakeryshop\\image.jpg");

    public Search(String s) throws HeadlessException {

        super("Search");
        setLayout(new BorderLayout());
        //setLocation(180, 50);

        setSize(1366, 768);
        setVisible(true);
        action act = new action();

        for (int i = 0; i < p.length; i++) {
            p[i] = new JPanel();
        }
        for (int i = 0; i < b.length; i++) {
            b[i] = new JButton();
            b[i].addActionListener(act);
        }

        p[0].setLayout(new BorderLayout());
        //p[0].setBackground(java.awt.Color.pink);
        add(p[0], BorderLayout.CENTER);

        p[1].setLayout(new FlowLayout());
        p[2].setLayout(new FlowLayout());
        p[3].setLayout(new FlowLayout());
        b[0].setText("Add to Cart");
        b[1].setText("Add to Wishlist");
        b[2].setText("Back");
        t1 = new JTextField(20);
        t2 = new JTextField(20);

        p[2].add(t1);
        p[2].add(b[0]);
        p[3].add(t2);
        p[3].add(b[1]);
        p[3].add(b[2]);

        p[1].add(p[2]);
        p[1].add(p[3]);
        p[0].add(p[1], BorderLayout.SOUTH);

        p[4].setLayout(new BorderLayout());
        p[0].add(p[4], BorderLayout.NORTH);

        p[6].setLayout(new FlowLayout());

        p[4].add(p[6], BorderLayout.CENTER);

        l1 = new JLabel("Results");
        l1.setForeground(java.awt.Color.red);

        Font f = new Font("Serif", Font.BOLD, 70);
        Font f1 = new Font("Serif", Font.BOLD, 20);

        //l1.setBackground(java.awt.Color.RED);
        l1.setFont(f);
        p[6].add(l1);

        p[7].setLayout(new BorderLayout());
//        Image img = image.getImage();
//        Image temp = img. getScaledInstance(1366, 768, Image.SCALE_SMOOTH);
//        image = new ImageIcon(temp);
//        l2 = new JLabel("", image, JLabel.CENTER);
//        l2.setBounds(0, 0, 1366, 768);
//        p[7].add(l2);

        p[8].setLayout(new GridLayout(50, 1));
        JScrollPane sc = new JScrollPane(p[8], JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        for (int i = 0; i < list.size(); i++) {
            if (s.startsWith(list.get(i).getCategory())
                    || s.equalsIgnoreCase(list.get(i).getCategory()) || s.equalsIgnoreCase(list.get(i).getId())
                    || s.equalsIgnoreCase(list.get(i).getName()) || s.startsWith(list.get(i).getName()) || s.startsWith(list.get(i).getId())
                    || s.endsWith(list.get(i).getId()) || s.startsWith(list.get(i).getName()) || s.startsWith(list.get(i).getCategory())
                    || s.contains(list.get(i).getId()) || s.contains(list.get(i).getName()) || s.contains(list.get(i).getCategory())) {
                displaytext[i] = new JTextField();
                displaytext[i].setEditable(false);
                displaytext[i].setBackground(Color.LIGHT_GRAY);
                displaytext[i].setFont(f1);
                displaytext[i].setText("Name : " + list.get(i).getName() + " \tID : " + list.get(i).getId() + " \tQuantity : " + list.get(i).getQuantity() + " \t Price : " + list.get(i).getPrice());
                p[8].add(displaytext[i]);
            }
        }
        p[7].add(sc);

        p[0].add(p[7], BorderLayout.CENTER);

    }

    class action implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            if (ae.getActionCommand().equalsIgnoreCase("Add to Cart")) {

                if (operations.islistContainID(t1.getText(), Products.readAllData())) {
                    try {

                        if (operations.isclistContainID(t1.getText(), Cartj.readAllData())) {
                            JOptionPane.showMessageDialog(new JFrame(), "Item already in cart");
                            t1.setText("");

                        } else {
                            ArrayList<Products> p5 = Products.readAllData();
                            int con = 0;
                            for (Products p1 : p5) {
                                if (t1.getText().equals(p1.getId())) {
                                    if (p1.getQuantity() <= 0) {

                                        JOptionPane.showMessageDialog(new JFrame(), "Item is Out of Stock");
                                        break;

                                    }

                                    if (p1.getClass().toString().contains("Pizza")) {
                                        Pizza pz = (Pizza) p1;
                                        String size = JOptionPane.showInputDialog(new JFrame(), "Enter Pizza Size\nS | M | L");
                                        try {
                                            pz.setSize(size);
                                        } catch (InvalidPizzaSizeException ex) {
                                            JOptionPane.showMessageDialog(new JFrame(), ex.getMessage());
                                            break;
                                        }

                                    }
                                    if (p1.getClass().toString().contains("NormalCakes")) {
                                        NormalCakes nc = (NormalCakes) p1;
                                        String pou = JOptionPane.showInputDialog(new JFrame(), "Enter Weight in Pounds");
                                        int pounds = Integer.parseInt(pou);
                                        try {
                                            nc.setWeight(pounds);
                                        } catch (InvalidCakeSizeException ex) {
                                            JOptionPane.showMessageDialog(new JFrame(), ex.getMessage());
                                            break;
                                        }

                                    }
                                    String q = JOptionPane.showInputDialog(new JFrame(), "Enter Quantity");
                                    int quantity = Integer.parseInt(q);
                                    operations.isQuantityAvailable(quantity, t1.getText(), p5);
                                    Cartj.writeObjectToFile(new Cartj(p1, quantity));
                                    operations.updateQuantity(t1.getText(), quantity, p5);
                                    JOptionPane.showMessageDialog(new JFrame(), p1.name + " Added in cart");

                                    break;
                                }

                            }
                        }
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(new JFrame(), e.getMessage());
                        t1.setText("");

                    }
                } else {
                    JOptionPane.showMessageDialog(new JFrame(), "Invalid Id");
                    t1.setText("");
                }
            }
            if (ae.getActionCommand().equalsIgnoreCase("Add to Wishlist")) {

                if (operations.islistContainID(t2.getText(), Products.readAllData())) {
                    try {

                        if (operations.isclistContainID(t2.getText(), Cartj.readAllData())) {
                            JOptionPane.showMessageDialog(new JFrame(), "Item already in Wishlist");
                            t2.setText("");

                        } else {
                            ArrayList<Products> p5 = Products.readAllData();
                            int con = 0;
                            for (Products p1 : p5) {
                                if (t2.getText().equals(p1.getId())) {
                                    if (p1.getClass().toString().contains("Pizza")) {
                                        Pizza pz = (Pizza) p1;
                                        String size = JOptionPane.showInputDialog(new JFrame(), "Enter Pizza Size\nS | M | L");
                                        try {
                                            pz.setSize(size);
                                        } catch (InvalidPizzaSizeException ex) {
                                            JOptionPane.showMessageDialog(new JFrame(), ex.getMessage());
                                            break;
                                        }

                                    }
                                    if (p1.getClass().toString().contains("NormalCakes")) {
                                        NormalCakes nc = (NormalCakes) p1;
                                        String pou = JOptionPane.showInputDialog(new JFrame(), "Enter Weight in Pounds");
                                        int pounds = Integer.parseInt(pou);
                                        try {
                                            nc.setWeight(pounds);
                                        } catch (InvalidCakeSizeException ex) {
                                            JOptionPane.showMessageDialog(new JFrame(), ex.getMessage());
                                            break;
                                        }

                                    }

                                    operations.writeObjectToWishlistFile(p1);

                                    JOptionPane.showMessageDialog(new JFrame(), p1.name + " Added in Wishlist");

                                    break;
                                } else {
                                    con++;
                                }

                            }
                        }
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(new JFrame(), e.getMessage());
                        t2.setText("");

                    }
                } else {
                    JOptionPane.showMessageDialog(new JFrame(), "Invalid Id");
                    t2.setText("");
                }
            }
            if (ae.getActionCommand().equalsIgnoreCase("Back")) {
                dispose();
                E_Commerce_Main_Runner e = new E_Commerce_Main_Runner();
                e.setResizable(false);
                e.setResizable(true);

            }
        }
    }

    public static void main(String[] args) {
        Search e = new Search("pizza");
        e.setResizable(false);
        e.setResizable(true);
    }
}
