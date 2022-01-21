public class IgnoreThisClass {
    public static void main(String[] args) {
        try{
            ConnectionDB c = new ConnectionDB();
            System.out.println(c.printSelectUsers(c.queryUsers("SELECT")));
        }  catch (Exception e){
            System.out.println(e);
        }
    }
}

