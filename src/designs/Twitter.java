package designs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class Twitter {

    /** Initialize your data structure here. */
    
    private long time = 0;
    
    private class Tweet implements Comparable<Tweet> {
        int id;
        long timestamp ;
        
        public Tweet(int id, long timestamp)  {
            this.id = id;
            this.timestamp = timestamp;
        }
        
        @Override
        public int compareTo(Tweet t) {
            return (int)t.timestamp - (int)this.timestamp;
        }
        
    }
    
    private Map<Integer, Set<Integer>> followersMap; 
    private Map<Integer, LinkedList<Tweet>> userTweets; 
    
    public Twitter() {
        followersMap = new HashMap<>();
        userTweets = new HashMap<>();
    }
    
    /** Compose a new tweet. */
    public void postTweet(int userId, int tweetId) {
        if (!userTweets.containsKey(userId)) {
            userTweets.put(userId, new LinkedList<>());
        }
        LinkedList<Tweet> list = userTweets.get(userId) ;
        list.addFirst(new Tweet(tweetId, time++));
    }
    
    /** Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent. */
    public List<Integer> getNewsFeed(int userId) {
        PriorityQueue<Tweet> queue = new PriorityQueue<>();
        for (Integer followee : followersMap.getOrDefault(userId, new HashSet<>())) {
            List<Tweet> recentTweets = addMostRecentKTweets(followee, 10);
            queue.addAll(recentTweets);
        }
        
        List<Tweet> selfTweets = addMostRecentKTweets(userId, 10);
        queue.addAll(selfTweets);
        
        List<Integer> res = new ArrayList<>();
        int count = 0;
        while (!queue.isEmpty()) {
            if (count == 10)
                break;
            res.add(queue.poll().id);
            count++;
        }
        
        return res;
        
    }
    
    private List<Tweet> addMostRecentKTweets(int userId, int k) {
        
        List<Tweet> tweets = new ArrayList<>();

        for (Tweet t : userTweets.getOrDefault(userId, new LinkedList<>())) {
            if (tweets.size() == k)
                break;
            tweets.add(t);
        }
        
        return tweets;
    }
    
    /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
    public void follow(int followerId, int followeeId) {
        if (!followersMap.containsKey(followerId)) {
            followersMap.put(followerId, new HashSet<>());
        }
        if (followerId == followeeId)
            return; 
        
        Set<Integer> followers = followersMap.get(followerId);
        followers.add(followeeId);
    }
    
    /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
    public void unfollow(int followerId, int followeeId) {
        Set<Integer> followers = followersMap.getOrDefault(followerId, new HashSet<>());
        if (followers.contains(followeeId)) {
            followers.remove(followeeId);
        }
    }
}

/**
 * Your Twitter object will be instantiated and called as such:
 * Twitter obj = new Twitter();
 * obj.postTweet(userId,tweetId);
 * List<Integer> param_2 = obj.getNewsFeed(userId);
 * obj.follow(followerId,followeeId);
 * obj.unfollow(followerId,followeeId);
 */