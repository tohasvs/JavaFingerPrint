package algorithm;


import algorithm.domain.LineList;
import algorithm.domain.Minutia;

import java.util.ArrayList;
import java.util.List;

public class DeleteNoise extends Algorithm<LineList> {

    private List<Minutia> minutiaEnd;
    private List<Minutia> minutiaBranch;

    public DeleteNoise(LineList lineList) {
        this.minutiaEnd = lineList.getMinutiaEnd();
        this.minutiaBranch = lineList.getMinutiaBranch();
    }

    private List<Minutia> removeDuplicates(List<Minutia> points, List<Minutia> tmp) {
        List<Minutia> arr1 = getUniqueArr(points, tmp);
        List<Minutia> arr2 = getUniqueArr(tmp, points);
        arr1.addAll(arr2);
        return arr1;
    }

    public List<Minutia> getUniqueArr(List<Minutia> points, List<Minutia> tmp) {
        List<Minutia> arr = new ArrayList<Minutia>();
        boolean flag = true;
        for (Minutia point : points) {
            flag = true;
            for (Minutia tmpPoint : tmp) {
                if (point.equals(tmpPoint)) {
                    flag = false;
                }
            }

            if (flag) {
                arr.add(point);
            }
        }
        return arr;
    }

    @Override
    public LineList execute() {
        List<Minutia> tmpEnd = new ArrayList<Minutia>();
        List<Minutia> tmpBranch = new ArrayList<Minutia>();


        for (Minutia endPoint : minutiaEnd) {
            for (Minutia branchPoint : minutiaBranch) {
                if (branchPoint.getX() >= (endPoint.getX() - 5)
                        && branchPoint.getX() <= (endPoint.getX() + 5)
                        && branchPoint.getY() >= (endPoint.getY() - 5) && branchPoint.getY() <= (endPoint.getY() + 5)) {
                    tmpEnd.add(endPoint);
                    tmpBranch.add(branchPoint);
                }
            }
        }

        return new LineList(removeDuplicates(minutiaEnd, tmpEnd), removeDuplicates(minutiaBranch, tmpBranch));
    }
}
