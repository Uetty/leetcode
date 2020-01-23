class Solution {
    
    public List<String> removeSubfolders(String[] folder) {
        Arrays.sort(folder);
        Set<String> set = new HashSet<>();
        for (int i = 0; i < folder.length; i++) {
            String str = folder[i];
            boolean contain = false;
            for (int j = 1; j < str.length(); j++) {
                if (str.charAt(j) == '/' && set.contains(str.substring(0, j))) {
                    contain = true;
                    break;
                }
            }
            if (!contain) {
                set.add(str);
            }
        }
        return new ArrayList<>(set);
    }
}