class Solution {
    public String largestMultipleOfThree(int[] digits) {
        int sum = 0;
        int[] map = new int[10];
        for (int i = 0; i < digits.length; i++) {
            sum += digits[i];
            map[digits[i]]++;
        }
        sum = sum % 3;

        balance(map, sum);

        StringBuilder str = new StringBuilder();
        for (int i = 9; i > 0; i--) {
            while (map[i] > 0) {
                str.append((char)('0' + i));
                map[i]--;
            }
        }
        
        if (str.length() == 0 && map[0] > 0) {
            return "0";
        }

        while (map[0] > 0) {
            str.append('0');
            map[0]--;
        }
        
        return str.toString();
    }

    private void balance(int[] map, int sum) {
        if (sum == 1) {
            for (int i = 1; i < map.length; i += 3) {
                if (map[i] > 0) {
                    map[i]--;
                    sum = 0;
                    break;
                }
            }

            if (sum == 0) {
                return;
            }

            for (int i = 2; i < map.length; i += 3) {
                while (map[i] > 0 && sum != 0) {
                    map[i]--;
                    sum = (sum + 1) % 3;
                }
                if (sum == 0) {
                    break;
                }
            }

        } else if (sum == 2) {
            for (int i = 2; i < map.length; i += 3) {
                if (map[i] > 0) {
                    map[i]--;
                    sum = 0;
                    break;
                }
            }

            if (sum == 0) {
                return;
            }

            for (int i = 1; i < map.length; i += 3) {
                while (map[i] > 0 && sum != 0) {
                    map[i]--;
                    sum -= 1;
                }
                if (sum == 0) {
                    break;
                }
            }
        }
    }
}