import java.util.Random;
import java.util.Scanner;
public class Codsoft {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random rn = new Random();
        int maxrounds = 5;
        int totalscore = 0;
        for(int round = 1 ; round <= maxrounds ; round++){
        int randomnum = rn.nextInt(100)+1;
        int limitattempts = 7;
        int attempts = 0 ;
        System.err.println("Round "+ round+ " Starts ! :--- ");
        while(attempts<limitattempts){
            System.out.print("Your Guess Number  :  ");
            int guessnum = sc.nextInt();
            if (guessnum == randomnum) {
                System.out.println("Congrats! , Your Guess Number is correct . ");
                totalscore++;
                attempts++;
            }else if (guessnum < randomnum) {
                System.out.println(" Try Again ! , Your  Guess Number is low . ");
                attempts++;
            }else{
                System.out.println(" Try Again ! , Your  Guess Number is high . ");
                attempts++;
                }
            }
        
        }
        System.out.println("Totalscore by user : "+totalscore);
    }
}