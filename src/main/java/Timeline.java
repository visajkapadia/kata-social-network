import java.util.List;

interface Timeline {
    public void publish(String userId, UserMessage message);
    public List<String> getUserTimeline(String userId);
    public List<UserMessage> getUserTimelineAsProfile(String userId);
    public List<String> getAggregatedTimeline(List<String> userIds);
}
