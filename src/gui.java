import com.sun.jndi.toolkit.url.UrlUtil;
import com.sun.webkit.network.Util;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.Authenticator;
import java.net.MalformedURLException;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.nio.charset.Charset;

public class gui extends JFrame {

    JPasswordField passfield;
    JTextField userfield;

    JLabel logininfo;

    public gui(){
        this.setTitle("TMDB api");
        this.setSize(500,500);

        addelements();
        addlisteners();

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    private boolean setProxy(String authUser, String authPassword) {
        Authenticator.setDefault(
                new Authenticator() {
                    @Override
                    public PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(
                                authUser, authPassword.toCharArray());
                    }
                }
        );

        //set https proxy
        System.setProperty("jdk.http.auth.tunneling.disabledSchemes", "");
        System.setProperty("https.proxyUser", authUser);
        System.setProperty("https.proxyPassword", authPassword);
        System.setProperty("https.proxyHost", "proxy.htl-steyr.ac.at");
        System.setProperty("https.proxyPort", "8082");

        //set http proxy
        System.setProperty("http.proxyUser", authUser);
        System.setProperty("http.proxyPassword", authPassword);
        System.setProperty("http.proxyHost", "proxy.htl-steyr.ac.at");
        System.setProperty("http.proxyPort", "8082");

        try {
            new URL("http://google.com").openStream();
            new URL("https://google.com").openStream();
            return true;
        } catch (IOException e1) {
            return false;
        }
    }

    private void addlisteners(){
        passfield.addActionListener(e -> {
            if(setProxy(userfield.getText(),String.copyValueOf(passfield.getPassword()))){
                logininfo.setText("logged in successfully");
            }else{
                logininfo.setText("loggin error!");
            }
            new apiTMDB();

//
        });
    }

    private void addelements(){
        this.setLayout(new BorderLayout());
        userfield = new JTextField();
        passfield = new JPasswordField();

        logininfo = new JLabel("Not logged in yet");

        JPanel authpanel = new JPanel(new GridLayout(2,2));
        authpanel.add(new JLabel("User:"));
        authpanel.add(userfield);
        authpanel.add(new JLabel("Password:"));
        authpanel.add(passfield);

        this.add(logininfo,BorderLayout.SOUTH);
        this.add(authpanel,BorderLayout.NORTH);
    }
}
