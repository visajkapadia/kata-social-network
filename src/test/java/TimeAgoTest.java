import org.junit.Assert;
import org.junit.Test;

public class TimeAgoTest {
    @Test
    public void toDurationTest() {
        Assert.assertEquals("0 seconds ago", TimeAgo.toDuration(10));
        Assert.assertEquals("1 second ago", TimeAgo.toDuration(1000));
        Assert.assertEquals("16 minutes ago", TimeAgo.toDuration(1000000));
        Assert.assertEquals("1 day ago", TimeAgo.toDuration(100000000));
        Assert.assertEquals("11 days ago", TimeAgo.toDuration(1000000000l));
        Assert.assertEquals("3 months ago", TimeAgo.toDuration(10000000000l));
        Assert.assertEquals("3 years ago", TimeAgo.toDuration(100000000000l));
    }
}
