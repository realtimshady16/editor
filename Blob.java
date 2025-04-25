import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Blob{
		private String contents;
		private UUID id;
		public String getContents(){return contents;}
		public UUID getID(){return id;}
		public Blob(String contents){
			this.contents = contents;
			this.id = UUID.randomUUID();
		}
		
		public void setContents(String contents){this.contents = contents;}
	}


