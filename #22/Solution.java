class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> list = new ArrayList<>();
        char[] chars = new char[n * 2];

        dfs(list, chars, 0, 0);

        return list;
    }

    private void dfs(List<String> list, char[] chars, int size, int stack) {
        if (stack > 0) {
            chars[size++] = ')';
            stack--;
            if (size == chars.length) {
                list.add(new String(chars));
            } else {
                dfs(list, chars, size, stack);
            }
            size--;
            stack++;
        }

        if (size + stack < chars.length) {
            chars[size++] = '(';
            stack++;
            if (size == chars.length) {
                list.add(new String(chars));
            } else {
                dfs(list, chars, size, stack);
            }
        }
    }
}