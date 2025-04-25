
public class Test {
    public static void main(String[] args) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(".temp.txt", false)); 
            writer.write("test");     
            writer.close();      
        } catch (Exception e) {
            System.err.print(e.getMessage());
        }
    } 
}
