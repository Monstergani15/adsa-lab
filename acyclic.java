import java.util.*;

public class acyclic<V> {
    private Map<V, List<V>> neighbors = new HashMap<V, List<V>>();

    public String toString() {
        StringBuffer s = new StringBuffer();
        for (V v : neighbors.keySet())
            s.append("\n " + v + " -> " + neighbors.get(v));
        return s.toString();
    }

    public void add(V vertex) {
        if (neighbors.containsKey(vertex))
            return;
        neighbors.put(vertex, new ArrayList<V>());
    }

    public boolean contains(V vertex) {
        return neighbors.containsKey(vertex);
    }

    public void add(V from, V to) {
        this.add(from);
        this.add(to);
        neighbors.get(from).add(to);
    }

    public void remove(V from, V to) {
        if (!(this.contains(from) && this.contains(to)))
            throw new IllegalArgumentException("Nonexistent vertex");
        neighbors.get(from).remove(to);
    }

    public Map<V, Integer> outDegree() {
        Map<V, Integer> result = new HashMap<V, Integer>();
        for (V v : neighbors.keySet())
            result.put(v, neighbors.get(v).size());
        return result;
    }

    public Map<V, Integer> inDegree() {
        Map<V, Integer> result = new HashMap<V, Integer>();
        for (V v : neighbors.keySet())
            result.put(v, 0);
        for (V from : neighbors.keySet()) {
            for (V to : neighbors.get(from)) {
                result.put(to, result.get(to) + 1);
            }
        }
        return result;
    }

    public List<V> topSort() {
        Map<V, Integer> degree = inDegree();
        Stack<V> zeroVerts = new Stack<V>();
        for (V v : degree.keySet()) {
            if (degree.get(v) == 0)
                zeroVerts.push(v);
        }
        List<V> result = new ArrayList<V>();
        while (!zeroVerts.isEmpty()) {
            V v = zeroVerts.pop();
            for (V neighbor : neighbors.get(v)) {
                degree.put(neighbor, degree.get(neighbor) - 1);
                if (degree.get(neighbor) == 0)
                    zeroVerts.push(neighbor);
            }
        }
        if (result.size() != neighbors.size())
            return null;
        return result;
    }

    public boolean isDag() {
        return topSort() != null;
    }

    public Map bfsDistance(V start) {
        Map<V, Integer> distance = new HashMap<V, Integer>();
        for (V v : neighbors.keySet())
            distance.put(v, null);
        distance.put(start, 0);
        Queue<V> queue = new LinkedList<V>();
        queue.offer(start);
        while (!queue.isEmpty()) {
            V v = queue.remove();
            int vDist = distance.get(v);
            for (V neighbor : neighbors.get(v)) {
                if (distance.get(neighbor) != null)
                    continue;
                distance.put(neighbor, vDist + 1);
                queue.offer(neighbor);
            }
        }
        return distance;
    }

    public static void main(String[] args) {
        acyclic<Integer> graph = new acyclic<Integer>();
        graph.add(0, 1);
        graph.add(0, 2);
        graph.add(0, 3);
        graph.add(1, 2);
        graph.add(1, 3);
        graph.add(2, 3);
        graph.add(2, 4);
        graph.add(4, 5);
        graph.add(5, 6);
        System.out.println("The current graph: " + graph);
        System.out.println("In-degrees: " + graph.inDegree());
        System.out.println("Out-degrees: " + graph.outDegree());
        System.out.println("A topological sort of the vertices: " + graph.topSort());
        System.out.println("The graph " + (graph.isDag() ? "is" : "is not") + " a dag");
        System.out.println("BFS distances starting from " + 0 + ": " + graph.bfsDistance(0));
        System.out.println("BFS distances starting from " + 1 + ": " + graph.bfsDistance(1));
        System.out.println("BFS distances starting from " + 2 + ": " + graph.bfsDistance(2));
        System.out.println("Cycle created");
        System.out.println("The current graph: " + graph);
        System.out.println("A topological sort of the vertices: " + graph.topSort());
        System.out.println("The graph " + (graph.isDag() ? "is" : "is not") + " a dag");
        System.out.println("BFS distances starting from " + 2 + ": " + graph.bfsDistance(2));
    }
}