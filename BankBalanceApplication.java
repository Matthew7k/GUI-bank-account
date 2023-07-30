import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BankBalanceApplication extends JFrame {
    private double balance = 0.0;
    private JLabel balanceLabel;

    public BankBalanceApplication() {
        setTitle("Bank Balance Application");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        createGUI();

        setVisible(true);
    }

    private void createGUI() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());

        JLabel balanceInput = new JLabel("Enter Initial Balance: ");
        JTextField balanceField = new JTextField(10);
        JButton submitButton = new JButton("Submit");

        inputPanel.add(balanceInput);
        inputPanel.add(balanceField);
        inputPanel.add(submitButton);

        balanceLabel = new JLabel("Balance: $0.00");

        JPanel balancePanel = new JPanel();
        balancePanel.setLayout(new FlowLayout());
        balancePanel.add(balanceLabel);

        mainPanel.add(inputPanel, BorderLayout.NORTH);
        mainPanel.add(balancePanel, BorderLayout.CENTER);

        // ActionListener for the submit button
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String balanceStr = balanceField.getText();
                try {
                    balance = Double.parseDouble(balanceStr);
                    updateBalanceLabel();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(BankBalanceApplication.this, "Invalid input. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // ActionListener for the deposit button
        JButton depositButton = new JButton("Deposit");
        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String depositStr = JOptionPane.showInputDialog(BankBalanceApplication.this, "Enter amount to deposit:");
                try {
                    double depositAmount = Double.parseDouble(depositStr);
                    balance += depositAmount;
                    updateBalanceLabel();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(BankBalanceApplication.this, "Invalid input. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // ActionListener for the withdraw button
        JButton withdrawButton = new JButton("Withdraw");
        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String withdrawStr = JOptionPane.showInputDialog(BankBalanceApplication.this, "Enter amount to withdraw:");
                try {
                    double withdrawAmount = Double.parseDouble(withdrawStr);
                    if (withdrawAmount <= balance) {
                        balance -= withdrawAmount;
                        updateBalanceLabel();
                    } else {
                        JOptionPane.showMessageDialog(BankBalanceApplication.this, "Insufficient balance.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(BankBalanceApplication.this, "Invalid input. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(depositButton);
        buttonPanel.add(withdrawButton);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    private void updateBalanceLabel() {
        balanceLabel.setText("Balance: $" + String.format("%.2f", balance));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new BankBalanceApplication();
        });
    }
}