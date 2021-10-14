package aws.onlineassesment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MergeIntervals {
    static class Interval implements Comparable<Interval> {
        int left;
        int right;

        public Interval (int left, int right) {
            this.left = left;
            this.right = right;
        }

        @Override
        public int compareTo(Interval o) {
            return this.left - o.left;
        }
    }

    // own implementation
    public int[][] merge(int[][] intervals) {
        if (intervals.length < 2) {
            return intervals;
        }

        ArrayList<Interval> li = new ArrayList<>();
        for (int[] interval : intervals) {
            li.add(new Interval(interval[0], interval[1]));
        }

        Collections.sort(li);
        ArrayList<Interval> result = new ArrayList<>();
        Interval start = li.get(0);
        for (int i = 1; i < li.size(); i++) {
            Interval current = li.get(i);
            if (start.right >= current.left) {
                if (start.right < current.right) {
                    start.right = current.right;
                }
            } else {
                result.add(start);
                start = current;
            }
        }

        int[][] res = new int[intervals.length][2];

        for (int i = 0; i < result.size(); i++) {
            res[i][0] = result.get(i).left;
            res[i][1] = result.get(i).right;
        }

        return res;
    }

    // optimal solution from leetcode
    public int[][] mergeOptimal(int[][] intervals) {
        if (intervals.length <= 1) {
            return intervals;
        }
        Arrays.sort(intervals, (arr1, arr2) -> Integer.compare(arr1[0], arr2[0]));

        List<int[]> output_arr = new ArrayList<>();
        int[] current_interval = intervals[0];
        output_arr.add(current_interval);

        for (int[] interval : intervals) {
            int current_end = current_interval[1];
            int next_begin = interval[0];
            int next_end = interval[1];
            if (current_end >= next_begin) {
                current_interval[1] = Math.max(current_end, next_end);
            } else {
                current_interval = interval;
                output_arr.add(interval);
            }
        }
        return output_arr.toArray(new int[output_arr.size()][]);
    }
}
