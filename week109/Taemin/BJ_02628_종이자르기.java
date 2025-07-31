import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class BJ_02628_종이자르기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int width = Integer.parseInt(st.nextToken());
        int height = Integer.parseInt(st.nextToken());
        int numOfIndex = Integer.parseInt(br.readLine());

        List<Integer> listOfWidth = new ArrayList<Integer>();
        listOfWidth.add(0);
        listOfWidth.add(width);

        List<Integer> listOfHeight = new ArrayList<Integer>();
        listOfHeight.add(0);
        listOfHeight.add(height);

        for (int i = 0; i < numOfIndex; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int d = Integer.parseInt(st.nextToken());
            int index = Integer.parseInt(st.nextToken());

            if (d == 0) {
                listOfHeight.add(index);
            } else if (d == 1) {
                listOfWidth.add(index);
            }
        }

        List<Integer> listOfWidthLength = new ArrayList<Integer>();
        Collections.sort(listOfWidth);
        for (int i = 1; i < listOfWidth.size(); i++) {
            listOfWidthLength.add(listOfWidth.get(i) - listOfWidth.get(i - 1));
        }

        List<Integer> listOfHeightLength = new ArrayList<Integer>();
        Collections.sort(listOfHeight);
        for (int i = 1; i < listOfHeight.size(); i++) {
            listOfHeightLength.add(listOfHeight.get(i) - listOfHeight.get(i - 1));
        }

        int answer = 0;
        for (int widthLength : listOfWidthLength) {
            for (int heightLength : listOfHeightLength) {
                answer = Math.max(answer, widthLength * heightLength);
            }
        }

        System.out.print(answer);
    }
}
