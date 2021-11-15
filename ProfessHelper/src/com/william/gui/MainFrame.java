package com.william.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

/**
 * 此类是个GUI类，用于产生ProfessHelper的主界面。
 *
 * @author WilliamLi
 * @version 1.0
 * @date 2021/11/13 23:26
 * @see javax.swing.JFrame 继承自JFrame。
 */
public class MainFrame extends JFrame {

    /**
     * 窗口主面板。
     */
    private final JPanel body;

    /**
     * 表白文字标签。
     */
    private final JLabel textLabel;

    /**
     * 装按钮的面板
     */
    private final JPanel buttonPanel;

    /**
     * 确认按钮。
     */
    private final JButton confirmButton;

    /**
     * 不同意按钮。
     */
    private final JButton cancelButton;

    /**
     * 窗口宽度。
     */
    private final int wide = 600;

    /**
     * 窗口高度。
     */
    private final int height = 400;

    /**
     * 取消按钮左上角x坐标。
     */
    private int cancelButtonX1 = this.wide - 250;

    /**
     * 取消按钮右下角x坐标。
     */
    private int cancelButtonX2 = cancelButtonX1 + 100;

    /**
     * 取消按钮左上角y坐标。
     */
    private int cancelButtonY1 = 200;

    /**
     * 取消按钮右下角y坐标。
     */
    private int cancelButtonY2 = cancelButtonY1 + 60;

    /**
     * 无参数构造方法。
     */
    public MainFrame() {
        super("我喜欢你");
        this.body = new JPanel();
        this.textLabel = new JLabel("山有木兮木有枝，心悦君兮君不知。");
        this.buttonPanel = new JPanel();
        this.confirmButton = new JButton("知");
        this.cancelButton = new JButton("否");
        this.setAction();
    }

    /**
     * 此方法用于启动整个窗口。
     */
    public void process() {
        this.setFrame();
        this.setLayout();
        this.setFont();
        this.setColor();
    }

    /**
     * 此方法用于设置窗口信息。<br>
     * 此方法中通过获取默认ToolKit获取显示屏大小。
     *
     * @see java.awt.Toolkit
     */
    private void setFrame() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        this.setBounds((dimension.width - this.wide) >> 1, (dimension.height - this.height) >> 1, wide, height);
        this.setContentPane(body);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setResizable(false);
        this.setFocusable(true);
        this.body.setFocusable(true);
        this.buttonPanel.setFocusable(true);
        this.setVisible(true);
    }

    /**
     * 此方法用于设置窗口布局。<br>
     * 其中主界面使用流式布局，按钮面板中的按钮无布局管理器。
     */
    private void setLayout() {
        FlowLayout bodyLayout = new FlowLayout();
        this.setLayout(null);
        this.body.setLayout(bodyLayout);
        this.buttonPanel.setLayout(null);
        this.buttonPanel.setPreferredSize(new Dimension(this.wide - 20, this.height - 85));
        this.confirmButton.setBounds(150, 200, 100, 60);
        this.cancelButton.setBounds(cancelButtonX1, 200, 100, 60);
        this.buttonPanel.add(confirmButton);
        this.buttonPanel.add(cancelButton);
        this.body.add(textLabel, BorderLayout.NORTH);
        this.body.add(buttonPanel, BorderLayout.CENTER);
    }

    /**
     * 此方法用于设置界面中所有字体。
     */
    public void setFont() {
        Font standardFont = new Font("宋体", Font.BOLD, 28);
        Font buttonFont = new Font("宋体", Font.PLAIN, 21);
        this.textLabel.setFont(standardFont);
        this.confirmButton.setFont(buttonFont);
        this.cancelButton.setFont(buttonFont);
    }

    /**
     * 此方法用于设置界面背景颜色。
     */
    public void setColor() {
        this.buttonPanel.setBackground(Color.WHITE);
    }

    /**
     * 此方法用于设置界面中所有按钮事件。<br>
     * 此方法同时还包含了键盘事件监听以及鼠标位置监听。
     */
    public void setAction() {

        //窗口关闭按钮事件。
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Toolkit toolkit = Toolkit.getDefaultToolkit();
                Random random = new Random();
                int randomX = random.nextInt(toolkit.getScreenSize().width - getThis().wide);
                int randomY = random.nextInt(toolkit.getScreenSize().height - getThis().height);
                getThis().setLocation(randomX, randomY);
            }
        });

        //程序退出密钥。
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.isAltDown() && e.isShiftDown() && (e.getKeyChar() == KeyEvent.VK_L)) {
                    System.exit(0);
                }
            }
        });

        //程序退出密钥。
        body.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.isAltDown() && e.isShiftDown() && (e.getKeyChar() == KeyEvent.VK_L)) {
                    System.exit(0);
                }
            }
        });

        //程序退出密钥。
        buttonPanel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.isAltDown() && e.isShiftDown() && (e.getKeyChar() == KeyEvent.VK_L)) {
                    System.exit(0);
                }
            }
        });

        //鼠标位置监听。
        buttonPanel.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {

            }

            @Override
            public void mouseMoved(MouseEvent e) {
                Random random = new Random();
                int x = e.getX();
                int y = e.getY();
                if ((x > cancelButtonX1 - 20) && (x < cancelButtonX2 + 20) && (y > cancelButtonY1 - 20) && (y < cancelButtonY2 + 20)) {
                    int newX = random.nextInt(buttonPanel.getPreferredSize().width - 100);
                    int newY = random.nextInt(buttonPanel.getPreferredSize().height - 60);
                    cancelButton.setLocation(newX, newY);
                    cancelButtonX1 = newX;
                    cancelButtonX2 = newX + 100;
                    cancelButtonY1 = newY;
                    cancelButtonY2 = newY + 60;
                }
            }
        });

        //确定按钮事件。
        confirmButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int result = JOptionPane.showConfirmDialog(null, "确认接受这份表白吗？", "确认",
                        JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
                if (result == JOptionPane.YES_OPTION) {
                    System.exit(0);
                } else if (result == JOptionPane.NO_OPTION) {
                    JOptionPane.showMessageDialog(null, "您无法拒绝这份表白", "想什么呢",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        //取消按钮事件。
        cancelButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "您无法拒绝这份表白", "想什么呢",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }

    /**
     * 此方法用于在内部类中获取外部类对象。
     *
     * @return 外部类对象。
     */
    private MainFrame getThis() {
        return this;
    }

}
