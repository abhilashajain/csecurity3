import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

import java.util.Scanner;
import java.io.File;

import java.io.FileWriter;
import java.util.ArrayList; 

import java.io.IOException;

 
public class verifypass {
 
    
    private static byte[] key;

    private static SecretKeySpec sKey;
 
 public static String decrypt(String strToDecrypt, String secret) 
    {
        try
        {
            setKey(secret);
            Cipher c = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            c.init(Cipher.DECRYPT_MODE, sKey);
            return new String(c.doFinal(Base64.getDecoder().decode(strToDecrypt)));
        } 
        catch (Exception e) 
        {
            System.out.println("Decryption failed with error : " + e.toString());
        }
        return null;
    }


    public static void setKey(String myKey) 
    {
        MessageDigest sha = null;
        try {
            key = myKey.getBytes("UTF-8");
            sha = MessageDigest.getInstance("SHA-1");
            key = sha.digest(key);
            key = Arrays.copyOf(key, 16); 
            sKey = new SecretKeySpec(key, "AES");
        } 
        catch (NoSuchAlgorithmException e) {
	    System.out.println("Setting key failed : " + e.toString());
            e.printStackTrace();
        } 
        catch (UnsupportedEncodingException e) {
	    System.out.println("Setting key failed : " + e.toString());
            e.printStackTrace();
        }
    }
 



public static void main(String[] args) 
{

    
   
   //ArrayList<String> ID = new ArrayList<String>();
   boolean idFound = false;
   boolean passFound = false;
   //ArrayList<String> Password = new ArrayList<String>();
   Scanner idScanner = new Scanner(System.in);
    System.out.println("Enter Username");
   String Id = idScanner.nextLine();	
    System.out.println("Enter Password");
try{
	File fp = new File("password");
   String pass = idScanner.nextLine();
   final String sKey = "abhilashassecret";
    
     Scanner myReader = new Scanner(fp);
      myReader.useDelimiter(" |\\n");
     
      while (myReader.hasNext()) {
	if(myReader.next().equals(Id) ){
			   idFound=true;
		if(verifypass.decrypt(myReader.next(), sKey).equals(pass))
			    passFound = true;
		
	}


  	}

} catch (IOException e) {
      System.out.println("Exiting with error -- ");
      System.out.println(e.getMessage());
      System.out.println("Please make sure password file is present in the working directory!");
            
	System.exit(0);
    }


if(passFound)
	System.out.println("The password is correct");
else if(idFound)
	System.out.println("The password is incorrect");
else
System.out.println("ID does not exist");

}


}
