class Solution {
    private static char[][] DICTS = {
        {'a', 'b', 'c'},
        {'d', 'e', 'f'},
        {'g', 'h', 'i'},
        {'j', 'k', 'l'},
        {'m', 'n', 'o'},
        {'p', 'q', 'r', 's'},
        {'t', 'u', 'v'},
        {'w', 'x', 'y', 'z'}
    };
    
    public List<String> letterCombinations(String digits) {
        List<String> list = new ArrayList<>();
        if (digits.length() == 0) {
            return list;
        }
        list.add("");
    
        for (int i = 0; i < digits.length(); i++) {
            int size = list.size();
            char c = digits.charAt(i);
            int index = c - '2';
            for (int j = 0; j < size; j++) {
                String str = list.remove(0);
                char[] arr = DICTS[index];
                for (int k = 0; k < arr.length; k++) {
                    list.add(str + arr[k]);
                }
            }
        }
        
        return list;
    }
}