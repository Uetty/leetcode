/**
 * 还有更优方案，见solution2
 */
class Solution1 {
    public int numTimesAllBlue(int[] light) {
        int count = 0;
        int len = light.length / 32 + (light.length % 32 == 0 ? 0 : 1);
        int[] status = new int[len];
        Arrays.fill(status, -1);
        int max = 0;
        int checked = 0;

        for (int i = 0; i < light.length; i++) {
            int pos = (light[i] - 1) % 32;
            int sec = (light[i] - 1) / 32;
            max = Math.max(max, light[i]);
            
            status[sec] &= ~(1 << pos);
            
            checked = check(status, checked, max);
            if (checked == max) {
                count++;
            }
        }
        return count;
    }
    
    private int check(int[] status, int checked, int max) {
        int maxPos = (max - 1) % 32;
        int maxSec = (max - 1) / 32;
        int i = checked / 32;
        for (; i < maxSec; i++) {
            if (status[i] != 0) {
                return i * 32;
            }
        }

        if (maxPos == 31) {
            return status[maxSec] == 0 ? max : i * 32;
        }
        int mask = (1 << (maxPos + 1)) - 1;
        return (mask & status[maxSec]) == 0 ? max : i * 32;
    }
}