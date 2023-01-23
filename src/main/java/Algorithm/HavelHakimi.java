package Algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class HavelHakimi {

    public List<Integer> isRealizable(int... degrees) {
        if (checkCorrectness(degrees)) {
            List<Integer> degreesList = Arrays.stream(degrees).boxed().collect(Collectors.toList());
            degreesList = topDownReduction(degreesList);
            return degreesList;
        }
        return new ArrayList<Integer>();
    }

    public boolean checkCorrectness(int... degrees) {
        if (degrees == null) return false;
        if (degrees.length == 0) return false;
        for (int deg : degrees) {
            if (deg < 0) {
                return false;
            }
        }
        return true;
    }

    public List<Integer> topDownReduction(List<Integer> degrees) {
        while (canStillCalculateReduction(degrees)) {
            Collections.sort(degrees, Collections.reverseOrder());
            int toReduce = degrees.get(0);
            for (int i = 1; i <= toReduce; i++) {
                int deg = degrees.get(i);
                deg -= 1;
                degrees.remove(i);
                degrees.add(i, deg);
            }
            degrees.remove(0);
        }
        return degrees;
    }

    public boolean canStillCalculateReduction(List<Integer> degrees) {
        boolean possible = false;
        for (int deg : degrees) {
            if (deg < 0) {
                return false;
            }
            if (deg >= 1) {
                possible = true;
            }
        }
        return possible;
    }

}
