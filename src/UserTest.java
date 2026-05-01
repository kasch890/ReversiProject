import java.util.Scanner;

 public class UserTest {

     // An example reading strings
// L. McGeoch, 9/2004

     public static Scanner keyboard = new Scanner(System.in);

     public static void main(String[] args) {

         String name; // the user’s name
         int age; // the user’s age
         System.out.print("What’s your name? ");
         name = keyboard.nextLine();

         System.out.print("What’s your age? ");
         age = keyboard.nextInt();

         System.out.println("Welcome " + name +
                 ", I hope you enjoy programming in Java.");
         System.out.println("You seem to be " + age + " years old.");
     }
 }