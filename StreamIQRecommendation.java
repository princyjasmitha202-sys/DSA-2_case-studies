import java.util.*;

public class StreamIQRecommendation {

    public static void main(String[] args) {

        String[] shows = {
            "Teach you a lesson S2",
            "Modern Family S3",
            "13 Reasons why S3",
            " Just Add Magic 2003"
        };

        int[] watchTime = {3, 4, 5, 2};     // hours
        int[] engagement = {40, 50, 60, 30};

        int W = 8; // Watch Budget
        int n = shows.length;

        int[][] dp = new int[n + 1][W + 1];

        // Build DP Table
        for (int i = 1; i <= n; i++) {
            for (int w = 0; w <= W; w++) {

                if (watchTime[i - 1] <= w) {
                    dp[i][w] = Math.max(
                            engagement[i - 1] +
                            dp[i - 1][w - watchTime[i - 1]],
                            dp[i - 1][w]
                    );
                } else {
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }

        System.out.println("Maximum Engagement Score = "
                + dp[n][W]);

        // Backtracking to find selected shows
        int w = W;
        System.out.println("\nRecommended Shows:");

        for (int i = n; i > 0; i--) {

            if (dp[i][w] != dp[i - 1][w]) {
                System.out.println(shows[i - 1]
                        + " (Time: " + watchTime[i - 1]
                        + " hrs, Score: "
                        + engagement[i - 1] + ")");

                w -= watchTime[i - 1];
            }
        }
    }
}
