/*
This program demonstrates how ROUND ROBIN SCHEDULING works.

Author: Elgen Prestosa of BS IT - 2B
*/

import java.util.*;
public class RoundRobinScheduling
{
    public static void main (String [] args)
    {
        Scanner in = new Scanner (System.in);


        int numberOfProcesses = 0, quantum = 0;
        final int MAX_PROCESS_COUNT = 10;

        System.out.println("==============================================");
        System.out.println("           ROUND ROBIN SCHEDULING");
        System.out.println("==============================================");

        do{
            System.out.print("Input the number of Processes(max of 10):");
            numberOfProcesses = in.nextInt();           
        }while (numberOfProcesses > MAX_PROCESS_COUNT || numberOfProcesses == 0);

        //listOfTimeArray[0][n] is reserve for job burst time. This part is subject for modification.
        //listOfTimeArray[1][n] is reserve for individual job waiting time
        //listOfTimeArray[2][n] is the copy of the original job burst time upon input. 
        int listOfTimeArray[][]  = new int[3][numberOfProcesses];

        for (int x = 0; x < numberOfProcesses; x++)
        {
            System.out.print("\nInput Burst Time For Process " + (x+1) + ": ");
            listOfTimeArray[0][x] = in.nextInt();

            listOfTimeArray[2][x] = listOfTimeArray[0][x];
        }

        System.out.print("\n    Input quantum / time slice: ");
        quantum = in.nextInt();

        createRoundRobin(listOfTimeArray,quantum,numberOfProcesses);
    }

    public static void createRoundRobin(int listOfTimeArray[][],int quantum,int numberOfProcesses )
    {
        float averageWaitingTime = 0.0f;
        int sum;
        do
        {
            for(int x = 0; x < numberOfProcesses; x++)
            {
                if(listOfTimeArray[0][x] > quantum)
                {
                    listOfTimeArray[0][x] -= quantum;

                    for(int y = 0; y < numberOfProcesses; y++)
                    {
                        if ((y != x) && (listOfTimeArray[0][y] != 0))
                            listOfTimeArray[1][y] += quantum;
                    }
                }
                else
                {
                    for(int z = 0; z < numberOfProcesses; z++)
                    {
                        if((z != x) && (listOfTimeArray[0][z] !=0))
                            listOfTimeArray[1][z] += listOfTimeArray[0][x];
                    }
                    listOfTimeArray[0][x] = 0;
                }
            }
            sum=0;
            for(int a = 0; a < numberOfProcesses; a++)
                sum += listOfTimeArray[0][a];

        }
        while(sum!=0);

        System.out.println("\n===========================================\n           ROUND  ROBIN SCHEDULING  \n===========================================");
        System.out.println("JOB NAME\t  BURST TIME\t  WAITING TIME\n===========================================");  
        for (int x = 0; x < numberOfProcesses; x++) 
        {
            System.out.println("PROCESS " + (x+1) + "\t\t\t" + listOfTimeArray[2][x] + "\t\t\t\t" + listOfTimeArray[1][x]);
            averageWaitingTime += listOfTimeArray[1][x];
        }
        averageWaitingTime /= numberOfProcesses;
        System.out.println("===========================================");
        System.out.println("Quantum / Time Slice: " + quantum + "ms");
        System.out.println("Average Waiting time: " + averageWaitingTime + "ms");
    }
}
