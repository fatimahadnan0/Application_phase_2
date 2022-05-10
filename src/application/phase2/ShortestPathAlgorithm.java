
package application.phase2;

abstract class ShortestPathAlgorithm extends  ApplicationPhase2{
    Graph graph; // to inhert all attribute from graph class 
    final static int INF = 999999; // infinit number 

    public ShortestPathAlgorithm(Graph graph) {
        this.graph = graph;
    }    
}
