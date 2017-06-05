package GUI;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import ControlLayer.LoginController;
import ControlLayer.NotificationController;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class Login {

    private JFrame mainFrame;
    private JTextField txtUsername;
    private JMenuItem mbiExit;
    private JButton btnLogin;
    int loginCommand;
    private JLabel lblCredintials;
    /**
     * Launch the application.
     */
    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {

            public void run() {
                try {


                    Login window = new Login();
                    window.mainFrame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public Login() {
        initializeComponents();
        createEvents();
    }



    /**
     * Initialize the contents of the frame.
     */
    private void initializeComponents() {
        mainFrame = new JFrame();
        mainFrame.setTitle("Lanex");
        mainFrame.setBounds(100, 100, 553, 416);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel lblUsername = new JLabel("USERNAME");
        lblUsername.setFont(new Font("Britannic Bold", Font.BOLD, 17));

        txtUsername = new JTextField();

        txtUsername.setFont(new Font("SansSerif", Font.PLAIN, 15));
        txtUsername.setColumns(10);

        btnLogin = new JButton("LOGIN");

        lblCredintials = new JLabel("Username: 123456789");

        lblCredintials.setFont(new Font("SansSerif", Font.PLAIN, 15));

        GroupLayout groupLayout = new GroupLayout(mainFrame.getContentPane());
        groupLayout.setHorizontalGroup(
                groupLayout.createParallelGroup(Alignment.TRAILING)
                        .addGroup(groupLayout.createSequentialGroup()
                                .addGap(56)
                                .addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
                                        .addComponent(btnLogin, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE)
                                        .addGroup(groupLayout.createSequentialGroup()
                                                .addComponent(lblUsername, GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE)
                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                .addComponent(txtUsername, 143, 143, 143)))
                                .addGap(223))
                        .addGroup(groupLayout.createSequentialGroup()
                                .addContainerGap(359, Short.MAX_VALUE)
                                .addComponent(lblCredintials)
                                .addGap(28))
        );
        groupLayout.setVerticalGroup(
                groupLayout.createParallelGroup(Alignment.LEADING)
                        .addGroup(groupLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(lblCredintials)
                                .addGap(60)
                                .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                                        .addGroup(groupLayout.createSequentialGroup()
                                                .addGap(3)
                                                .addComponent(lblUsername, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
                                        .addComponent(txtUsername, GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE))
                                .addGap(108)
                                .addComponent(btnLogin, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                                .addGap(75))
        );
        mainFrame.getContentPane().setLayout(groupLayout);

        JMenuBar menuBar = new JMenuBar();
        mainFrame.setJMenuBar(menuBar);

        JMenu mbFile = new JMenu("File");
        menuBar.add(mbFile);

        mbiExit = new JMenuItem("Exit");
        mbFile.add(mbiExit);
    }

    private void createEvents() {
        mbiExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit the program?");
                if(confirm == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });

        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                LoginController loginCon = new LoginController();

                if (tryParseInt(txtUsername.getText())) {
                    loginCommand = Integer.parseInt(txtUsername.getText());
                }

                if(loginCon.checkLogin(loginCommand)) {
                    JOptionPane.showMessageDialog(null, "Welcome");
                    mainFrame.dispose();
                    MainMenu chooseMenu = new MainMenu();
                    chooseMenu.setVisible(true);
                    NotificationController notificationController = NotificationController.getInstance();
                    Runnable r1 = () -> {
                        try {
                            while (true) {
                                notificationController.substractAvarageConsumption();
                                Thread.sleep(5000 );
                            }
                        } catch (InterruptedException iex) {}
                    };
                    Thread thr1 = new Thread(r1);
                    thr1.start();
                }
                else {
                    JOptionPane.showMessageDialog(null, "Wrong username");
                }
            }
        });
    }
    private boolean tryParseInt(Object object) {
        try {
            Integer.parseInt(object.toString());
            return true;
        } catch (NumberFormatException e){
            return false;
        }
    }
}
