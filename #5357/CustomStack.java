class CustomStack {

    int[] stack;
    int size;
    
    public CustomStack(int maxSize) {
        stack = new int[maxSize];
    }
    
    public void push(int x) {
        
        if (size < stack.length) {
            stack[size++] = x;
        }
    }
    
    public int pop() {
        if (size > 0) {
            return stack[--size];
        }
        return -1;
    }
    
    public void increment(int k, int val) {
        k = Math.min(k, size);
        for (int i = 0; i < k; i++) {
            stack[i] += val;
        }
    }
}

/**
 * Your CustomStack object will be instantiated and called as such:
 * CustomStack obj = new CustomStack(maxSize);
 * obj.push(x);
 * int param_2 = obj.pop();
 * obj.increment(k,val);
 */