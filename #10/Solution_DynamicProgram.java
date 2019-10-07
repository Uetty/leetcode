class Solution {
    
    // 定义f(S(m1:m2), P(n1:n2))为字符串s从m1到m2的子串，是否与匹配串p从n1到n2的子串匹配
    // S(0:m1)缩写为S(m1),P(0:n1)缩写为P(n1)
    // 对于f(S(m+1), P(n+1))有如下结果：
    // 当P(n:n+1)为".*"时， f(S(m+1),P(n+1)) = f(S(m+1),P(n) || f(S(m),P(n))
    // 当P(n:n+1)为"."时，f(S(m+1),P(n+1)) = f(S(m),P(n))
    // 当P(n:n+1)为"a"时，f(S(m+1),P(n+1)) = f(S(m),P(n)) && f(S(m:m+1),P(n:n+1))
    // 当P(n:n+1)为"a*"时，f(S(m+1),P(n+1)) = f(S(m+1),P(n)) || f(S(m:m+1),P(n:n+1) && f(S(m),P(n+1))
    // 为了方便编码，重新整理下，可变形为如下结果：
    // 当P(n:n+1)包含"*"时，f(S(m+1),P(n+1)) = f(S(m+1),P(n)) || f(S(m:m+1),P(n:n+1) && f(S(m),P(n+1))
    // 当P(n:n+1)不包含"*"时，f(S(m+1),P(n+1)) = f(S(m:m+1),P(n:n+1)) && f(S(m),P(n))
    public boolean isMatch(String s, String p) {
        if (s.length() == 0 && p.length() == 0) {
            return true;
        }
        if (s.length() > 0 && p.length() == 0) {
            return false;
        }
        List<Node> patterns = compile(p);
        
        // 优化，去头去尾
        int trimHead = 0;
        for (int i = 0,j = 0; i < patterns.size(); i++,j++) {
            Node node1 = patterns.get(i);
            if (node1.containStar()) {
                break;
            } else if (j >= s.length() || !node1.isMatch(s.charAt(j))) {
                return false;
            } else {
                trimHead++;
                i--;
                patterns.remove(0);
            }
        }
        if (trimHead > 0) {
            s = s.substring(trimHead);
        }
        int trimTail = 0;
        for (int i = 0,j = 0; i < patterns.size(); i++,j++) {
            Node node1 = patterns.get(patterns.size() - 1 - i);
            if (node1.containStar()) {
                break;
            } else if (s.length() - 1 - j < 0 || !node1.isMatch(s.charAt(s.length() - 1 - j))) {
                return false;
            } else {
                trimTail++;
                i--;
                patterns.remove(patterns.size() - 1);
            }
        }
        if (trimTail > 0) {
            s = s.substring(0, s.length() - trimTail);
        }
        
        // 动态规划
        boolean[][] matrix = new boolean[patterns.size() + 1][];
        matrix[0] = new boolean[s.length() + 1];
        matrix[0][0] = true;
        for (int i = 1; i <= patterns.size(); i++) {
            matrix[i] = new boolean[s.length() + 1];
            Node cnode = patterns.get(i - 1);
            boolean containStar = cnode.containStar();
            matrix[i][0] = containStar && matrix[i - 1][0];
            for (int j = 1; j <= s.length(); j++) {
                if (containStar) {
                    matrix[i][j] = matrix[i - 1][j] || (cnode.isMatch(s.charAt(j - 1)) && matrix[i][j - 1]);
                } else {
                    matrix[i][j] = matrix[i - 1][j - 1] && cnode.isMatch(s.charAt(j - 1));
                }
            }
        }
        return matrix[patterns.size()][s.length()];
    }
    
    class Node {
        protected char pattern;
        protected boolean containStar;
        public Node(char pattern, boolean containStar) {
            this.pattern = pattern;
            this.containStar = containStar;
        }
        public boolean isMatch(char c) {
            return c == pattern;
        }
        public boolean containStar() {
            return this.containStar;
        }
    }
    
    class DotNode extends Node {
        public DotNode(char pattern, boolean containStar) {
            super(pattern, containStar);
        }
        public boolean isMatch(char c) {
            return true;
        }
    }
    
    
    private List<Node> compile(String p) {
        List<Node> list = new ArrayList<>();
        for (int i = 0; i < p.length(); i++) {
            char reg = p.charAt(i);
            Node node;
            boolean containStar = false;
            if (i < p.length() - 1 && p.charAt(i + 1) == '*') {
                containStar = true;
                i++;
            }
            if (reg == '.') {
                node = new DotNode(reg, containStar);
            } else {
                node = new Node(reg, containStar);
            }
            list.add(node);
        }
        return list;
    }
}