class Solution {
    String str;
    Node[] nodes;
    int[] starNodeIndexes;
    int cursor;
    int nodeCursor;
    
    class Node {
        protected char pattern;
        protected int matchIndex;
        
        public Node(char pattern) {
            this.pattern = pattern;
        }
        
        public boolean isMatch() {
            return str.charAt(matchIndex) == pattern;
        }
        public boolean isMatch(char c) {
            return c == pattern;
        }
    }
    
    class DotNode extends Node {
        public DotNode(char pattern) {
            super(pattern);
        }
        @Override
        public boolean isMatch() {
            return true;
        }
        @Override
        public boolean isMatch(char c) {
            return true;
        }
    }
    
    class StarNode extends Node {
        protected int matchLength;
        protected Node patternNode;
        public StarNode(char pattern) {
            super(pattern);
        }
        @Override
        public boolean isMatch() {
            int max = matchIndex + matchLength;
            for (int i = matchIndex; i < max; i++) {
                if (!patternNode.isMatch(str.charAt(matchIndex))) {
                    return false;
                }
            }
            return true;
        }
        public int maxMatchLength(int allowMax) {
            int i = 0;
            for (; i < allowMax; i++) {
                if (!patternNode.isMatch(str.charAt(matchIndex + i))) {
                    break;
                }
            }
            return i;
        }
    }
    
    private void compile(String p) {
        List<Node> list = new ArrayList<>();
        int starCount = 0;
        for (int i = 0; i < p.length(); i++) {
            char reg = p.charAt(i);
            Node node;
            if (p.charAt(i) == '.') {
                node = new DotNode(reg);
            } else {
                node = new Node(reg);
            }
            if (i < p.length() - 1 && p.charAt(i + 1) == '*') {
                StarNode snode = new StarNode('*');
                snode.patternNode = node;
                list.add(snode);
                i++;
                starCount++;
            } else {
                list.add(node);
            }
        }
        nodes = list.toArray(new Node[list.size()]);
        starNodeIndexes = new int[starCount];
        starCount = 0;
        for (int i = 0; i < nodes.length; i++) {
            if (nodes[i] instanceof StarNode) {
                starNodeIndexes[starCount++] = i;
            }
        }
    }
    
    public boolean isMatch(String s, String p) {
        if (s.length() == 0 && p.length() == 0) {
            return true;
        }
        if (s.length() > 0 && p.length() == 0) {
            return false;
        }
        str = s;
        
        compile(p);
        
        if (s.length() < nodes.length - starNodeIndexes.length
        || (starNodeIndexes.length == 0 && s.length() != nodes.length)) {
            return false;
        }
        
        nodeCursor = 0;
        cursor = 0;
        int starCount = 0;
        while (nodeCursor < nodes.length) {
            Node node = nodes[nodeCursor];
            if (node instanceof StarNode) {
                StarNode snode = (StarNode)node;
                snode.matchIndex = cursor;
                
                int restCharLength = s.length() - cursor;
                int minLength = starNodeIndexes.length - starCount > 1 ? 0 : restCharLength - (nodes.length - nodeCursor - 1);
                int maxLength = restCharLength - (nodes.length - nodeCursor) + starNodeIndexes.length - starCount;
                
                int length = snode.maxMatchLength(maxLength);
                
                if (length >= minLength) {
                    snode.matchLength = length;
                    starCount++;
                    nodeCursor++;
                    cursor += length;
                    continue;
                } else { // 进入后面的回退到上一个*的算法
                     snode.matchLength = 0;
                }
                
            } else {
                node.matchIndex = cursor;
                if (node.isMatch()) {
                    cursor++;
                    nodeCursor++;
                    continue;
                }
            }
            if (starCount > 0) {
                // 回退到'*'匹配的位置，并且把*匹配的个数减一
                while (starCount > 0) {
                    int iStarCount = starCount - 1;
                    int iNodeCursor = starNodeIndexes[iStarCount];
                    StarNode snode = (StarNode) nodes[iNodeCursor];
                    int iCursor = snode.matchIndex;
                    
                    int iRestCharLength = s.length() - iCursor + 1;
                    int minLength = starNodeIndexes.length - iStarCount > 1 ? 0 : iRestCharLength - (nodes.length - iNodeCursor - 1);
                    
                    if (snode.matchLength > minLength) {
                        snode.matchLength = snode.matchLength - 1;
                        nodeCursor = iNodeCursor + 1;
                        cursor = iCursor + snode.matchLength;
                        break;
                    } else {
                        starCount--;
                    }
                }
                if (starCount > 0) {
                    continue;
                }
            }
            return false;
        }
        return true;
    }
}