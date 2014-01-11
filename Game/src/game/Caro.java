/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author duong
 */
public class Caro extends JPanel implements ActionListener {

    JPanel pan = new JPanel();
    public JFrame frame = new JFrame();
    public int n = 10, m = 10, num = 0, diem = 0;
    public JButton btn[][] = new JButton[n][m];
    int pos[][] = new int[m][n];

//ham add
    public void add() {
        frame.add(pan);
        pan.setLayout(new GridLayout(n, m));
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                pos[i][j] = num;
                num++;
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                btn[i][j] = new JButton();
                btn[i][j].addActionListener(this);
                pan.add(btn[i][j]);

            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                btn[i][j].setBackground(Color.white);
            }
        }
        frame.setVisible(true);
        pan.setSize(600, 800);
        frame.setSize(600, 600);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
//tim o trong de set text

    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (e.getSource() == btn[i][j] && btn[i][j].getText() != "X" && btn[i][j].getText() != "O") {
                    if (diem % 2 == 0) {
                        btn[i][j].setText("X");
                        btn[i][j].setForeground(Color.RED);
                        diem++;
                        if (win(i, j, btn[i][j].getText())) {
                            btn[i][j].setBackground(Color.red);
                            JOptionPane.showMessageDialog(null, "X win!", "Game Over!", JOptionPane.INFORMATION_MESSAGE);
                            for (int i1 = 0; i1 < n; i1++) {
                                for (int j1 = 0; j1 < m; j1++) {
                                    btn[i1][j1].setText("");
                                    btn[i1][j1].setBackground(Color.white);
                                }
                            }
                        }
                    } else {
                        btn[i][j].setText("O");
                        btn[i][j].setForeground(Color.BLACK);
                        diem++;
                        if (win(i, j, btn[i][j].getText())) {
                            btn[i][j].setBackground(Color.green);
                            JOptionPane.showMessageDialog(null, "O win!", "Game Over!", JOptionPane.INFORMATION_MESSAGE);
                            for (int i1 = 0; i1 < n; i1++) {
                                for (int j1 = 0; j1 < m; j1++) {
                                    btn[i1][j1].setText("");
                                    btn[i1][j1].setBackground(Color.white);
                                }
                            }

                        }

                    }
                }

            }
        }
    }
//kiem tra thang 

    public boolean win(int x, int y, String name) {
        int k, j;
        int d = 0;
// kt chieu doc
        for (k = -4; k <= 4; k++) {
            if (x + k >= 0 && x + k < n) {

                if (btn[x + k][y].getText() == name) {
                    d++;

                } else if (d < 5) {
                    d = 0;
                }
            }
        }
        if (d >= 5) {
            return true;
        } else {
            d = 0;
        }

//xet ngang
        for (k = -5; k <= 5; k++) {
            if (y + k >= 0 && y + k < n) {
                if (btn[x][y + k].getText() == name) {
                    d++;
                } else if (d < 5) {
                    d = 0;
                }
            }
        }
        if (d >= 5) {
            return true;
        } else {
            d = 0;
        }

//cheo
        for (k = -4, j = 4; k <= 4 && j >= -4; k++, j--) {
            if (y + k >= 0 && y + k < n && x + j >= 0 && x + j < m) {
                if (btn[x + j][y + k].getText() == name) {
                    d++;
                } else if (d < 5) {
                    d = 0;
                }
            }
        }
        if (d >= 5) {
            return true;
        } else {
            d = 0;
        }
        for (k = -4; k <= 4; k++) {
            if (y + k >= 0 && y + k < n && x + k >= 0 && x + k < m) {

                if (btn[x + k][y + k].getText() == name) {
                    d++;
                } else if (d < 5) {
                    d = 0;
                }

            }
        }
        if (d >= 5) {
            return true;
        }

        return false;
    }

    public static void main(String[] args) {
        Caro c = new Caro();
        c.add();
    }
}
