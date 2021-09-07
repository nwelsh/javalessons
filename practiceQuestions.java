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
