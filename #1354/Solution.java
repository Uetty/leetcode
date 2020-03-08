class Solution {

    // 令k = 初始数组总和 - 1，每次相加可转化为k值相加来计算，则有如下约束成立
    // 数组中每次相加后的值m均满足如下k值公式： m = k * n + 1 （其中n为非负整数）

    // 基于上面公式，将结果集中的每一项转化为与k相关的值n(i)，将结果值排序后，得到值n(1), n(2), ..., n(k), n(k+1)
    // 则会有n(k+1) = (n(1) + ... + n(k) + 1) + 计算前的n(k+1)，由公式明显可知，n(k+1) 大于n(1)到n(k)
    // 不断将最大的值减去（其他值加1），直到它不是最大值为止，重新排序，重复前面步骤，最终能得到所有值均为0的数组，则输出true


    // 平衡二叉树，非严格排序，排序后父节点一定比子节点大，所以最大的节点是第0个，第二大的节点是第1个或第2个
    int[] tree;

    private int buildTree(int[] target) {
        int k = target.length - 1;
        int nsum = 0;
        tree = target;

        for (int i = 0; i < target.length; i++) {
            if ((target[i] - 1) % k != 0) {
                return -1;
            }
            int ni = (target[i] - 1) / k;
            tree[i] = ni;
            nsum += ni;
        }

        for (int i = (tree.length >>> 1) - 1; i >= 0; i--) {
            heapify(i);
        }
        return nsum;
    }

    private void heapify(int i) {
        int val = tree[i];
        int size = tree.length;
        int half = size >>> 1;
        while (i < half) {
            int max = (i << 1) + 1;
            if (max + 1 < size && tree[max] < tree[max + 1]) {
                max = max + 1;
            }
            if (val >= tree[max]) {
                break;
            }
            tree[i] = tree[max];
            i = max;
        }
        tree[i] = val;
    }

    public boolean isPossible(int[] target) {
        if (target.length == 1) {
            return target[0] == 1;
        }
        
        int nsum = buildTree(target);
        if (nsum == -1) {
            return false;
        }
        int size = tree.length;
        while (tree[0] != 0) {
            int val = tree[0];
            int prevVal = 2 < size && tree[2] > tree[1] ? tree[2] : tree[1];

            int orinalVal = val % (nsum - val + 1);
            if (orinalVal != 0 && orinalVal >= prevVal) {
                return false;
            }

            tree[0] = orinalVal;
            heapify(0);
            nsum -= val - orinalVal;
        }
        return true;
    }

}