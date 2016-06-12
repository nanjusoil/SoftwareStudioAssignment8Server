package com.example;

import java.io.InputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 * Created by Gary on 16/5/28.
 */
public class Server implements Runnable {
    private Thread thread;
    private ServerSocket servSock;
    private JTextArea  label;
    private JPanel panel;
    private JFrame frame;
    private String s;

    public Server(){

        frame = new JFrame("Testing");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel = new JPanel();
        label = new JTextArea("ascasc");
        panel.add(label);
        frame.add(panel);
        frame.setSize(400, 400);
        frame.setVisible(true);
        try {
            // Detect server ip
            InetAddress IP = InetAddress.getLocalHost();
            System.out.println("IP of my system is := "+IP.getHostAddress());
            System.out.println("Waitting to connect......");

            // Create server socket
            servSock = new ServerSocket(2000);
            System.out.println(servSock.getLocalPort());
            // Create socket thread
            thread = new Thread(this);
            thread.start();
        } catch (java.io.IOException e) {
            System.out.println("Socket������� !");
            System.out.println("IOException :" + e.toString());
        } finally{
        	
        }
    }

    @Override
    public void run(){
        // Running for waitting multiple client
        while(true){
            try{
                // After client connected, create client socket connect with client
                Socket clntSock = servSock.accept();
                InputStream in = clntSock.getInputStream();

                System.out.println("Connected!!");

                // Transfer data
                byte[] b = new byte[1024];
                int length;

                length = in.read(b);
                s = new String(b);
                System.out.println("[Server Said]" + s);
                label.setText( new String(b).substring(0, 20));
                frame.repaint();
            }
            catch(Exception e){
                //System.out.println("Error: "+e.getMessage());
            }
            finally{
            	
            }

        }
    }
}
