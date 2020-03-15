import java.util.*;
public class FirstComeFirstServeScheduling
{
    public static void main (String [] args)
    {
        System.out.println("===========================================");
        System.out.printf("%38s","FIRST COME FIRST SERVE SCHEDULING\n");
        System.out.println("===========================================");
        
        //Declaration and initialization of variables.
        //Setting maximum number of processes by 10.
        Scanner in = new Scanner (System.in);
        int numberOfProcesses = 0;
        final int MAX_PROCESS_COUNT = 10;
        
        //A prompt to enter how many processes to execute.
        //Uses a loop for validation if the number entered exceeds 10 or equal to zero.
        do{
            System.out.print("Input the number of Processes(max of 10):");
            numberOfProcesses = in.nextInt();           
        }while (numberOfProcesses > MAX_PROCESS_COUNT || numberOfProcesses == 0);

        //Initializing an array of job burst time using the specific number of processes.
        int jobBurstTime[]    = new int [numberOfProcesses];
        
        //A loop that prompts the user to enter job burst time per process.
        for (int x = 0; x < numberOfProcesses; x++)
        {
            System.out.print("\nInput Burst Time For Process " + (x+1) + ": ");
            jobBurstTime[x] = in.nextInt();
        }

        createFirstComeFirstServe(jobBurstTime,numberOfProcesses);      
    }
    
    public static void createFirstComeFirstServe(int jobBurstTime[], int numberOfProcesses)
    {
        //Declaring an array to store the waiting time for each processes.
        int waitingTime[] = new int [numberOfProcesses];
        int totalWaitingTime = 0;
        float averageWaitingTime = 0.0F;
        
        //Assigning and calculating the waiting time.
        for (int x = 0; x < numberOfProcesses-1; x++)
        {
            totalWaitingTime += jobBurstTime[x];
            waitingTime[x + 1] = totalWaitingTime;  
        }   

        //Displaying process name, burst time, waiting time and average waiting time.
        System.out.println("==========================================\n          FIRST COME FIRST SERVE\n==========================================");
        System.out.println("JOB NAME\t  BURST TIME\t  WAITING TIME\n==========================================");
        for (int x = 0; x < numberOfProcesses; x++) 
        {
            System.out.println("PROCESS " + (x + 1) + "\t\t\t" + jobBurstTime[x] + "\t\t\t\t" + waitingTime[x]);
            if (x != numberOfProcesses - 1)
                averageWaitingTime += waitingTime[x + 1];
        }
        averageWaitingTime /= numberOfProcesses;
        System.out.println("==========================================");
        System.out.println("Average Waiting time: " + averageWaitingTime + "ms");
    }
}
