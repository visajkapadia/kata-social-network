import java.util.*;

public class UserTimeline implements Timeline{

    private Map<String, List<UserMessage>> userTimeline;

    public UserTimeline() {
        userTimeline = new HashMap<>();
    }

    @Override
    public void publish(String userId, UserMessage message) {
        if(!userTimeline.containsKey(userId)) {
            userTimeline.put(userId, new ArrayList<>());
        }
        userTimeline.get(userId).add(message);
    }

    private List<UserMessage> sortUserMessagesByTimestampDesc(List<UserMessage> userMessages) {
        List<UserMessage> sortedUserMessages = new ArrayList<>();
        Queue<UserMessage> maxHeap = new PriorityQueue<>((a, b) -> (int) (b.getTimestamp() - a.getTimestamp()));
        for(UserMessage userMessage : userMessages) {
            maxHeap.offer(userMessage);
        }
        while(!maxHeap.isEmpty()) {
            sortedUserMessages.add(maxHeap.poll());
        }
        return sortedUserMessages;
    }

    @Override
    public List<String> getUserTimeline(String userId) {
        if(!userTimeline.containsKey(userId)) return null;
        List<String> timelineMessages = new ArrayList<>();
        List<UserMessage> userMessageList  = userTimeline.get(userId);
        Collections.sort(userMessageList, new Comparator<UserMessage>() {
            @Override
            public int compare(UserMessage o1, UserMessage o2) {
                return Long.compare(o2.getTimestamp(), o1.getTimestamp());
            }
        });
        for(UserMessage userMessage : userMessageList) {
            String timelineMessage = String.format("%s (%s)",
                    userMessage.getMessageContent(),
                    TimeAgo.toDuration(System.currentTimeMillis() - userMessage.getTimestamp()));
            timelineMessages.add(timelineMessage);
        }
        return timelineMessages;
    }

    @Override
    public List<UserMessage> getUserTimelineAsProfile(String userId) {
        if(!userTimeline.containsKey(userId)) return null;
        List<UserMessage> userMessageList = userTimeline.get(userId);
        Collections.reverse(userMessageList);
        return userMessageList;
    }

    @Override
    public List<String> getAggregatedTimeline(List<String> userIds) {
        List<UserMessage> userMessageList = new ArrayList<>();
        for(String uId : userIds) {
            if(userTimeline.containsKey(uId)) {
                for(UserMessage userMessage : userTimeline.get(uId)) {
                    userMessageList.add(userMessage);
                }
            }
        }
        Collections.sort(userMessageList, new Comparator<UserMessage>() {
            @Override
            public int compare(UserMessage o1, UserMessage o2) {
                return Long.compare(o2.getTimestamp(), o1.getTimestamp());
            }
        });
        List<String> timelineMessages = new ArrayList<>();

        for(UserMessage userMessage : userMessageList) {
            String timelineMessage = String.format("%s - %s (%s)",
                    userMessage.getUserName(),
                    userMessage.getMessageContent(),
                    TimeAgo.toDuration(System.currentTimeMillis() - userMessage.getTimestamp()));
            timelineMessages.add(timelineMessage);
        }
        return timelineMessages;
    }

    public Map<String, List<UserMessage>> getUserTimeline() {
        return userTimeline;
    }

}
