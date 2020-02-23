class Solution {
    
    int[] m = {31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334, 365};
    
    public int daysBetweenDates(String date1, String date2) {
        return Math.abs(getDate(date1) - getDate(date2));
    }
    
    
    private boolean isLeap(int year) {
        return year % 400 == 0 || (year % 100 != 0 && year % 4 == 0);
    }
    
    private int getDate(String date) {
        int year = 0, month = 0, day = 0;
        for (int i = 0; i < 4; i++) {
            year = year * 10 + (date.charAt(i) - '0');
        }
        month = (date.charAt(5) - '0') * 10 + date.charAt(6) - '0';
        day = (date.charAt(8) - '0') * 10 + date.charAt(9) - '0';
        
        int cdate = 0;
        for (int i = 1970; i < year; i++) {
            cdate += isLeap(i) ? 366 : 365;
        }
        month = month - 1;
        if (month > 0) {
            cdate += m[month - 1];
        }
        if (month > 1 && isLeap(year)) {
            cdate += 1;
        }
        cdate += day;

        return cdate;
    }
}