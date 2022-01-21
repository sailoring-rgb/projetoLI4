import java.util.Map;

public class IgnoreThisClass {
    public static void main(String[] args) {
        try{
            ConnectionDB c = new ConnectionDB();
            Map<String, User> us = c.loadUsers();
            User tmp = us.get("1");
            tmp.setPassword("user");
            us.put("1",tmp);
            c.saveUsers(us);
            Map<String, User> afterSave = c.loadUsers();
            for(User u : afterSave.values()){
                System.out.println(u.getPassword());
            }
        }  catch (Exception e){
            System.out.println(e);
        }
    }
}

