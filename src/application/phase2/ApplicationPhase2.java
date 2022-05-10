package application.phase2;

import java.io.IOException;
import java.util.*;

public class ApplicationPhase2 {

    public static void main(String[] args) throws IOException {
        System.out.println("\t\t Project Algorithms and Data Structures || Phase 2\n -----------------------------------------------------------------------------");
        System.out.println("1- enter 1 to Read from the file");
        System.out.println("1- enter 2 to ((NOT)) Read from the file" + "\n3- enter 0 to exit the porgram ");
        System.out.println(" -----------------------------------------------------------------------------");

        Scanner input = new Scanner(System.in); // allow user to input 

        Graph graph = new Graph(); //create object of graph to access to the class 
        int choose = input.nextInt();
        
        /* the user has two choices: enter 1 or 2. if the user enters 1, 
        it will read from the file and display the result of the matrix .however, 
        if the user chooses 2, it will not read from fill the code will run and display the running time */

        switch (choose) {
            //                            ____________________________________________
            //                           |                                            |
            //---------------------------|                the first case              |---------------------------------------------
            //                           |____________________________________________|
            case 1: {

                graph.readGraphFromFile("test.txt");
                boolean read_file = true;

                System.out.println("1- enter 1 to Floyd Warshall algorithm ");
                System.out.println("2- enter 2 to Dijkstra algorithm "
                        + "\n -----------------------------------------------------------------------------");
                int type_Of_Alg_1 = input.nextInt();

                //if the user inter 1 will display the matrix of Floyd Warshall algorithm 
                if (type_Of_Alg_1 == 1) {
                    SingleSourceSPAlg s = new SingleSourceSPAlg(graph);
                    s.computeFloyedWarshalAlg(read_file);
                }

                //if the user inter 1 will display the matrix of Dijkstra algorithm 
                if (type_Of_Alg_1 == 2) {
                    AllSourceSPAlg a = new AllSourceSPAlg(graph);
                    a.computeDijkstraAlg(read_file);
                }
                //  graph.Print_Graph();
                break; // break from the loop
            }

            //                            ____________________________________________
            //                           |                                            |
            //---------------------------|             the second case                |------------------------------------------
            //                           |____________________________________________|
            case 2: {

                boolean read_file = false;

                System.out.println("1- enter 1 to Floyd Warshall algorithm ");
                System.out.println("2- enter 2 to Dijkstra algorithm \n -----------------------------------------------------------------------------");

                int type_Of_Alg_2 = input.nextInt();

                // if the user enter 1 , it will display the running time of  Floyd Warshall algorithm by following vertices and edges
                if (type_Of_Alg_2 == 1) {
                    System.out.println(">>> n is the number of vertices) and (m is the number of edges) : \n--------------------------\n");
                    System.out.println(" n=2000 , m=10000");
                    System.out.println(" n=3000 , m=15000");
                    System.out.println(" n=4000 , m=20000");
                    System.out.println(" n=5000 , m=25000");
                    System.out.println(" n=6000 , m=30000");
                    System.out.print("\n-------------------------\n ");
                    int[][] floyed = {{2000, 10000}, {3000, 15000}, {4000, 20000}, {50001, 25000}, {6000, 30000}};

                    for (int i = 0; i < 5; i++) { // loop for n and m 
                        graph = new Graph(floyed[i][0], floyed[i][1], false);

                        for (int j = 1; j < 6; j++) {  // loop for iterations 
                            SingleSourceSPAlg s = new SingleSourceSPAlg(graph);
                            System.out.println("-------------------------iteration" + j + "-------------------------\n" + "n = " + floyed[i][0] + " , m = " + floyed[i][1]);

                            long startF = System.currentTimeMillis(); // start time 
                            s.computeFloyedWarshalAlg(read_file);
                            long fineshF = System.currentTimeMillis(); // end time of alg
                            System.out.println("FloyedWarshal time " + (fineshF - startF)); //calculate the actual time 
                        }
                    }

                }

                // if the user enter 2 , it will display the running time of  Dijkstra algorithm by following vertices and edges
                if (type_Of_Alg_2 == 2) {

                    System.out.println(">>> n is the number of vertices) and (m is the number of edges) : \n--------------------------\n");
                    System.out.println(" n=5000 , m=25000");
                    System.out.println(" n=10000 , m=50000");
                    System.out.println(" n=15000 , m=75000");
                    System.out.println(" n=20000 , m=100000");
                    System.out.println(" n=25000 , m=125000");
                    System.out.print("\n-------------------------\n ");

                    int[][] Dijestra = {{5000, 25000}, {10000, 50000}, {15000, 75000}, {20000, 100000}, {25000, 125000}};
                    for (int i = 0; i < 5; i++) {
                        graph = new Graph(Dijestra[i][0], Dijestra[i][1], false);
                        for (int j = 1; j < 6; j++) {

                            AllSourceSPAlg a = new AllSourceSPAlg(graph);
                            System.out.println("-------------------------iteration" + j + "-------------------------\n" + "n = " + Dijestra[i][0] + " , m = " + Dijestra[i][1]);
                            long startD = System.currentTimeMillis(); // start time 
                            a.computeDijkstraAlg(read_file);
                            long fineshD = System.currentTimeMillis(); //end time 
                            System.out.println("Dijkstra time " + (fineshD - startD)); //calculate the actual time 
                        }
                    }
                }
                break;
            }

            case 0: {
                // terminate 
                break;
            }

            default:
                System.out.println("---Invalid input!---");
                break;
        }
    }

}
