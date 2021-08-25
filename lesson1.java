// Lesson1: https://www.learnjavaonline.org/
// Date: 8/25/21
// Author: Nicole 


public class Main {
    public static void main(String[] args) {
        byte zero = 0;
        String output = "W" + zero + "w"; //output: W0w
        System.out.println(output);
    }
}

//exercise 2 
public class Main {
    public static void main(String[] args) {
        String a = new String("Wow");
        String b = a;
        String c = a + "!";
        String d = c;

        boolean b1 = a == b;
        boolean b2 = d.equals(b + "!");
        boolean b3 = !c.equals(a);

        if (b1 && b2 && b3) {
            System.out.println("Success!");
        }
    }
}
