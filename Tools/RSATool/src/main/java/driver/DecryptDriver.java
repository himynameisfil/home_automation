package main.java.driver;

import java.io.FileInputStream;
import java.io.InputStream;
import java.math.BigInteger;
import java.util.Properties;

import main.java.util.ASCIIMinus31Encoder;

public class DecryptDriver {

	public static void main(String[] args) {
        BigInteger PublicKeyN;
        BigInteger PrivateKeyD;
        BigInteger decryptedMessage;
        String messageToDecrypt;
        ASCIIMinus31Encoder decodedMessage;
        Properties prop = new Properties();
        InputStream input = null;
        
        if (args.length != 2) {
            System.out.println("Sorry, you must enter 2 parameters, 1st=private key file 2nd=message to encrypt");
            System.exit(0);
        }
        
        try {
            input = new FileInputStream(args[0]);
            prop.load(input);
            PublicKeyN = new BigInteger(prop.getProperty("PublicKeyN"));
            PrivateKeyD = new BigInteger(prop.getProperty("PrivateKeyD"));
            messageToDecrypt = args[1];
            decryptedMessage = new BigInteger(messageToDecrypt);
            decryptedMessage = decryptedMessage.modPow(PrivateKeyD, PublicKeyN);
            decodedMessage = new ASCIIMinus31Encoder(decryptedMessage);
            System.out.println(decodedMessage.getMessage());
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
	}

}
