
package application.phase2;



public class AllSourceSPAlg extends ShortestPathAlgorithm {

    public AllSourceSPAlg(Graph graph) {
        super(graph); // we use super to call the graph calss to inhert all of the data in this class1
       
    }

    public void computeDijkstraAlg(boolean read_file) {
        //distence used to store the distance of vertex from source
        Vertex distance[] = new Vertex[graph.veticesNo];  
            
     
        Boolean visited[] = new Boolean[graph.veticesNo]; 

        int[] parent = new int[graph.veticesNo]; 
        
        //make all the vertices unvisited and distance as infinty
        for (int i = 0; i < graph.veticesNo; i++) {
            visited[i] = false;
            distance[i] = new Vertex(INF);
            
        }

        // Distance of source vertex from itself is always 0
       distance[0].weight = 0;

        // Find shortest path for all vertices
        for (int i = 0; i < graph.veticesNo - 1; i++) {
            // Pick the minimum distance vertex from the all the vertices
          
           //update all the distance between neighbour vertex and source vertex
            int minemum = minDistance(distance, visited);

            parent[0] = -1;

            // Mark the picked vertex as processed
            visited[minemum] = true;

            // Update distance value of the adjacent vertices of the picked vertex.
            for (int vertex = 0; vertex < graph.veticesNo; vertex++) 
          {
           // Update distance[v] only if is not in visited, there is an edge from u to v, and total weight of path from source to v through u is smaller than current value of dist[v]
  
                if (graph.verticies[minemum][vertex] != null) {
                    if (visited[vertex]==false && graph.verticies[minemum][vertex].weight != 0 && distance[minemum].weight != INF 
                            && (distance[minemum].weight + graph.verticies[minemum][vertex].weight) < distance[vertex].weight) {

                        parent[vertex] = minemum;
                        distance[vertex].weight = distance[minemum].weight + graph.verticies[minemum][vertex].weight;
                    }
                }
            }
        }

        // print 
        if(read_file)
        print(distance, parent);
    }

    int minDistance(Vertex distance[], Boolean visited[]) {
        // Initialize min value
        int minWeight = INF;
        int minIndex = 0;

        for (int i = 0; i < graph.veticesNo; i++) {
            if (!visited[i] && distance[i].weight <= minWeight) {
                minWeight = distance[i].weight;
                minIndex = i;
            }
        }
        return minIndex;
    }

    void print(Vertex distance[], int[] parent) {
        for (int i = 0; i < graph.veticesNo; i++) {
            if(graph.read_file == true)
            {
                System.out.print("from  "+graph.labels.get(0) +"  to   "+graph.labels.get(i)+ " \t is  " + distance[i].weight + "      Path : ");
            }
            else
            {
                System.out.print("from  "+graph.labels.get(0) +"  to   "+graph.labels.get(i)+ " \t is  " + distance[i].weight + "      Path : ");
            }
            printPath(parent,i);
            System.out.println("");
        }
    }

    //                                    ____________________________________________
            //                           |                                            |
            //---------------------------|        print the path of algotihm          |---------------------------------------------
            //                           |____________________________________________|
    
    private void printPath(int[] parents, int current) {
        // recursive
        if (current == -1) {
            return;
        }
        printPath(parents,parents[current]);
        if(graph.read_file == true)
        {
            if(current!=0){
                System.out.print(">>");
            }
            System.out.print(graph.labels.get(current) );
        }
        else
        {
        System.out.print(current + " ");
        }
    }
}



    
  