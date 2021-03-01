import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class UserTimelineTest {

    @Test
    public void publishTest() {
        UserTimeline userTimeline = new UserTimeline();

        KataUser alice = new KataUser();
        alice.setUserName("Alice");
        UserMessage aliceMessage = new UserMessage("I love the weather today.", alice);

        userTimeline.publish(alice.getUserId(), aliceMessage);

        Map<String, List<UserMessage>> map = userTimeline.getUserTimeline();

        Assert.assertTrue(map.containsKey(alice.getUserId()));
        List<UserMessage> userMessageList = userTimeline.getUserTimelineAsProfile(alice.getUserId());
        Assert.assertEquals(userMessageList.get(0).getMessageContent(), aliceMessage.getMessageContent());
    }

    @Test
    public void getAggregatedTimelineTestOne() {

        // Timeline 1
        UserTimeline userTimeline = new UserTimeline();

        KataUser alice = new KataUser();
        alice.setUserName("Alice");

        // bob
        KataUser bob = new KataUser();
        bob.setUserName("Bob");

        // charlie
        KataUser charlie = new KataUser();
        charlie.setUserName("Charlie");
        charlie.addFollower(alice.getUserId());
        charlie.addFollower(bob.getUserId());

        String aliceContent = "I love the weather today.";
        UserMessage aliceMessage = new UserMessage(aliceContent, alice);
        userTimeline.publish(alice.getUserId(), aliceMessage);
        sleep(1000);

        String bobContent = "Good game though.";
        UserMessage bobMessage = new UserMessage(bobContent, bob);
        userTimeline.publish(bob.getUserId(), bobMessage);
        sleep(1000);

        String bobContentTwo = "Darn! We lost!";
        UserMessage bobMessageTwo = new UserMessage(bobContentTwo, bob);
        userTimeline.publish(bob.getUserId(), bobMessageTwo);
        sleep(1000);

        String charlieContent = "I'm in New York today! Anyone wants to have a coffee?";
        UserMessage charlieMessage = new UserMessage(charlieContent, charlie);
        userTimeline.publish(charlie.getUserId(), charlieMessage);
        sleep(1000);

        Map<String, List<UserMessage>> map = userTimeline.getUserTimeline();
        List<String> userMessageList = userTimeline.getAggregatedTimeline(Arrays.asList(alice.getUserId(), bob.getUserId(), charlie.getUserId()));
        Assert.assertTrue(userMessageList.get(0).contains(charlieContent));
        Assert.assertTrue(userMessageList.get(1).contains(bobContentTwo));
        Assert.assertTrue(userMessageList.get(2).contains(bobContent));
        Assert.assertTrue(userMessageList.get(3).contains(aliceContent));
    }

    private void sleep(long millis) {
        try
        {
            Thread.sleep(millis);
        }
        catch(InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }
    }

    @Test
    public void getAggregatedTimelineTestTwo() {

        // Timeline 2
        UserTimeline userTimeline = new UserTimeline();

        KataUser alice = new KataUser();
        alice.setUserName("Alice");

        // bob
        KataUser bob = new KataUser();
        bob.setUserName("Bob");

        // charlie
        KataUser charlie = new KataUser();
        charlie.setUserName("Charlie");
        charlie.addFollower(alice.getUserId());
        charlie.addFollower(bob.getUserId());

        String bobContent = "Good game though.";
        UserMessage bobMessage = new UserMessage(bobContent, bob);
        userTimeline.publish(bob.getUserId(), bobMessage);
        sleep(1000);

        String aliceContent = "I love the weather today.";
        UserMessage aliceMessage = new UserMessage(aliceContent, alice);
        userTimeline.publish(alice.getUserId(), aliceMessage);
        sleep(1000);

        String charlieContent = "I'm in New York today! Anyone wants to have a coffee?";
        UserMessage charlieMessage = new UserMessage(charlieContent, charlie);
        userTimeline.publish(charlie.getUserId(), charlieMessage);
        sleep(1000);

        String bobContentTwo = "Darn! We lost!";
        UserMessage bobMessageTwo = new UserMessage(bobContentTwo, bob);
        userTimeline.publish(bob.getUserId(), bobMessageTwo);
        sleep(1000);

        Map<String, List<UserMessage>> map = userTimeline.getUserTimeline();
        List<String> followers = new ArrayList<>(charlie.getFollowers());
        List<String> userMessageList = userTimeline.getAggregatedTimeline(followers);
        Assert.assertTrue(userMessageList.get(3).contains(bobContentTwo));
        Assert.assertTrue(userMessageList.get(2).contains(charlieContent));
        Assert.assertTrue(userMessageList.get(1).contains(aliceContent));
        Assert.assertTrue(userMessageList.get(0).contains(bobContent));
    }

    @Test
    public void getUserTimelineAsProfileTest() {
        // Timeline 3
        UserTimeline userTimeline = new UserTimeline();

        KataUser alice = new KataUser();
        alice.setUserName("Alice");

        String aliceContent = "I love the weather today.";
        UserMessage aliceMessage = new UserMessage(aliceContent, alice);
        userTimeline.publish(alice.getUserId(), aliceMessage);

        String aliceContentTwo = "Alice test msg";
        UserMessage aliceMessageTwo = new UserMessage(aliceContentTwo, alice);
        userTimeline.publish(alice.getUserId(), aliceMessageTwo);

        List<UserMessage> list = userTimeline.getUserTimelineAsProfile(alice.getUserId());
        Assert.assertEquals(list.get(0).getMessageContent(), "Alice test msg");
        Assert.assertEquals(list.get(1).getMessageContent(), "I love the weather today.");
    }

    @Test
    public void getUserTimelineTest() {
        // Timeline 4
        UserTimeline userTimeline = new UserTimeline();

        KataUser alice = new KataUser();
        alice.setUserName("Alice");

        // bob
        KataUser bob = new KataUser();
        bob.setUserName("Bob");

        // charlie
        KataUser charlie = new KataUser();
        charlie.setUserName("Charlie");
        charlie.addFollower(alice.getUserId());
        charlie.addFollower(bob.getUserId());

        String bobContent = "Good game though.";
        UserMessage bobMessage = new UserMessage(bobContent, bob);
        userTimeline.publish(bob.getUserId(), bobMessage);
        sleep(1000);

        String aliceContent = "I love the weather today.";
        UserMessage aliceMessage = new UserMessage(aliceContent, alice);
        userTimeline.publish(alice.getUserId(), aliceMessage);
        sleep(1000);

        String charlieContent = "I'm in New York today! Anyone wants to have a coffee?";
        UserMessage charlieMessage = new UserMessage(charlieContent, charlie);
        userTimeline.publish(charlie.getUserId(), charlieMessage);
        sleep(1000);

        String bobContentTwo = "Darn! We lost!";
        UserMessage bobMessageTwo = new UserMessage(bobContentTwo, bob);
        userTimeline.publish(bob.getUserId(), bobMessageTwo);
        sleep(1000);

        List<String> res = userTimeline.getUserTimeline(charlie.getUserId());
        Assert.assertEquals(res.size(), 1);
        Assert.assertTrue(res.get(0).contains(charlieContent));
    }
}
