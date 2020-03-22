class Solution {
    public int maxSizeSlices(int[] slices) {
        int[] dp1 = new int[slices.length - 1];
        int[] dp2 = new int[slices.length - 1];
        for (int i = 0; i < dp1.length; i++) {
            dp1[i] = slices[i];
            dp2[i] = slices[i + 1];
        }

        int n = slices.length / 3;
        for (int i = 1; i < n; i++) {
            int[] dpp = new int[slices.length - 1];
            dpp[0] = slices[0];
            dpp[1] = slices[1];
            int max = dpp[0];
            for (int j = 2; j < dpp.length; j++) {
                dpp[j] = max + slices[j];
                max = Math.max(max, dp1[j - 1]);
            }
            dp1 = dpp;

            dpp = new int[slices.length - 1];
            dpp[0] = slices[1];
            dpp[1] = slices[2];
            max = dpp[0];
            for (int j = 2; j < dpp.length; j++) {
                dpp[j] = max + slices[j + 1];
                max = Math.max(max, dp2[j - 1]);
            }
            dp2 = dpp;
        }

        int max = Math.max(dp1[0], dp2[0]);
        for (int i = 0; i < dp1.length; i++) {
            max = Math.max(max, dp1[i]);
            max = Math.max(max, dp2[i]);
        }

        return max;
    }

}