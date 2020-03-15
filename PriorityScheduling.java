import java.util.*;
public class PriorityScheduling
{
    public static void main (String[] args)
    {
        System.out.println("==============================================");
        System.out.printf("%31s%n","PRIORITY SCHEDULING");
        System.out.println("==============================================");

        Scanner in = new Scanner (System.in);
        int numberOfProcesses = 0;
        final int MAX_PROCESS_COUNT = 10;

        do{
            System.out.print("Input the number of Processes(max of 10):");
            numberOfProcesses = in.nextInt();           
        }while (numberOfProcesses > MAX_PROCESS_COUNT || numberOfProcesses == 0);

        int jobBurstTime[]      = new int [numberOfProcesses];
        int jobPriorityNumber[] = new int [numberOfProcesses];
        
        for (int x = 0; x < numberOfProcesses; x++)
        {
            System.out.print("\nInput  Burst  Time  For   Process " + (x+1) + ": ");
            jobBurstTime[x] = in.nextInt();
            
            int jobPriorityNum = 0;
            boolean isInvalid = false, isRepeating = false;
        
            do{
                System.out.print("Input Priority Number for Process " + (x + 1) + ": ");
                jobPriorityNum = in.nextInt();
                
                for (int y = 0; y < numberOfProcesses; y++)
                {
                    if (jobPriorityNumber[y] == jobPriorityNum)
                    {
                        System.out.println("\nThere already exist a priority number " + jobPriorityNum + "\n");
                        isRepeating = true;
                        break;
                    }
                    else
                        isRepeating = false;
                }
            
                if (jobPriorityNum > numberOfProcesses || jobPriorityNum == 0)
                {
                    System.out.println("\nJob Priority Input must be between 0 and " + numberOfProcesses + "\n");
                    isInvalid = true;
                }
                else
                    isInvalid = false;
                    
            }while(isInvalid != false || isRepeating != false);
            jobPriorityNumber[x] = jobPriorityNum;
        }
        createPriorityScheduling(jobPriorityNumber,jobBurstTime,numberOfProcesses);
    }
    
    public static void createPriorityScheduling(int jobPriorityNum[], int jobBurstTime[], int numberOfProcesses)
    {
        final int ROW_COUNT = 3;
        int tempStorage[][] = new int [ROW_COUNT][numberOfProcesses];
        int waitingTime[] = new int [numberOfProcesses];
        int totalWaitingTime = 0;
        float averageWaitingTime = 0.0f;
        
        for (int x = 0; x < ROW_COUNT - 1; x++)
        {
            for (int y = 0; y < numberOfProcesses; y++)
            {
                if (x == 0)
                    tempStorage[x][y] = jobPriorityNum[y];      
                if (x == 1)
                    tempStorage[x][y] = jobBurstTime[y];        
            }

        }   

        Arrays.sort(jobPriorityNum);

        for (int x = 0; x < numberOfProcesses; x ++)
        {
            for (int y = 0; y < numberOfProcesses; y++)
            {
                if (tempStorage[0][x] == jobPriorityNum[y])     
                    tempStorage[2][y] = (x + 1);            
            }
        }

        for (int x = 0; x < numberOfProcesses; x++)
        {
            for (int y = 0; y < numberOfProcesses; y++)
            {
                if (jobPriorityNum[x] == tempStorage[0][y])
                {
                    jobBurstTime[x] = tempStorage[1][y];
                }
            }
        }
        waitingTime[0] = 0;

        for (int x = 0; x < numberOfProcesses-1; x++)
        {
            totalWaitingTime += jobBurstTime[x];
            waitingTime[x + 1] = totalWaitingTime;  
        }   

        System.out.println("================================================\n               PRIORITY SCHEDULING\n================================================");
        System.out.println("JOB NAME\tBURST TIME\tPRIORITY\tWAITING TIME\n================================================");
        for (int x = 0; x < numberOfProcesses; x++) 
        {
            if (jobBurstTime[x] >= 10)
                System.out.println("PROCESS " + tempStorage[2][x] + "\t\t  " + jobBurstTime[x] + "\t\t" + jobPriorityNum[x] + "\t\t\t" + waitingTime[x]);
            else
                System.out.println("PROCESS " + tempStorage[2][x] + "\t\t  " + jobBurstTime[x] + "\t\t\t" + jobPriorityNum[x] + "\t\t\t" + waitingTime[x]);
            if (x != numberOfProcesses - 1)
                averageWaitingTime += waitingTime[x + 1];
        }
        System.out.println("================================================");
        averageWaitingTime /= numberOfProcesses;
        System.out.println("Average Waiting time: " + averageWaitingTime + "ms");
    }
}
