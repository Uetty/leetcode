class Solution {
    public boolean isValid(String s) {
        if ((s.length() & 1) == 1) {
            return false;
        }
        char[] stack = new char[s.length()];
        int len = 0;
        
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            switch(c) {
                case '(':
                case '[':
                case '{':
                    stack[len++] = c;
                    break;
                case ')':
                    if (len == 0 || stack[--len] != '(') {
                        return false;
                    }
                    break;
                case ']':
                    if (len == 0 || stack[--len] != '[') {
                        return false;
                    }
                    break;
                case '}':
                    if (len == 0 || stack[--len] != '{') {
                        return false;
                    }
                    break;
            }
        }
        return len == 0;
    }
}