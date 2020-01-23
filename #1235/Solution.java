class Solution {
    class Job implements Comparable<Job> {
            int start;
            int end;
            int profit;
            public Job(int start, int end, int profit) {
                this.start = start;
                this.end = end;
                this.profit = profit;
            }
            @Override
            public int compareTo(Job o) {
                int c = end - o.end;
                if (c != 0) {
                    return c;
                }
                return start - o.start;
            }
        }
        public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
            Job[] jobs = new Job[profit.length];
            for (int i = 0; i < profit.length; i++) {
                jobs[i] = new Job(startTime[i], endTime[i], profit[i]);
            }
            Arrays.sort(jobs);

            profit = new int[profit.length];
            profit[0] = jobs[0].profit;
            for (int i = 1; i < profit.length; i++) {
                int lastMaxSum = foundMaxProfitBeforeStart(jobs, jobs[i].start, profit, i - 1);
                profit[i] = Math.max(profit[i - 1], lastMaxSum + jobs[i].profit);
            }

            return profit[profit.length - 1];
        }

        private int foundMaxProfitBeforeStart(Job[] jobs, int time, int[] profit, int right) {
            if (jobs.length < 60) {
                return foundMaxProfitBeforeStart1(jobs, time, profit, right);
            } else {
                return foundMaxProfitBeforeStart2(jobs, time, profit, right);
            }
        }
    
        private int foundMaxProfitBeforeStart1(Job[] jobs, int time, int[] profit, int right) {
            for (int i = right; i >= 0; i--) {
                if (jobs[i].end <= time) {
                    return profit[i];
                }
            }
            return 0;
        }
    
        private int foundMaxProfitBeforeStart2(Job[] jobs, int time, int[] profit, int right) {
            int left = 0;
            while (left < right) {
                int mid = (left + right) / 2;

                if (jobs[mid].end > time) {
                    right = mid - 1;
                } else {
                    if (mid == right || jobs[mid + 1].end > time) {
                        return profit[mid];
                    } else {
                        left = mid + 1;
                    }
                }
            }
            // left 不会超出边界，所以从left出发计算比较方便
            if (jobs[left].end > time) {
                left = left - 1;
            }
            return left == -1 ? 0 : profit[left];
        }
}