import java.util.*;
public class PreEmptiveShortestJobFirst {
    public static void main (String args[])
    {
        System.out.println("==============================================");
        System.out.println("  PRE EMPTIVE SHORTEST JOB FIRST SCHEDULING");
        System.out.println("==============================================");

        Scanner in = new Scanner (System.in);
        
        int numberOfProcesses = 0;
        final int MAX_PROCESS_COUNT = 10;

        do{
            System.out.print("Input the number of Processes(max of 10):");
            numberOfProcesses = in.nextInt();           
        }while (numberOfProcesses > MAX_PROCESS_COUNT || numberOfProcesses == 0);

        //let [0] the first row of the 2D array represent JOB BURST TIME
        //The [1] second row to be the ARRIVAL TIME
        //The [2] third row to be the TIME OF COMPLETION
        //The [3] fourth row to be the TURN AROUND TIME
        //The [4] fifth  row to be the JOB WAITING TIME
        //The [5] last   row to be the COPY OF THE ORIGINAL JOB BURST TIME
        boolean isProcessComplete[] = new boolean[numberOfProcesses];
        int     listOfTimeArray[][] = new int [6][numberOfProcesses];
        
        for (int x = 0; x < numberOfProcesses; x++)
        {
            System.out.print("\nInput   Burst Time For Process " + (x+1) + ": ");
            listOfTimeArray[0][x] = in.nextInt();
            listOfTimeArray[5][x] = listOfTimeArray[0][x];
            
            System.out.print("Input Arrival Time For Process " + (x+1) + ": ");
            listOfTimeArray[1][x] = in.nextInt();   
            
        }

        createPreEmptiveShortestJobFirst(listOfTimeArray,isProcessComplete,numberOfProcesses);
        
    }
    public static void createPreEmptiveShortestJobFirst(int listOfTimeArray[][], boolean isProcessComplete[], int numberOfProcesses)
    {
        int runningCompletionTime = 0,  numberOfCompletedProcess = 0;
        float averageWaitTime = 0.0f;
        boolean isDone = false;
        do
        {
            int runningMinimumJobBurstTime = 50 ,jobIndex = numberOfProcesses;
            for (int x = 0; x < numberOfProcesses; x++)
            {
                if ((listOfTimeArray[1][x] <= runningCompletionTime) && (isProcessComplete[x] == false) && (listOfTimeArray[0][x] < runningMinimumJobBurstTime))
                {   
                    runningMinimumJobBurstTime = listOfTimeArray[0][x];
                    jobIndex = x;   
                }
            }

            if (jobIndex == numberOfProcesses)
                runningCompletionTime++;

            else
            {
                listOfTimeArray[0][jobIndex]--;
                runningCompletionTime++;
                if (listOfTimeArray[0][jobIndex] == 0)
                {
                    listOfTimeArray[2][jobIndex] = runningCompletionTime;
                    isProcessComplete[jobIndex]  = true;
                    numberOfCompletedProcess++;
                }
            }

            if (numberOfCompletedProcess == numberOfProcesses)
                isDone = true;
        }while (isDone == false);


        for (int x = 0; x < numberOfProcesses; x++)
        {
            listOfTimeArray[3][x] = listOfTimeArray[2][x] - listOfTimeArray[1][x];
            listOfTimeArray[4][x] = listOfTimeArray[3][x] - listOfTimeArray[5][x];
            averageWaitTime += listOfTimeArray[4][x];
        }



        System.out.println("==============================================");
        System.out.println("PROCESS    ARRIVAL    JOB  BURST    WAITING");
        System.out.println("NUMBER      TIME         TIME        TIME ");
        System.out.println("==============================================");

        for(int x = 0; x < numberOfProcesses; x++)
            System.out.println((x+1) +"\t\t\t"+ listOfTimeArray[1][x] +"\t\t\t"+ listOfTimeArray[5][x] +"\t\t\t"+ listOfTimeArray[4][x]);

        System.out.println("==============================================");
        System.out.println("Average Wait Time: "+ (averageWaitTime/numberOfProcesses) + " ms");
    }
}
