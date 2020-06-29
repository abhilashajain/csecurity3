import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList; 
import java.io.IOException;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
 
public class genpass {
 
    private static SecretKeySpec sKey;
    private static byte[] key;
    public static String encrypt(String strToEncrypt, String secret) 
    {
        try
        {
            setKey(secret);
            Cipher c = Cipher.getInstance("AES/ECB/PKCS5Padding");
            c.init(Cipher.ENCRYPT_MODE, sKey);
            return Base64.getEncoder().encodeToString(c.doFinal(strToEncrypt.getBytes("UTF-8")));
        } 
        catch (Exception e) 
        {
            System.out.println("Error while encrypting: " + e.toString());
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
            e.printStackTrace();
        } 
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
 
   


public static void main(String[] args) 
{

   
   File fp = new File("password");
  // ArrayList<String> ID = new ArrayList<String>();
   String id = null;
   //ArrayList<String> Password = new ArrayList<String>();
    int i=0;
	
   final String sKey = "abhilashassecret";
    try{ 
    FileWriter myWriter = new FileWriter("password",true);
   System.out.println("How many users you wish to create?");
   Scanner in = new Scanner(System.in); 
  
   int n = Integer.parseInt(in.nextLine()); 
   
    for(i=0; i<n ; i++){
          
	System.out.println("Enter Id for User no." + (i+1));

	//ID.add(in.nextLine());
	id = in.nextLine();
	System.out.println("Enter Password for User no." + (i+1));
	
	//Password.add(in.nextLine());
	myWriter.write(id+ " "+ genpass.encrypt(in.nextLine(), sKey)+"\n") ;	
	
	

	}
myWriter.close();

} catch (IOException e) {
      System.out.println("A file processing error has occured -");
      System.out.println(e.getMessage());
      System.exit(0);
    } catch (NumberFormatException e) {
      System.out.println("Number format exception :: Please Enter a valid number of users");
      //System.out.println(e.getError());
      System.exit(0);
    }

System.out.println(i + " users created!");


}


}
