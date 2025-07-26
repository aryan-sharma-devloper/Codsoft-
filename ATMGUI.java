import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class ATMGUI extends JFrame implements ActionListener {
    private int amount;
    private int pin;
    private int accid;
    private String accname;
    private int otp;

    private JTextField inputField;
    private JTextArea displayArea;
    private JButton printButton, pinButton, withdrawButton, depositButton, balanceButton, exitButton, otpButton;
    private int step = 0;

    public ATMGUI(int amount, int pin, int accid, String accname) {
        this.amount = amount;
        this.pin = pin;
        this.accid = accid;
        this.accname = accname;
        this.otp = generateOTP();

        setTitle("SMART ATM SIMULATOR");
        setSize(600, 400);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        displayArea = new JTextArea();
        displayArea.setEditable(false);
        displayArea.setFont(new Font("Monospaced", Font.HANGING_BASELINE, 14));
        displayArea.setText("OTP sent to your phone: " + otp + "\nEnter OTP:");
        add(new JScrollPane(displayArea), BorderLayout.NORTH);

        inputField = new JTextField();
        add(inputField, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(6, 1, 4, 4));

        printButton = new JButton("Print Details");
        pinButton = new JButton("Change PIN");
        withdrawButton = new JButton("Withdraw");
        depositButton = new JButton("Deposit");
        balanceButton = new JButton("Check Balance");
        exitButton = new JButton("Exit");

        printButton.addActionListener(this);
        pinButton.addActionListener(this);
        withdrawButton.addActionListener(this);
        depositButton.addActionListener(this);
        balanceButton.addActionListener(this);
        exitButton.addActionListener(this);

        buttonPanel.add(printButton);
        buttonPanel.add(pinButton);
        buttonPanel.add(withdrawButton);
        buttonPanel.add(depositButton);
        buttonPanel.add(balanceButton);
        buttonPanel.add(exitButton);

        add(buttonPanel, BorderLayout.EAST);

        setButtonsEnabled(false);

        otpButton = new JButton("Submit");
        otpButton.addActionListener(this);
        add(otpButton, BorderLayout.SOUTH);
    }

    private void setButtonsEnabled(boolean enabled) {
        printButton.setEnabled(enabled);
        pinButton.setEnabled(enabled);
        withdrawButton.setEnabled(enabled);
        depositButton.setEnabled(enabled);
        balanceButton.setEnabled(enabled);
        exitButton.setEnabled(enabled);
    }

    public int generateOTP() {
        Random rand = new Random();
        return 1000 + rand.nextInt(9000);
    }

    public boolean validatepin(int pin) {
        return this.pin == pin;
    }

    public boolean validatepin(int actualotp, int enterotp) {
        return actualotp == enterotp;
    }

    public void deposit(int balance) {
        amount += balance;
        displayArea.setText("Deposit Successfully.");
    }

    public void withdraw(int balance) {
        if (balance > amount) {
            displayArea.setText("Insufficient balance.");
        } else {
            amount -= balance;
            displayArea.setText("Withdraw Successfully.");
        }
    }

    public void checkamount() {
        displayArea.setText("Current Balance: $" + amount);
    }

    public void printing() {
        displayArea.setText("Account Details:  Name: " + accname + "  ID: " + accid + "  Balance: $  " + amount);
    }

    public void Changepin(int newpin) {
        this.pin = newpin;
        displayArea.setText("PIN changed successfully.");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String input = inputField.getText().trim();

        if (e.getSource() == otpButton) {
            if (step == 0) {
                try {
                    int enteredOtp = Integer.parseInt(input);
                    if (validatepin(otp, enteredOtp)) {
                        displayArea.setText("OTP verified.\nEnter PIN:");
                        step = 1;
                    } else {
                        displayArea.setText("Invalid OTP. Try again:");
                    }
                } catch (Exception ex) {
                    displayArea.setText("Enter a valid OTP.");
                }
            } else if (step == 1) {
                try {
                    int enteredPin = Integer.parseInt(input);
                    if (validatepin(enteredPin)) {
                        displayArea.setText("Login successful.\nUse buttons below to perform actions.");
                        setButtonsEnabled(true);
                        inputField.setText("");
                        otpButton.setEnabled(false);
                        step = 2;
                    } else {
                        displayArea.setText("Invalid PIN. Try again:");
                    }
                } catch (Exception ex) {
                    displayArea.setText("Enter a valid PIN.");
                }
            }
            return;
        }

        if (step < 2) {
            displayArea.setText("Please complete OTP and PIN verification first.");
            return;
        }

        try {
            if (e.getSource() == printButton) {
                printing();
            } else if (e.getSource() == pinButton) {
                String newPinStr = JOptionPane.showInputDialog(this, "Enter new PIN:");
                if (newPinStr != null) {
                    int newPin = Integer.parseInt(newPinStr);
                    Changepin(newPin);
                }
            } else if (e.getSource() == withdrawButton) {
                String amtStr = JOptionPane.showInputDialog(this, "Enter amount to withdraw:");
                if (amtStr != null) {
                    int amt = Integer.parseInt(amtStr);
                    withdraw(amt);
                }
            } else if (e.getSource() == depositButton) {
                String amtStr = JOptionPane.showInputDialog(this, "Enter amount to deposit:");
                if (amtStr != null) {
                    int amt = Integer.parseInt(amtStr);
                    deposit(amt);
                }
            } else if (e.getSource() == balanceButton) {
                checkamount();
            } else if (e.getSource() == exitButton) {
                displayArea.setText("Thank you for using ATM.");
                setButtonsEnabled(false);
            }
        } catch (NumberFormatException ex) {
            displayArea.setText("Invalid input. Please enter numbers only.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ATMGUI atm = new ATMGUI(1000, 1234, 24131010, "Aryan Sharma");
            atm.setVisible(true);
        });
    }
}
