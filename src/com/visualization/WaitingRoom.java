package com.visualization;

import javax.swing.*;
import java.awt.*;

public class WaitingRoom extends JFrame{
    private JButton readyButton;
    private JTextArea chatArea;
    private JCheckBox readyCheckBox;
    private JTextField inputMessage;
    private JLabel inputMessageLabel;
    private JPanel mainPanel;
    private JLabel nickLabel;
    private JTextField nicknameTextField;

    public WaitingRoom() throws HeadlessException {
        super("Waiting room");
        this.setVisible(true);
        this.setSize(400,260);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(mainPanel);
        this.setLocationRelativeTo(null);
    }
    public void setFieldsAfterNickname(){
        nicknameTextField.setEnabled(false);
        nickLabel.setEnabled(true);
        readyCheckBox.setEnabled(true);
        chatArea.setEnabled(true);
        inputMessageLabel.setEnabled(true);
        inputMessage.setEnabled(true);

    }
    public JButton getReadyButton() {
        return readyButton;
    }

    public void setReadyButton(JButton readyButton) {
        this.readyButton = readyButton;
    }

    public JTextArea getChatArea() {
        return chatArea;
    }

    public void setChatArea(JTextArea chatArea) {
        this.chatArea = chatArea;
    }

    public JCheckBox getReadyCheckBox() {
        return readyCheckBox;
    }

    public void setReadyCheckBox(JCheckBox readyCheckBox) {
        this.readyCheckBox = readyCheckBox;
    }

    public JTextField getInputMessage() {
        return inputMessage;
    }

    public void setInputMessage(JTextField inputMessage) {
        this.inputMessage = inputMessage;
    }

    public JLabel getInputMessageLabel() {
        return inputMessageLabel;
    }

    public void setInputMessageLabel(JLabel inputMessageLabel) {
        this.inputMessageLabel = inputMessageLabel;
    }

    public JTextField getNicknameTextField() {
        return nicknameTextField;
    }

    public JLabel getNickLabel() {
        return nickLabel;
    }
}
