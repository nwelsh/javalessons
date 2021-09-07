// created 9/7/21
// updated 9/7/21

// Question 1: what is the difference between instance variables and local variables 
// instance variables are accessible by all methods in the class 
class Athlete {
public String athleteName; // these are instance 
public double athleteSpeed;
public int athleteAge;
}
//local varibales are present within a block, function, or constructor and can only be accessed inside that
public void athlete() {
String athleteName; // these are local 
double athleteSpeed;
int athleteAge;
}

// how do you have an infinite loop 
for (;;)
{
   
}
while(true){
   
}
do {

}while(true);

// constructor overloading 
// when you have many constructors and each have their own parameters and are used at different times 
class Example {
int variable1, variable2;
double variable3;
public Example(int var1, int var2) {
 variable1 = var1;
 variable2 = var2;
}
public Hospital(int var1) {
 variable1 = var1;
}
public Hospital(double var3) {
 variable3 = var3
}
}

// method overloading 
// similar to constructor overlaoding, but with methods 
class OverloadingHelp {
   public int findarea (int l, int b) {
           int var1;
           var1 = l * b;
           return var1;
   }
   public int findarea (int l, int b, int h) {
           int var2;
           var2 = l * b * h;
           return var2;
   }
}
// you use find area 1 if there is a 2d shape and find area 2 if there is a 3d shape 

// another example.... if there are the same method for two different classes 

class HumanBeing {
       public int walk (int distance, int time) {
               int speed = distance / time;
               return speed;
       }
}
class Athlete extends HumanBeing {
       public int walk(int distance, int time) {
               int speed = distance / time;
               speed = speed * 2;
               return speed;
       }
}
