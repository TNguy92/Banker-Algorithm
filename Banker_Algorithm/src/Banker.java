import javax.swing.JOptionPane;
import java.util.Scanner;
import java.io.FileReader;
import java.util.*;
import java.io.*;
import java.io.BufferedReader;

class Banker {

    public static void main(String[] args) throws IOException{

        FileReader fr = new FileReader("input.txt");
        Scanner sc = new Scanner(fr);
        int n = sc.nextInt();
        System.out.print("Number of Process: " + n);
        int m = sc.nextInt();
        System.out.print("\nNumber of Resources: " + m);

        int available[] = new int[m];
        int max[][] = new int[n][m];
        int allocation[][] = new int[n][m];
        int need[][] = new int[n][m];
        String sequence = "";

        System.out.print("\nNumber Of Available Resource: \n");

        String inputLine[];

        String tmp = sc.nextLine();

        tmp = sc.nextLine();
        System.out.print(tmp);



        inputLine = new String[(tmp.split(" ")).length];



        tmp = sc.nextLine();
while(sc.hasNextLine()) {
    inputLine = tmp.split(" ");
    System.out.print("\nAllocation: ");
    for (int i = 0; i < n; i++) {


        for (int j = 0; j < m; j++) {
            allocation[i][j] = Integer.parseInt(inputLine[j]);
            System.out.print(" " + allocation[i][j]);
        }


        System.out.print("\nClaim Max: ");

        i = 0;
        for (int j = 0; j < n; j++, i++) {
            if ((j + m) < inputLine.length) {
                max[i][j] = Integer.parseInt(inputLine[j + m]);
                System.out.print(" " + max[i][j]);
            }
        }

    }
    tmp = sc.nextLine();
}


//        for(int i = 0; i < m; i++)
//        {
//            available[i] = sc.nextInt();
//            System.out.print(" " + available[i]);
//        }
//
//        System.out.print("\nAllocation: ");
//        for(int i = 0; i < n; i++)
//        {
//            for(int j = 0; j < n; j++)
//            {
//                if( j < m )
//                {
//                    allocation[i][j] = sc.nextInt();
//                    System.out.print(" " + allocation[i][j]);
//                }
//                if(j >m)
//                {
//                    max[i][j] = sc.nextInt();
//                    System.out.print(" " + max[i][j]);
//                }
//                //System.out.print(" " + allocation[i][j]);
//            }
//        }



        for(int i = 0; i < n; i++)
        {
            for(int j = 0; j < m; j++)
            {
                need[i][j] = max[i][j] - allocation[i][j];
            }
        }
        boolean finish[] = new boolean[n];

        for(int i = 0; i < n; i++)
        {
            finish[i] = false;
        }

        boolean check = true;
        while(check) //Until all process allocated
        {
            check = false;
            for(int i = 0; i < n; i++)
            {
                if(!finish[i]) //Trying to allocate
                {
                    int j;
                    for(j = 0; j < m; j++)
                    {
                        if(need[i][j] > available[j])
                        {
                            break; //No allocation
                        }
                    }
                    if(j == m) //If all processes are allocated
                    {
                        for(j=0; j < m; j++)
                        {
                            available[j] = available[j] - need[i][j] + max[i][j];  //+ allocation[i][j];
                        }
                        finish[i] = true;
                        check = true;
                        sequence += i + ", ";
                    }
                }
            }
        }

        int i;
        for(i = 0; i < n; i++)
        {
            if(!finish[i])
                break;
        }

        if(i==n)
        {
            System.out.print("\nSAFE And Sequence is: " + sequence);
        }
        else
        {
            System.out.print("\nDEAD LOCK");
        }
    }
}