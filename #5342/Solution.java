class Solution {
    
    
    public int maxEvents(int[][] events) {
        if (events.length == 0) {
            return 0;
        }
        
        
        Arrays.sort(events, (o1, o2) -> {
            if (o1[0] >= o2[0] && o1[1] <= o2[1]) {
                return -1;
            }
            if (o1[0] <= o2[0] && o1[1] >= o2[1]) {
                return 1;
            }
            return o1[0] - o2[0];
		});
        int max = events[0][1];
        int min = events[0][0];
        for (int i = 0; i < events.length; i++) {
            max = Math.max(max, events[i][1]);
            min = Math.min(min, events[i][0]);
        }
        
            
        int c = 0;
        boolean[] scheduled = new boolean[max - min + 1];
        for (int i = 0; i < events.length; i++) {
            // System.out.println("s -> " + c + ", [" + events[i][0] + ", " + events[i][1] + "]");
            
            for (int j = events[i][0]; j <= events[i][1]; j++) {
                if (!scheduled[j - min]) {
                    scheduled[j - min] = true;
                    c++;
                    break;
                }
            }
            
        }
        // System.out.println("s -> " + scheduled + ", c -> " + c);
        return c;
    }
}