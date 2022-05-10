package application.phase2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
import java.util.Vector;

public class Graph {

    // the attributes we have to use it in this class
    Vertex[][] verticies;
    int veticesNo; // the number of vertex
    int edgeNo; //the number of edges
    boolean isDigraph;  // check if is it Directed graph or not
    Vector<String> labels;  // name of vertex example: name of cities ....
    boolean read_file; //check if is it read from file or not

    //contractor with no argument 
    public Graph() {
        read_file = false;
        labels = new Vector<String>();
    }

    // contractor with argument
    public Graph(boolean isDigraph) {
        read_file = false;
        this.isDigraph = isDigraph;
        labels = new Vector<String>();
    }

    public Graph(int veticesNo, int edgeNo, boolean isDigraph) {
        this.isDigraph = isDigraph;
        this.edgeNo = edgeNo;
        this.veticesNo = veticesNo;
        read_file = false;
        this.labels = new Vector<String>();
        this.verticies = new Vertex[veticesNo][veticesNo];

    }

    public void makeGraph() {
        //random class
        Random random = new Random(); //generate random number

        // we use this for loop to drow the edges and add it and check that all vertices are connectet
        for (int i = 0; i < veticesNo - 1; i++) {
            int w = random.nextInt(30) + 1;
            addEdge(i, i + 1, w);
            /* add edge take source and target and weight ,we 
            use i+1 becouse the target cant be same place or stack on same vertex */
        }

//remaning edge
        //check for remaning edge to drow the edges if there is not have it 
        //if is it not have edge , will add edge with sourse and target and wight
        int remaining = edgeNo - (veticesNo - 1); //
        for (int i = 0; i < remaining; i++) {
            int source = random.nextInt(veticesNo);
            int target = random.nextInt(veticesNo);

            // create random weight
            int weight = random.nextInt(30) + 1;
            if ((target == source)) {
                i--; // if the edge was exiest will decrement 1 to not repeate it again to avoid infinite loop
            } else {
                addEdge(source, target, weight);
            }
        }
    }

    //                            ____________________________________________
    //                           |                                            |
    //---------------------------|          read graph from file              |---------------------------------------------
    //                           |____________________________________________|
    public void readGraphFromFile(String fileName) throws FileNotFoundException, IOException {
        read_file = true;
        String s;
        String t;
        int wight;

        //Create file object to input file
        File file = new File(fileName);
        //Create scanner object
        Scanner input = new Scanner(file);

        //read from file 'digraph"
        String type = input.next();
        //read from file "0 or 1"
        int istype = input.nextInt();
        //read from file number of vetices
        veticesNo = 10; // the number of vertticies from file 
        verticies = new Vertex[veticesNo][veticesNo]; //array 2D take row ands column , if the number of vertex mor than 10 ,it will not read from file 

        //check the file is empty or not 
        if (input.hasNextLine()) {
            if (istype == 0) {
                isDigraph = true;
            } else {
                isDigraph = false;
            }

            int source_pos, target_pos;
            while (input.hasNextLine()) {
                //read from file >>source 
                s = input.next();
                //read from file >>target
                t = input.next();
                //invoke method addVertlable to add the label if not exist
                //firstly will read source , target , weight  and then will store them to use get method and  addedge to connect with each pther and creat edge
                addVertLabel(s);
                addVertLabel(t);
                //read from file >>weight
                wight = input.nextInt();
                // invoke method getVertPos to check if label is exist or not 
                source_pos = getVertPos(s);
                target_pos = getVertPos(t);

                addEdge(source_pos, target_pos, wight);
            }
        }
        input.close();
    }

    //                            ____________________________________________
    //                           |                                            |
    //---------------------------|           add edge methodm                 |---------------------------------------------
    //                           |____________________________________________|
    /* we use this method to add edges betwoeen the source and destenation to 
      connect with them to help us to craete a graph */
    public void addEdge(int v, int u, int w) {
        verticies[v][u] = new Vertex(w);

        if (isDigraph) { // if there is digraph will take source and destination assign to the weight  
            verticies[u][v] = new Vertex(w);
        }

        if (read_file) {
            if (isDigraph) {
                edgeNo++;
            } else {
                edgeNo += 2;
            }
        }
    }

    //                            ____________________________________________
    //                           |                                            |
    //---------------------------|            get vertex position             |---------------------------------------------
    //                           |____________________________________________|
//to check if label is exist return its position otherwise retern -1 
    public int getVertPos(String v) {
        int position = -1;
        for (int i = 0; i < labels.size(); i++) {
            if (labels.get(i).equals(v)) {
                position = i;
                break;
            }
        }
        return position;
    }

    //                            ____________________________________________
    //                           |                                            |
    //---------------------------|         add the lable of vertex            |---------------------------------------------
    //                           |____________________________________________|
//to add the label if not exist otherwise return false
    public boolean addVertLabel(String vLabel) {
        if (!labels.contains(vLabel)) {
            labels.add(vLabel);
            return true;
        } else {
            return false;
        }

    }

}
