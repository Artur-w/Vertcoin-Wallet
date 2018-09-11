
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.util.Random;


/**
 * 
 * @author Artur Wenc
 *
 */

public class privateKeyGenerator {

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

	String _80string = "80"+hexGenerator64();
	System.out.println("80 Hex:\n"+_80string);
		
	String secret = _80string;
	
	//try{
	
		//System.out.println(SHA256.sha256(secret));
		
		//System.out.println(SHA256.sha256(SHA256.sha256(secret)));
	//}catch (NoSuchAlgorithmException e){}
	String dhash = new String();
	try {
		dhash = SHA256.sha256(SHA256.sha256(secret));
	} catch (NoSuchAlgorithmException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
			String _8Front = dhash.substring(0,8);
			System.out.println("Doubled sha256hash 8 front chars:\n"+_8Front);
			
			String _80string_8dhash = _80string + _8Front;
			System.out.println("80String + 8char of doubled sha256:\n"+_80string_8dhash);
			
			//Base58.encode("hello");
			//System.out.println(Base58.encode(hexGenerator64()));
			
			//b58(_80string_8dhash);
			//new Long(Long.parseLong(_80string_8dhash));
			System.out.println(base58Encoder(_80string_8dhash));
	}
	

	/**
	 * Create 64 digit hexadecimal random String
	 * @return
	 */
	public static String hexGenerator64() {
		String[] hexArray = 
			{"0","1","2","3","4","5","6","7","8","9",
					"A","B","C","D","E","F"};
		Random rn = new Random();
		String randomHex = new String();
		for(int i =0;i<64;i++) {
			int x = rn.nextInt(16);
			randomHex += hexArray[x];
			
		}
		System.out.println("Random Hex:\n"+randomHex);
		return randomHex;
	}

	/**
	 * 
	 * @param s
	 * @return
	 */
	 public static String base58Encoder (String s){
	        BigInteger bigHex = new BigInteger(s, 16); //converts hex into big integer
	        BigInteger _58 = new BigInteger("58");
	        String encodedHex = new String();
	        String bitCoinString = "123456789ABCDEFGHJKLMNPQRSTUVWXYZabcdefghijkmnopqrstuvwxyz";
	        char bitCoinArray[] = bitCoinString.toCharArray();
	        
	        System.out.println("Big Integer: "+ bigHex);
	        
	        BigInteger mod = new BigInteger ("0");
	        
	        while(bigHex.compareTo(_58)>0){
	            mod = bigHex.mod(_58); //get remainder
	            encodedHex = bitCoinArray[mod.intValue()]+encodedHex;
	            bigHex = bigHex.divide(_58); //divide bigHex by 58
	           // System.out.println(bigHex);
	        
	        }
	        mod=bigHex.mod(_58);//get remainder
	        encodedHex = bitCoinArray[mod.intValue()]+encodedHex; //mod.intvalue converts remainder to int
	        bigHex = bigHex.divide(_58); //divide bigHex by 58
	        //System.out.println(bigHex);
	        
	        return encodedHex;
	 }

}
