class Solution {
    
    public class Serie {
        int len;
        boolean[] roads;
        boolean self = false;
        boolean has = false;
    }
    
    public int maxLength(List<String> arr) {
        Serie[] matrix = new Serie[arr.size()];
        int len = arr.size();
        
        
        outloop: 
        for (int i = 0; i < len; i++) {
            String str1 = arr.get(i);
            matrix[i] = new Serie();
            matrix[i].len = str1.length();
            matrix[i].roads = new boolean[len];
            
            
            boolean[] words = new boolean[26];
            for (int k = 0; k < str1.length(); k++) {
                int c = str1.charAt(k) - 'a';
                if (words[c]) {
                    continue outloop;
                }
                words[c] = true;
            }
            matrix[i].self = true;
            
            for (int j = i + 1; j < len; j++) {
                matrix[i].roads[j] = canCombine(Arrays.copyOf(words, words.length), arr.get(j));
                if (matrix[i].roads[j]) {
                    matrix[i].has = true;
                }
            }
        }
        
        arr = null;
        List<Serie> combines = new ArrayList<>();
        int maxLen = 0;
        
        int i = 0;
        for (; i < matrix.length; i++) {
            if (matrix[i].self) {
                if (matrix[i].len > maxLen) {
                    maxLen = matrix[i].len;
                }
                if (matrix[i].has) {
                    combines.add(matrix[i]);
                    i++;
                    break;
                }
            }
        }
        
        
        for (; i < matrix.length; i++) {
            int lsize = combines.size();
            Serie sei = matrix[i];
            if (!sei.self) {
                continue;
            }
            
            if (sei.len > maxLen) {
                maxLen = sei.len;
            }
            combines.add(sei);
            
            for (int j = 0; j < lsize; j++) {
                Serie sej = combines.get(j);

                if (sej.roads[i]) {
                    boolean[] roads = new boolean[len];
                    boolean has = false;
                    for (int k = 0; k < len; k++) {
                        roads[k] = sei.roads[k] && sej.roads[k];
                        if (roads[k]) {
                            has = true;
                        }
                    }
                    int nlen = sej.len + sei.len;
                    if (nlen > maxLen) {
                        maxLen = nlen;
                    }
                    if (has) {
                        Serie nse = new Serie();
                        nse.len = nlen;
                        nse.roads = roads;
                        combines.add(nse);
                    }
                }
            }
            
        }
        
        return maxLen;
    }
    
    private boolean canCombine(boolean[] words, String str) {
        for (int i = 0; i < str.length(); i++) {
            int c = str.charAt(i) - 'a';
            if (words[c]) {
                return false;
            }
            words[c] = true;
        }
        return true;
    }
}