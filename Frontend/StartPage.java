package Frontend;
import java.awt.event.*;

import javax.swing.*;
import java.awt.*;

public class StartPage implements ActionListener {
    JFrame frame = new JFrame();
    JButton easy;
    JButton medium;
    JButton hard;

    public StartPage()
    {
        frame.setSize(750, 500);
        frame.setTitle("Welcome!");

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel label = new JLabel();
        ImageIcon image = new ImageIcon("Frontend/bostoncollege.png");
        label.setIcon(image);
        label.setText("Welcome to the Boston College Hedge Fund Simulator!");
        label.setHorizontalTextPosition(JLabel.CENTER);
        label.setVerticalTextPosition(JLabel.TOP);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        gbc.gridheight = 1;
        mainPanel.add(label, gbc);

        JTextArea label2 = new JTextArea("In this simulator, you will be the hedge fund manager for Boston College. As the hedge fund manager, you will be able to purchase and sell stocks on BC “companies” such as O’Neill Library or Stokes Hall. You win once you have earned $10,000 through commissions from stock sales. Select from the three levels of difficulties below:");
        label2.setLineWrap(true);
        label2.setWrapStyleWord(true);
        label2.setPreferredSize(new Dimension(500, 100));

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 3;
        gbc.gridheight = 1;
        mainPanel.add(label2, gbc);

        JPanel selections = new JPanel();
        selections.setLayout(new GridBagLayout());

        JTextArea easyText = new JTextArea(" Easy: \n Commission - 10% \n Funds - $100,000");
        easyText.setPreferredSize(new Dimension(150, 100));
        easyText.setLineWrap(true);
        easyText.setWrapStyleWord(true);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        selections.add(easyText, gbc);

        easy = new JButton();
        easy.setText("Select Easy");
        easy.addActionListener(this);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        selections.add(easy, gbc);

        JTextArea mediumText = new JTextArea(" Medium: \n Commission - 5% \n Funds - $75,000");
        mediumText.setPreferredSize(new Dimension(150, 100));
        mediumText.setLineWrap(true);
        mediumText.setWrapStyleWord(true);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        selections.add(mediumText, gbc);

        medium = new JButton();
        medium.setText("Select Medium");
        medium.addActionListener(this);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        selections.add(medium, gbc);

        JTextArea hardText = new JTextArea(" Hard: \n Commission - 2% \n Funds - $50,000");
        hardText.setPreferredSize(new Dimension(150, 100));
        hardText.setLineWrap(true);
        hardText.setWrapStyleWord(true);

        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        selections.add(hardText, gbc);

        hard = new JButton();
        hard.setText("Select Hard");
        hard.addActionListener(this);

        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        selections.add(hard, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 3;
        gbc.gridheight = 1;
        mainPanel.add(selections, gbc);

        frame.add(mainPanel);

        frame.setResizable(false);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        DifficultyFacade df = new DifficultyFacade();
        if (e.getSource() == easy)
        {
            frame.dispose();
            df.easyInitializeAccount();
            new LaunchPage();
        }
        else if (e.getSource() == medium)
        {
            frame.dispose();
            df.mediumInitializeAccount();
            new LaunchPage();
        }
        else if (e.getSource() == hard)
        {
            frame.dispose();
            df.hardInitializeAccount();
            new LaunchPage();
        }        
    }

}
