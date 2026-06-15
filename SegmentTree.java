class SegmentTree {
    int[] tree;
    int n;

    SegmentTree(int[] arr) {
        n = arr.length;
        tree = new int[4 * n];
        build(arr, 1, 0, n - 1);
    }

    void build(int[] arr, int node, int start, int end) {
        if (start == end) {
            tree[node] = arr[start];
        } else {
            int mid = (start + end) / 2;

            build(arr, 2 * node, start, mid);
            build(arr, 2 * node + 1, mid + 1, end);

            tree[node] = tree[2 * node] + tree[2 * node + 1];
        }
    }

    int query(int node, int start, int end, int l, int r) {

        if (r < start || end < l)
            return 0;

        if (l <= start && end <= r)
            return tree[node];

        int mid = (start + end) / 2;

        return query(2 * node, start, mid, l, r)
                + query(2 * node + 1, mid + 1, end, l, r);
    }

    void update(int node, int start, int end,
                int idx, int value) {

        if (start == end) {
            tree[node] = value;
        } else {
            int mid = (start + end) / 2;

            if (idx <= mid)
                update(2 * node, start, mid, idx, value);
            else
                update(2 * node + 1, mid + 1, end, idx, value);

            tree[node] = tree[2 * node] + tree[2 * node + 1];
        }
    }

    public static void main(String[] args) {

        int[] playCounts = {10, 20, 30, 40};

        SegmentTree st = new SegmentTree(playCounts);

        System.out.println(
            "Total Plays (Day2-Day4): "
            + st.query(1, 0, 3, 1, 3));

        st.update(1, 0, 3, 2, 50);

        System.out.println(
            "After Update Total Plays (Day1-Day4): "
            + st.query(1, 0, 3, 0, 3));
    }
}