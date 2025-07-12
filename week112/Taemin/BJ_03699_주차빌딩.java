import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ_03699_주차빌딩 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = null;
        StringBuilder stringBuilder = new StringBuilder();

        int numOfTest = Integer.parseInt(bufferedReader.readLine());
        for (int i = 0; i < numOfTest; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
            int height = Integer.parseInt(stringTokenizer.nextToken());
            int length = Integer.parseInt(stringTokenizer.nextToken());

            PriorityQueue<Location> clients = new PriorityQueue<>();
            for (int j = 0; j < height; j++) {
                stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
                for (int k = 0; k < length; k++) {
                    int number = Integer.parseInt(stringTokenizer.nextToken());
                    if (number != -1) {
                        clients.add(new Location(number, j, k));
                    }
                }
            }

            int[] conveyor = new int[height];
            int time = 0;

            while(!clients.isEmpty()) {
                Location client = clients.poll();
                int row = client.row;
                int col = calculate(length, client.col, conveyor[row]);

                int move = 0;
                if (col <= length / 2) {
                    move = col;
                    conveyor[row] -= move;
                } else {
                    move = length - col;
                    conveyor[row] += move;
                }

                time += (5 * move + 2 * 10 * row);
            }

            stringBuilder.append(time).append('\n');
        }

        System.out.print(stringBuilder.toString());
    }

    static int calculate(int length, int col, int conveyor) {
        return (col + (conveyor % length) + length) % length;
    }

    static class Location implements Comparable<Location>{
        int sequence;
        int row;
        int col;

        public Location(int sequence, int row, int col) {
            this.sequence = sequence;
            this.row = row;
            this.col = col;
        }

        @Override
        public int compareTo(Location other) {
            return Integer.compare(this.sequence, other.sequence);
        }
    }
}
