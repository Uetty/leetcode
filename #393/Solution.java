class Solution {
    public boolean validUtf8(int[] data) {
        for (int i = 0; i < data.length; i++) {
            int lowerNum = checkUpper(data[i]);
            if (lowerNum == -1) {
                return false;
            } else if (lowerNum > 0) {
                if (data.length - i <= lowerNum) {
                    return false;
                }
                for (int j = 0; j < lowerNum; j++) {
                    if (!checkLower(data[++i])) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private int checkUpper(int num) {
        if ((num & 0x80) == 0) {
            return 0;
        }
        int c = 0;
        do {
            if (((num >>> (8 - c - 2)) & 1) == 0) {
                break;
            }
            c++;
        } while (c <= 3);
        if (c == 0 || c > 3) {
            return -1;
        }
        return c;
    }

    private boolean checkLower(int num) {
        return 0x80 == (num & 0xC0);
    }
}