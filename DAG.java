import java.util.*;

public class DAG {
	private Map<Commit, List<Commit>> adjacencyList;
	private Set<String> vertices;
	private UUID id;

	public DAG(){
		this.adjacencyList = new HashMap<>();
		this.vertices = new HashSet<>();
		this.id = UUID.randomUUID();
	}
	public void addVertex(Commit vertex){
		vertices.add(vertex);
		adjacencyList.putIfAbsent(vertex, new ArrayList<>());

	}
	public void addEdge(Commit source, Commit destination){
		if (vertices.contains(source) && vertices.contains(destination) && !hasPath(destination, source)){
			adjacencyList.get(source).add(destination);
		}else{
			throw new RuntimeException("Cannot add edge, it would create a cycle")
		}
	}
	private boolean hasPath(Commit source, Commit destination){
		Set<String> visited = new HashSet<>();	
		return dfs(source, destination, visited);
	}
	private boolean dfs(Commit current, Commit destination, Set<Commit> visited){
		if (current.equals(destination)){
			return true;
		}
		visited.add(current);
		for (Commit neighbour: adjacencyList.get(current)){
			if (!visited.contains(neighbour) && dfs(neighbour, destination, visited)){return true;}
		}return false;
	}
}


