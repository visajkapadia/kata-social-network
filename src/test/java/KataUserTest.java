import org.junit.Assert;
import org.junit.Test;

public class KataUserTest {
    @Test
    public void addFollowerTest() {
        KataUser alice = new KataUser();
        Assert.assertTrue(!alice.getFollowers().isEmpty());
        KataUser bob = new KataUser();
        KataUser charlie = new KataUser();
        alice.addFollower(bob.getUserId());
        charlie.addFollower(alice.getUserId());
        charlie.addFollower(bob.getUserId());
        Assert.assertTrue(alice.getFollowers().contains(bob.getUserId()));
        Assert.assertTrue(charlie.getFollowers().contains(alice.getUserId()));
        Assert.assertTrue(charlie.getFollowers().contains(bob.getUserId()));
    }
}
