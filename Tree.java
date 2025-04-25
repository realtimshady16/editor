import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

class Node {
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


public class Tree{
	private UUID id;
	private Node root;
	private Tree rootTree;

	public UUID getId(){
		return id;
	}
	public Tree(Blob root, Tree rootTree){
		this.root = new Node(root);
		this.rootTree = new Tree();
		this.id = UUID.randomUUID();
	}
	public void setBlobRoot(Node root){
		this.root = root;
	}
	public void setTreeRoot(Tree rootTree){
		this.rootTree = rootTree;
	}
	public Tree getTreeRoot(){
		return rootTree;
	}
	public Node getBlobRoot(){
		return root;
	}
	public void addNode(Node parent, Node child){
		parent.addChild(child);
	}
}
