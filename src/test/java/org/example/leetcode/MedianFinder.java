package org.example.leetcode;

import java.util.ArrayList;
import java.util.List;

// ToDo: refactoring
public class MedianFinder {

    List<Integer> minHeap;
    int currentIdxMin = 0;
    List<Integer> maxHeap;
    int currentIdxMax = 0;

    public static void main(String[] args) {
        MedianFinder medianFinder = new MedianFinder();
        medianFinder.addNum(1);
        System.out.println(medianFinder.findMedian());
        medianFinder.addNum(2);
        System.out.println(medianFinder.findMedian());
        medianFinder.addNum(3);
        System.out.println(medianFinder.findMedian());
        medianFinder.addNum(4);
        System.out.println(medianFinder.findMedian());
        medianFinder.addNum(5);
        System.out.println(medianFinder.findMedian());
        medianFinder.addNum(6);
        System.out.println(medianFinder.findMedian());
        medianFinder.addNum(7);
        System.out.println(medianFinder.findMedian());
        medianFinder.addNum(8);
        System.out.println(medianFinder.findMedian());
        medianFinder.addNum(9);
        System.out.println(medianFinder.findMedian());
        medianFinder.addNum(10);
        System.out.println(medianFinder.findMedian());
    }


    public MedianFinder() {
        this.minHeap = new ArrayList<>();
        this.maxHeap = new ArrayList<>();
    }

    public void addNum(int num) {

        if (maxHeap.isEmpty()) {
            maxHeap.add(-1);
            maxHeap.add(1, num);
            currentIdxMax++;
        } else if (num < maxHeap.get(1)) {
            currentIdxMax++;
            maxHeap.add(currentIdxMax, num);
            shiftUpMax();
        } else if (minHeap.isEmpty()) {
            minHeap.add(-1);
            minHeap.add(1, num);
            currentIdxMin++;
        } else {
            currentIdxMin++;
            minHeap.add(currentIdxMin, num);
            shiftUpMin();
        }

        if (currentIdxMax > currentIdxMin + 1) {
            balanceHeaps();
        }
        if (currentIdxMax < currentIdxMin) {
            balanceHeapsToMax();
        }
    }

    private void balanceHeaps() {
        int idx = currentIdxMax - (currentIdxMin + 1);
        while (idx-- >= 1) {
            Integer i = maxHeap.get(1);
            if (minHeap.isEmpty()) {
                minHeap.add(-1);
            }
            minHeap.add(++currentIdxMin, i);
            shiftUpMin();
            removeFromMaxHeap();
        }
    }

    private void balanceHeapsToMax() {
        int idx = currentIdxMin - currentIdxMax;
        while (idx-- >= 1) {
            Integer i = minHeap.get(1);
            if (maxHeap.isEmpty()) {
                maxHeap.add(-1);
            }
            maxHeap.add(++currentIdxMax, i);
            shiftUpMax();
            removeFromMinHeap();
        }
    }

    private void removeFromMaxHeap() {
        Integer candidate = maxHeap.remove(currentIdxMax--);
        maxHeap.set(1, candidate);
        shiftDownMax(1);
    }

    private void removeFromMinHeap() {
        Integer candidate = minHeap.remove(currentIdxMin--);
        minHeap.set(1, candidate);
        shiftDownMin(1);
    }

    private void shiftDownMin(int i) {
        int largest = i;
        int l = 2 * i;
        int r = 2 * i + 1;
        if (l <= currentIdxMin && minHeap.get(largest) > minHeap.get(l)) {
            largest = l;
        }
        if (r <= currentIdxMin && minHeap.get(largest) > minHeap.get(r)) {
            largest = r;
        }
        if (largest != i) {
            int temp = minHeap.get(largest);
            minHeap.set(largest, minHeap.get(i));
            minHeap.set(i, temp);
            shiftDownMin(largest);
        }
    }

    private void shiftDownMax(int i) {
        int largest = i;
        int l = 2 * i;
        int r = 2 * i + 1;
        if (l <= currentIdxMax && maxHeap.get(largest) < maxHeap.get(l)) {
            largest = l;
        }
        if (r <= currentIdxMax && maxHeap.get(largest) < maxHeap.get(r)) {
            largest = r;
        }
        if (largest != i) {
            int temp = maxHeap.get(largest);
            maxHeap.set(largest, maxHeap.get(i));
            maxHeap.set(i, temp);
            shiftDownMax(largest);
        }
    }

    private void shiftUpMin() {
        int idx = currentIdxMin;
        while (idx > 1 && minHeap.get(idx) < minHeap.get(idx / 2)) {
            int temp = minHeap.get(idx);
            minHeap.set(idx, minHeap.get(idx / 2));
            minHeap.set(idx / 2, temp);
            idx /= 2;
        }
    }

    private void shiftUpMax() {
        int idx = currentIdxMax;
        while (idx > 1 && maxHeap.get(idx) > maxHeap.get(idx / 2)) {
            int temp = maxHeap.get(idx);
            maxHeap.set(idx, maxHeap.get(idx / 2));
            maxHeap.set(idx / 2, temp);
            idx /= 2;
        }
    }


    public double findMedian() {
        if (!maxHeap.isEmpty() && !minHeap.isEmpty()) {
            if (maxHeap.size() > minHeap.size()) {
                return maxHeap.get(1);
            } else if (minHeap.size() > maxHeap.size()) {
                return minHeap.get(1);
            } else {
                return (double) (maxHeap.get(1) + minHeap.get(1)) / 2;
            }

        }
        if (minHeap.isEmpty()) {
            return maxHeap.get(1);
        } else {
            return minHeap.get(1);
        }
    }
}
