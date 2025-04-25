import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Node {
	private Blob value;
	private List<Node> children;

	public Node(Blob value){
		this.value = value;
		this.children = new ArrayList<>();
	}

	public void addChild(Node child){
		children.add(child);
	}
	public Blob getBlob(){
		return value;
	}
	public List<Node> getChildren(){
		return children;
	}
}
