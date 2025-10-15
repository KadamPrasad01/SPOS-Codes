import java.util.*;

public class BankersSimple{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of processes: ");
        int n = sc.nextInt();

        System.out.print("Enter number of resources: ");
        int m = sc.nextInt();

        int[][] alloc = new int[n][m]; 
        int[][] max = new int[n][m];   
        int[][] need = new int[n][m];  
        int[] avail = new int[m];      

        
        System.out.println("Enter Allocation Matrix:");
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                alloc[i][j] = sc.nextInt();

        
        System.out.println("Enter Max Matrix:");
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                max[i][j] = sc.nextInt();


        System.out.println("Enter Available Resources:");
        for (int j = 0; j < m; j++)
            avail[j] = sc.nextInt();

        sc.close();

        
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                need[i][j] = max[i][j] - alloc[i][j];

        
        boolean[] finished = new boolean[n];
        int[] safeSeq = new int[n];
        int count = 0;

        while (count < n) {
            boolean found = false;

            for (int i = 0; i < n; i++) {
                if (!finished[i]) {
                    int j;
                    for (j = 0; j < m; j++) {
                        if (need[i][j] > avail[j])
                            break;
                    }

                    if (j == m) {
                        for (int k = 0; k < m; k++)
                            avail[k] += alloc[i][k]; 
                        safeSeq[count++] = i;
                        finished[i] = true;
                        found = true;
                    }
                }
            }

            if (!found) { 
                break;
            }
        }


        if (count == n) {
            System.out.println("System is in SAFE state.");
            System.out.print("Safe Sequence: ");
            for (int i = 0; i < n; i++)
                System.out.print("P" + safeSeq[i] + " ");
        } else {
            System.out.println("System is NOT in safe state (Deadlock possible)");
        }
    }
}
