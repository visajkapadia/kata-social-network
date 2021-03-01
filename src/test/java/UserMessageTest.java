import org.junit.Assert;
import org.junit.Test;

public class UserMessageTest {

    @Test
    public void messageTimestampTest() {
        KataUser user = new KataUser();
        UserMessage userMessage = new UserMessage("msg", user);
        Assert.assertEquals(userMessage.getTimestamp(), System.currentTimeMillis());
        Assert.assertEquals(userMessage.getMessageContent(), "msg");
    }
}
