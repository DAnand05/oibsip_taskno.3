package OasisInfobyte;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.awt.event.*;
import java.util.*;

public class ATM extends JFrame{
    private JLabel balLbl;
    private JTextArea trnTa;
    private JButton wdBtn, dpstBtn, tfBtn, quitBtn;

    private double bal;
    private List<String> trn;

    public ATM(){
        setTitle("ATM");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        
        balLbl = new JLabel("Balance: ₹0.00");
        trnTa = new JTextArea(10,30);
        trnTa.setEditable(false);
        JScrollPane sp = new JScrollPane(trnTa);
        
        wdBtn = new JButton("Withdraw");
        dpstBtn = new JButton("Deposit");
        tfBtn = new JButton("Transfer");
        quitBtn = new JButton("Quit");

        wdBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                String in = JOptionPane.showInputDialog(ATM.this, "Enter the amount to withdraw: ");
                if(in != null && !in.isEmpty()){
                    double amt = Double.parseDouble(in);
                    withdraw(amt);
                }
            }
        });

        dpstBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                String in = JOptionPane.showInputDialog(ATM.this, "Enter the amount to deposit : ");
                if(in != null && !in.isEmpty()){
                    double amt = Double.parseDouble(in);
                    deposit(amt);
                }
            }
        });

        tfBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                String in = JOptionPane.showInputDialog(ATM.this, "Enter the amount to transfer : ");
                if(in != null && !in.isEmpty()){
                    double amt = Double.parseDouble(in);
                    tf(amt);
                }
            }
        });

        quitBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                JOptionPane.showMessageDialog(ATM.this, "Thank you for using this ATM !!!");
                dispose();
            }
        });

        balLbl.setBounds(10,10,200,25);
        sp.setBounds(10,45,250,300);
        dpstBtn.setBounds(10,355,100,30);
        wdBtn.setBounds(155,355,100,30);
        tfBtn.setBounds(10,395,100,30);
        quitBtn.setBounds(155,395,100,30);

        add(balLbl);
        add(sp);
        add(dpstBtn);
        add(wdBtn);
        add(tfBtn);
        add(quitBtn);

        setSize(300,480);
        setVisible(true);

        initATM();
    }

    private void initATM(){
        bal = 0.0;
        trn = new ArrayList<>();
        updateBal();
        updateTrn();
    }

    private void updateBal(){
        balLbl.setText("Balance: ₹" + String.format("%.2f",bal));
    }

    private void updateTrn(){
        trnTa.setText("");
        for(String trns : trn){
            trnTa.append(trns + "\n");
        }
    }

    private void withdraw(double amt){
        if(amt > 0 && amt <= bal){
            bal -= amt;
            trn.add("Withdraw: ₹" + String.format("%.2f", amt));
            updateBal();
            updateTrn();
        }else{
            JOptionPane.showMessageDialog(this, "Invalid amount or insufficient balance!!!");
        }
    }

    private void deposit(double amt){
        if(amt > 0){
            bal += amt;
            trn.add("Deposit: ₹" + String.format("%.2f",amt));
            updateBal();
            updateTrn();
        } else {
            JOptionPane.showMessageDialog(this, "Invalid amount!!!");
        }
    }

    private void tf(double amt){
        if (amt > 0 && amt <= bal){
            String rcp = JOptionPane.showInputDialog(this, "Enter recipient's name : ");
            if(rcp != null && !rcp.isEmpty()){
                bal -= amt;
                trn.add("Transfer: ₹" + String.format("%.2f", amt)+ " to " + rcp);
                updateBal();
                updateTrn();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Invalid amount or insufficient balance!!!");
        }
    }

    public static void main(String[] main){
        new ATM();
    }

}
