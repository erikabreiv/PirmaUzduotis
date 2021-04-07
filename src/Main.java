import java.util.Scanner;

public class Main {

    private static Scanner in;
    private static String message;
    private static String mappedKey;

    public static void main (String[] args){

        in = new Scanner (System.in);
        System.out.println("1.Užkoduoti\n2.Atkoduoti\nPasirinkti : ");
        int choice = in.nextInt();
        in.nextLine ();

        if (choice == 1) {
            System.out.println("---Užkoduoti---");
            msgAndKey();
            ciperEncryption(message,mappedKey);
        } else if (choice == 2) {
            System.out.println("---Atkoduoti---");
            msgAndKey();
            ciperDecryption(message,mappedKey);
        }else {
            System.out.println("Blogas pasirinkimas");
        }
    }

    private static void ciperDecryption(String message, String mappedKey) {
        int[][] table = createVingenereTable();
        String decryptedText = "";
        for (int i=0;i <message.length();i++){
            if(message.charAt(i)== (char)32 && mappedKey.charAt(i) == (char)32){
                decryptedText += "";
            }else {
                decryptedText += (char)(65 + itrCount((int)mappedKey.charAt(i), (int)message.charAt(i)));
            }
        }
        System.out.println("Atkoduotas tekstas: " + decryptedText);
    }


    private static int itrCount (int key, int msg){
            int counter = 0;
            String result = "";
            for (int i = 0; i < 26; i++){
                if(key+i > 90){
                    result += (char)(key+(i-26));

                }else{
                    result += (char)(key+i);
                }
            }
            for (int i = 0; i< result.length();i++){
                if(result.charAt(i)==msg){
                    break;
                }else {
                    counter++;
                }
            }
            return counter;
        }


    private static void ciperEncryption (String message,String mappedKey){
        int[][] table = createVingenereTable();
        String encryptedText = "";
        for (int i=0; i< message.length();i++){
            if (message.charAt(i) == (char)32 && mappedKey.charAt(i)== (char)32) {
                encryptedText += "";
            }else {
                encryptedText += (char)table[(int)message.charAt(i)-65][(int)mappedKey.charAt(i)-65];

            }
        }
        System.out.println("Užkoduotas tekstas: " + encryptedText);
    }

    private static int[][]  createVingenereTable () {
        int[][] tableArr = new int[26][26];
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < 26; j++) {
                int temp;
                if ((i + 65) + j > 90) {
                    temp = ((i + 65) + j) - 26;
                    tableArr[i][j] = temp;
                } else {
                    temp = (i + 65) + j;
                    tableArr[i][j] = temp;
                }
            }
        }
        return tableArr;
    }




        private static void msgAndKey() {
            System.out.println("**Nenaudokite simbolių**");

            System.out.println("Įveskite žinutę");
            String msg = in.nextLine();
            msg = msg.toUpperCase();

            System.out.println("Įveskite raktą");
            String key = in.next();
            key = key.toUpperCase();

            String keyMap = "";
            for(int i=0, j= 0; i< msg.length(); i++) {
                if(msg.charAt(i)== (char)32){
                    keyMap+= (char)32;
                }else {

                    if(j< key.length()){
                        keyMap += key.charAt(j);
                        j++;
                    }else {
                        j = 0;
                        keyMap += key.charAt(j);
                        j++;
                    }
                }
            }
            message = msg;
            mappedKey = keyMap;


    }
    }

