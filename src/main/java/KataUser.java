import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class KataUser implements User{

    private String userId;
    private String userName;
    private Set<String> followers;

    public KataUser() {
        this.userId = UUID.randomUUID().toString();
        followers = new HashSet<>();
        followers.add(this.userId);
    }

    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Set<String> getFollowers() {
        return followers;
    }

    public void setFollowers(Set<String> followers) {
        this.followers = followers;
    }

    @Override
    public void addFollower(String userId) {
        this.followers.add(userId);
    }
}
