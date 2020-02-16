class TweetCounts {
    
    Map<String, List<Integer>> queueMap; 
    static Map<String, Integer> timeMap;
    
    static {
        timeMap = new HashMap<>();
        timeMap.put("minute", 60);
        timeMap.put("hour", 3600);
        timeMap.put("day", 86400);
    }
    
    public TweetCounts() {
        queueMap = new HashMap<>(); 
    }
    
    public void recordTweet(String tweetName, int time) {
        List<Integer> list = queueMap.get(tweetName);
        if (list == null) {
            list = new ArrayList<>();
            queueMap.put(tweetName, list);
        }
        insert(list, time);
    }
    
    private void insert(List<Integer> list, int val) {
        if (list.size() == 0) {
            list.add(val);
            return;
        }
        int start = 0;
        int end = list.size() - 1;
        if (list.get(start) >= val) {
            list.add(start, val);
            return;
        } 
        if (list.get(end) <= val) {
            list.add(val);
            return;
        }
        while (start < end) {
            int middle = (start + end) / 2;
            if (list.get(middle) == val) {
                list.add(middle, val);
                return;
            }
            if (list.get(middle) < val) {
                start = middle + 1;
            } else {
                end = middle - 1;
            }
        }
        if (list.get(start) >= val) {
            list.add(start, val);
        } else {
            list.add(start + 1, val);
        }
    }
    
    public List<Integer> getTweetCountsPerFrequency(String freq, String tweetName, int startTime, int endTime) {
        List<Integer> list = queueMap.get(tweetName);
        if (list == null) {
            list = new ArrayList<>();
        }
        
        int timelen = timeMap.get(freq);
        List<Integer> result = new ArrayList<>();
        for (int i = startTime; i <= endTime; i += timelen) {
            int end = Math.min(endTime + 1, i + timelen);
            result.add(count(list, i, end));            
        }
        if (result.size() == 0) {
            return null;
        }
        return result;
    }
    
    private int count(List<Integer> list, int start, int end) {
        if (list.size() == 0) {
            return 0;
        }
        end = findIndex(list, end);
        start = findIndex(list, start);
        return end - start;
    }
    
    private int findIndex(List<Integer> list, int val) {
        int start = 0;
        int end = list.size() - 1;
        if (list.get(start) >= val) {
            return 0;
        }
        if (list.get(end) < val) {
            return list.size();
        }
        
        int index = -1;
        while (start < end) {
            int middle = (start + end) / 2;
            if (list.get(middle) == val) {
                index = middle;
                break;
            }
            if (list.get(middle) < val) {
                start = middle + 1;
            } else {
                end = middle - 1;
            }
        }
        
        if (index == -1) {
            if (list.get(start) == val) {
                index = start;
            } else if (list.get(start) > val) {
                return start;
            } else {
                return start + 1;
            }
        }
        
        int cur = list.get(index);
        while (index > 0) {
            if (list.get(index - 1) != cur) {
                break;
            }
            index--;
        }
        return index;
    }
}

/**
 * Your TweetCounts object will be instantiated and called as such:
 * TweetCounts obj = new TweetCounts();
 * obj.recordTweet(tweetName,time);
 * List<Integer> param_2 = obj.getTweetCountsPerFrequency(freq,tweetName,startTime,endTime);
 */