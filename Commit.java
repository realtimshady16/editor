import java.util.UUID;

class Commit{	
	private Tree tree;
	private UUID id;

	public Commit(Tree tree){
		this.tree = tree;
		this.id = UUID.randomUUID();
	}
	public UUID getId(){
		return id;
	}

}
