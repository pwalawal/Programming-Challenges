  import java.util.Scanner;

            class Pomodoro {
            	
              public static void pomodoro_task() throws InterruptedException {
                          
                  boolean flag = false;
                  int checkmark = 1;
                  System.out.println("Begin the task"); 
                  while (!flag) {
                	  	
                        System.out.println("Work for 25 minutes");
                        pomodoro_timer(25);
                        //Timer starts for 25 minutes
                        System.out.println("Checkmark " + checkmark +" complete");
                        //Keep track of check mark.
                        checkmark++;
                        if(checkmark>4)
                        {
                        	checkmark=1;
                        	//reset check mark after 4 rounds. 
                        	System.out.println("Take a 30 minute long break.");
                        	//Long break of 30 minutes
                        	pomodoro_timer(30);
                        	
                        }
                        else
                        {
                        	System.out.println("Take a 5 minute break.");
                        	//short break of 5 minutes
                        	pomodoro_timer(5);        	
                        }
                        
                     }
               }
              
              
             public static void pomodoro_timer(int timer_minutes) throws InterruptedException {
                    
            	  System.out.println("The Timer starts :");
            	  System.out.println(timer_minutes +" minutes remaining");
                  for (int minute=timer_minutes-1; minute >=0;--minute ) {
                    	
                        for (int second = 59; second >= 0; --second) {
                        	Thread.sleep(1000);
                            //will cause thread to suspend execution for 1 millisecond
                            System.out.println(minute + "m " + second+"s");    
  
                        }
                  
                    }
               }
              
              
              public static void main(String[] args) throws InterruptedException {
                	
                		System.out.println("Basic Pomodoro Timer");
                		pomodoro_task();
                       
               }

                
          }
