import java.util.Scanner;
public class CodsoftA {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int totalmarks = 600 ; 
        int obtainamarks = 0;
        System.out.println("Enter the Marks of All Subjects : ");
        for (int index = 1; index <= 6; index++) {
            System.out.print( " Enter the marks of "+ index + " subject : ");
            int n = sc.nextInt();
            obtainamarks += n;
        }
        double avgpercentile =((double)  obtainamarks/ totalmarks)*100;
        System.out.println("Total marks obtain by students out of 600 : "+obtainamarks);
        System.out.println("Average percentage obtain by students  : "+avgpercentile+ " %");
        if (90<=avgpercentile) {
            System.out.println( " You pass exam with A Grade ");
        }else if(70<=avgpercentile){
            System.out.println( " You pass exam with B Grade ");  
        }else if (40<= avgpercentile) {
            System.out.println( " You pass exam with C Grade ");
        }else{
            System.out.println( " You  fail in exam ");
        }
        
    }
}