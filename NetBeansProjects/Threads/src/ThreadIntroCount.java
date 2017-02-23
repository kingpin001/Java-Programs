/*public class ThreadIntroCount {
    
 public static void main(String[] args) {
        
  System.out.println("**********MAIN THREAD STARTING" );
  MyCountUpCode mc1 = new MyCountUpCode("SUBTHREAD 1"); //create an object with my code
     
  Thread subThread1 = new Thread(mc1); //create thread object
   
  MyCountDownCode mc2 = new MyCountDownCode("SUBTHREAD 2"); //create an object with my code                                              //called mycode
  Thread subThread2 = new Thread(mc2);  //create thread object that represents
                                           
        
  subThread2.setPriority(Thread.MAX_PRIORITY); //place priority on thread
  subThread1.setPriority(Thread.MIN_PRIORITY); // place priority on thread
  subThread1.start(); //start means to "make elgible for execution"
  subThread2.start(); //not necessarily that it will begin running right away
     
  //right now we have 3 elgible threads that can be chosen to be run
  // they can be run concurrently, or one thread fully all the way through
  //without stopping...just depends on the o/s on your computer
      
  //Since main thread started the subthreads, main should wait until
  //the two subthreads finish and then finish itself.
  //If not, main thread may end before subthreads which is not good coding
     try{  
        subThread1.join(); //main waits here till subthread is done
        subThread2.join(); //main waits here till subthread is done
        System.out.println("***************MAIN THREAD IS ENDING**THE 2 SUB THREADS ARE TERMINATED");
             }
        catch (InterruptedException exc)  {
            System.out.println("ERROR IN MAIN");
             }
          }
}//end ThreadIntroCount
//*******************************************************************
//   CODE FOR SUBTHREAD 1 IS HERE-COUNT UP CODE                                            *
//*******************************************************************
//must have a run method if implement Runnable
class MyCountUpCode implements Runnable{ 
String thrdName;  //threadname we pass in for messages
MyCountUpCode(String inname){ 
    thrdName=inname;
}
//override of run() method, required for Runnable interface
public void run(){
    System.out.println(" "+thrdName+ " STARTING");
    
    for (int i=0; i<100; i++) {
    System.out.println("IN THREAD 1 AND COUNTING UP -- "+
                " COUNT IS " +i);
    }  
    System.out.println(thrdName + " terminating.");
  }
} //endclass

//*******************************************************************
//   CODE FOR SUBTHREAD 2 IS HERE -COUNTDOWN CODE                                           *
//*******************************************************************
class MyCountDownCode implements Runnable{ 
String thrdName;  //threadname we move in 
MyCountDownCode(String inname){ //
    thrdName=inname;
}
//override of run-required
public void run(){
  System.out.println(" "+thrdName+ " STARTING");
    
  for (int i=100; i>0; i-=1) {
  System.out.println("IN THREAD 2 AND COUNTING DOWN-- "+
                "COUNT IS " +i);
  }  
  System.out.println(thrdName + " terminating.");
  }
} //endclass
  
*/
  //       COUNT 5 PROGRAM WITH THREAD POINTER IN THE OBJECT AND MULTIPLE COPIES OF THE SAME CLASS MADE
// 
//   4 threads          CPU
//
//subthread1 loop   subthread2 count loop  subthread3 count loop  + mainloop   --


//  main logic below in loop waits for the subthreads to end based on its loop condition

//*******************************************************************
//    MAIN THREAD                                                   *
public class ThreadIntroCount {
public static void main(String[] args) {
    
System.out.println("MAIN TASK/THREAD STARTING");
 //create 3 objects/threads below ...here 3 copies of
//my code exist and each one has a pointer out to a thread object.
//constructor makes a thread and starts it
        
MyCode subThread1 = new MyCode("SUBTHREAD 1"); 
MyCode subThread2 = new MyCode("SUBTHREAD 2"); 
MyCode subThread3 = new MyCode("SUBTHREAD 3"); 
        

     
     
 try{  
        subThread1.threadName.join(); //main waits here till subthread is done
        subThread2.threadName.join(); //main waits here till subthread is done
        subThread3.threadName.join();
        System.out.println("***************MAIN THREAD IS ENDING**THE 3 SUB THREADS ARE TERMINATED");
     }
 catch (InterruptedException exc)  {
            System.out.println("ERROR IN MAIN");
     } 
     
     
     
    
  }} //endclass main
 
//***************************************************************** 
//THIS CLASS IS NEWED UP 3 TIMES AND EACH OBJECT IS A THREAD, 
//THE CODE SIMPLY COUNTS TO 5 WITH A SLEEP BREAK
class MyCode implements Runnable{ 
int count;
Thread threadName; //variable of type thread, pointS to thread object

MyCode(String inname){ //contructor to create a thread and start it
    threadName= new Thread(this,inname); //here is where Thread object gets its name
    count=1;
    threadName.start(); //makes thread elgible to run
}
//override of run is required because of runnable interface
public void run(){
 System.out.println(" ***alert "+threadName.getName()+ " STARTING");//use method getname
 try{ while (count !=5) {
      
     System.out.print("\nCOUNTING IN THREAD "+threadName.getName()+" NOW SLEEP IN THIS THREAD FOR 5 SECONDS...");
     Thread.sleep(5000);  //put current thread to sleep to allow others to run
     count++;
     System.out.println("\n  WOKE UP IN " + threadName.getName()+ " COUNT IS "+count);
   }
 }
     catch (InterruptedException exc)  {
             System.out.println("ERROR");
      }
    //if count ever gets to 5, terminate thread
  System.out.println("**** "+threadName.getName() + "--> terminating"+ " ****");
 } //end run 
}//end my code


