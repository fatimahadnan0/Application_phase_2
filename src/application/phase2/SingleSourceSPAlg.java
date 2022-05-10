package application.phase2;

/**
 *
 * @author fatimah
 */
public class SingleSourceSPAlg extends ShortestPathAlgorithm {

    /**
     *
     */
    Vertex dist[][]=graph.verticies;

    /**
     *
     * @param graph
     */
    public SingleSourceSPAlg(Graph graph) {
        super(graph);
    }

    /**
     *
     * @param read_file
     */
    public void computeFloyedWarshalAlg(boolean read_file) {
        
  
    /*  Now we use the Floyd Warshall Algorithm for solving the All Pairs Shortest Path problem. 
    the propuse is  to find the shortest path between evry vertices in a given edge weighted directed Graph by 
    using the following algorithm :
    D(K)[i,j] = min {D(k-1)[i,j], D(k-1)[i,k] + D(k-1)[k,j]} 
    
    */
    
     //   graph.verticies[0][0] , the weight will set to 0;
    // k = number of algorithm , like A1 ,A2 ... 
    
    
        for (int k = 0; k < graph.veticesNo; k++) { // n 
            // Pick all vertices as source one by one
            
            for (int i = 0; i < graph.veticesNo; i++) {//n
                // Pick all vertices as destination for the above picked source
                for (int j = 0; j < graph.veticesNo; j++) { //n
                    
                    // if the i k if it was null , will change the value to infinite number (NF) 
                    // if there is no edge from vertex i to j will set NF
                    if(graph.verticies[i][k] == null) 
                    {
                        graph.verticies[i][k] = new Vertex(INF);
                    }
                    if(graph.verticies[k][j] == null)
                    {
                        graph.verticies[k][j] = new Vertex(INF);
                    }
                    if(graph.verticies[i][j] == null)
                    {
                        graph.verticies[i][j] = new Vertex(INF);
                    }
                    if(graph.verticies[i]==graph.verticies[j]){ // All the diagonals values are zero ,such as (2,2) , (1,1) ...
                    graph.verticies[i][j].weight=0;
                    }
                    
                    // If vertex k is on the shortest path from i to j, then update the value of dist[i][j]
                    /* we use this part D(k-1)[i,k] + D(k-1)[k,j]  and check if the right side is smaller than left side 
                        will updated the large value to the smaller one */
                    if (graph.verticies[i][k].weight + graph.verticies[k][j].weight < graph.verticies[i][j].weight) {
                        graph.verticies[i][j].weight = graph.verticies[i][k].weight + graph.verticies[k][j].weight;
                    }
                }
            }
            
           if(read_file) 
          Print();
        }
    }
    
            //                            ____________________________________________
            //                           |                                            |
            //---------------------------|       print the matrix of algorithm        |---------------------------------------------
            //                           |____________________________________________|

    /**
     *
     */
    
     public void Print() {
 //------ Print Header ----------------
        System.out.print("   ");

      
        System.out.println(); 
              System.out.println();
              for (int i = 0; i < graph.veticesNo; i++) {
         
            for (int j = 0; j < graph.veticesNo; j++) {
               if (i == j) {
                    System.out.print(" 0\t");

               }
               else if (graph.verticies[i][j] != null && graph.verticies[i][j].weight != ShortestPathAlgorithm.INF)
                {
                    
                    System.out.print(" "+graph.verticies[i][j].weight + "\t");
                }
                else
                {
                    System.out.print("INF\t");
                }
            }
            System.out.println("");
        }
        System.out.println("------------------------------------------------------------------------------");
    
     }
}


