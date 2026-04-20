import java.util.ArrayList;

public class Administrator extends User {

    public Administrator(String id, String username, String password) {
        super(id, username, password);
    }

    public void addMedia(Media m, ArrayList<Media>list){
        list.add(m);
    }

    public void removeMedia(Media m, ArrayList<Media>list){
        list.remove(m);
    }

    @Override
    public String toString() {
        return "Administrator - " + super.toString();
    }
}




