class Solution {
    public class Square {
        int left;
        int right;
        int top;
        int bottom;

        public Square(int left, int top, int sideLen) {
            this.left = left;
            this.top = top;
            this.right = left + sideLen - 1;
            this.bottom = top + sideLen - 1;
        }

        public boolean contains(int row, int col) {
            return row >= top && row <= bottom
                    && col >= left && col <= right;
        }
    }

    /**
     * 贪心策略辗转相除法得出上界
     */
    private int upperBound(int a, int b) {
        if (b > a) {
            b = b ^ a;
            a = b ^ a;
            b = b ^ a;
        }
        return a / b + (a % b != 0 ? upperBound(b, a % b) : 0);
    }

    public int tilingRectangle(int n, int m) {
        List<Square> list = new ArrayList<>();
        int[] currentMin = { upperBound(m, n) };
        return countRestRectangle(n, m, list, currentMin);
    }

    private int countRestRectangle(int n, int m, List<Square> currentSquares, int[] currentMin) {
        if (currentMin[0] <= currentSquares.size()) {
            // 判断比之前的最小值小的情况下提前结束，减少大量的时间复杂度
            return currentMin[0];
        }
        Square availableSquare = findAvailableSquare(n, m, currentSquares);

        if (availableSquare == null) {
            int size = currentSquares.size();
            if (size < currentMin[0]) {
                currentMin[0] = size;
            }
            return size;
        } else {
            int maxSideLen = availableSquare.right - availableSquare.left + 1;
            int min = n * m;
            for (int i = maxSideLen; i > 0; i--) {
                Square square = new Square(availableSquare.left, availableSquare.top, i);
                currentSquares.add(square);
                int count = countRestRectangle(n, m, currentSquares, currentMin);
                if (count < min) {
                    min = count;
                }
                currentSquares.remove(currentSquares.size() - 1);
            }
            return min;
        }
    }

    private Square findAvailableSquare(int n, int m, List<Square> squares) {
        int left = 1;
        int top = 1;
        if (squares.size() > 0) {
            Square square = squares.get(squares.size() - 1);
            top = square.right >= m ? square.top + 1 : square.top;
            left = square.right >= m ? 1 : square.right + 1;
        }

        Square availableSquare = null;
        outer:
        while (left <= m && top <= n) {
            while (left <= m) {
                Square locateSquare = findSquareByPoint(squares, top, left);
                if (locateSquare != null) {
                    left = locateSquare.right + 1;
                } else {
                    availableSquare = new Square(left, top, 1);
                    break outer;
                }
            }
            top++;
            left = 1;
        }
        if (availableSquare == null) {
            return null;
        }

        int sideLen = 2;
        while (availableSquare.top + sideLen - 1 <= n && availableSquare.left + sideLen - 1 <= m) {
            if (findSquareByPoint(squares, availableSquare.top, availableSquare.left + sideLen - 1) == null
                    && findSquareByPoint(squares, availableSquare.top + sideLen - 1, availableSquare.left) == null) {
                sideLen++;
            } else {
                break;
            }
        }
        availableSquare = new Square(availableSquare.left, availableSquare.top, sideLen - 1);
        return availableSquare;
    }

    private Square findSquareByPoint(List<Square> squares, int row, int col) {
        for (Square square : squares) {
            if (square.contains(row, col)) {
                return square;
            }
        }
        return null;
    }
}