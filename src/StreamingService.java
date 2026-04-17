import java.util.ArrayList;

public class StreamingService {

    private ArrayList<Media>mediaList;
    private ArrayList<User>userList;

    public StreamingService(ArrayList<Media> mediaList, ArrayList<User> userList) {
        this.mediaList = mediaList;
        this.userList = userList;
    }

    public void startSystem(){

    }

    public void registerUser(){

    }

    public void loginUser(){

    }

    public void findMedia(){

    }

    public ArrayList<Media> getMediaList() {
        return mediaList;
    }

    public void setMediaList(ArrayList<Media> mediaList) {
        this.mediaList = mediaList;
    }

    public ArrayList<User> getUserList() {
        return userList;
    }

    public void setUserList(ArrayList<User> userList) {
        this.userList = userList;
    }
}
