class Solution {
    
    private String getNumStr2(char c1, int num) {
        String str = "";
        for (int i = 1; i <= num; i++) {
            str = str + c1;
        }
        return str;
    }
    
    private String getNumStr(char c1, char c2, char c3, int num) {
        String str = "";
        if (num <= 3) {
            for (int i = 1; i <= num; i++) {
                str = str + c1;
            }
        } else if (num == 4) {
            str = str + c1 + c2;
        } else if (num <= 8) {
            str = str + c2;
            for (int i = 6; i <= num; i++) {
                str = str + c1;
            }
        } else {
            str = str + c1 + c3;
        }
        return str;
    }
    
    public String intToRoman(int num) {
        
        return getNumStr2('M', num / 1000) + getNumStr('C', 'D', 'M', (num = num % 1000) / 100)
            + getNumStr('X', 'L', 'C', (num = num % 100) / 10)
            + getNumStr('I', 'V', 'X', num % 10);
    }
}