import java.util.*;
public class NonPreEmptiveShortestJobFirst
{
    public static void main (String [] args)
    {
        System.out.println("==============================================");
        System.out.println("NON PRE-EMPTIVE SHORTEST JOB FIRST SCHEDULING");
        System.out.println("==============================================");

        Scanner in = new Scanner (System.in);
        int numberOfProcesses = 0;
        final int MAX_PROCESS_COUNT = 10;

        do{
            System.out.print("Input the number of Processes(max of 10):");
            numberOfProcesses = in.nextInt();           
        }while (numberOfProcesses > MAX_PROCESS_COUNT || numberOfProcesses == 0);

        int jobBurstTime[] = new int [numberOfProcesses];

        for (int x = 0; x < numberOfProcesses; x++)
        {
            System.out.print("\nInput Burst Time For Process " + (x+1) + ": ");
            jobBurstTime[x] = in.nextInt();
        }
        
        createNonPreEmptiveShortestJobFirst(jobBurstTime,numberOfProcesses);
        
    }
    
    public static void createNonPreEmptiveShortestJobFirst(int jobBurstTime[], int numberOfProcesses)
    {
        int totalWaitingTime = 0;
        final int ROW_COUNT = 2;
        float averageWaitingTime = 0.0f;
        int tempStorage[][] = new int [ROW_COUNT][numberOfProcesses];
        int waitingTime[] = new int [numberOfProcesses];
        
        for (int x = 0; x < ROW_COUNT - 1; x++)
        {
            for (int y = 0; y < numberOfProcesses; y++)
                tempStorage[x][y] = jobBurstTime[y];            
        }

        Arrays.sort(jobBurstTime);

        for (int x = 0; x < numberOfProcesses; x ++)
        {
            for (int y = 0; y < numberOfProcesses; y++)
            {
                if (tempStorage[0][x] == jobBurstTime[y])       
                    tempStorage[1][y] = (x + 1);            
            }
        }

        for (int x = 0; x < numberOfProcesses-1; x++)
        {
            totalWaitingTime += jobBurstTime[x];
            waitingTime[x + 1] = totalWaitingTime;  
        }   

        System.out.println("===========================================\n     NON PRE EMPTIVE SHORTEST JOB FIRST\n===========================================");
        System.out.println("JOB NAME\t  BURST TIME\t  WAITING TIME\n===========================================");
        for (int x = 0; x < numberOfProcesses; x++) 
        {
            System.out.println("PROCESS " + tempStorage[1][x] + "\t\t\t" + jobBurstTime[x] + "\t\t\t\t" + waitingTime[x]);
            if (x != numberOfProcesses - 1)
                averageWaitingTime += waitingTime[x + 1];
        }
        averageWaitingTime /= numberOfProcesses;
        System.out.println("===========================================");
        System.out.println("Average Waiting time: " + averageWaitingTime + "ms");
    }
}
