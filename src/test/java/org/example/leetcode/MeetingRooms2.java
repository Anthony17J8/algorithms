/**
 * https://neetcode.io/problems/swim-in-rising-water?list=neetcode150
 */
package org.example.leetcode;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MeetingRooms2 {

    @ParameterizedTest
    @MethodSource("source")
    void test(List<Interval> intervals, int out) {
        assertEquals(out, minMeetingRooms(intervals));
    }

    static int st = 1, en = -1;

    public int minMeetingRooms(List<Interval> intervals) {
        List<Event> events = new ArrayList<>();
        for (Interval interval : intervals) {
            events.add(new Event(st, interval.start));
            events.add(new Event(en, interval.end));
        }

        events.sort(Comparator.comparing(Event::getVal).thenComparing(Event::getType));

        int max = 0;
        int counter = 0;
        for (Event event : events) {
            if (event.type == st) {
                counter++;
            } else if (event.type == en) {
                counter--;
            }
            max = Math.max(counter, max);
        }
        return max;

    }

    static Stream<Arguments> source() {
        return Stream.of(
                Arguments.of(List.of(
                        new Interval(0, 40),
                        new Interval(5, 10),
                        new Interval(15, 20)
                ), 2),
                Arguments.of(List.of(
                        new Interval(1, 5),
                        new Interval(5, 10),
                        new Interval(10, 15)
                ), 1),

                Arguments.of(List.of(
                        new Interval(4, 9)
                ), 1)
        );
    }

    public static class Event {
        int val;
        int type;

        public Event(int type, int val) {
            this.val = val;
            this.type = type;
        }

        public int getVal() {
            return val;
        }

        public int getType() {
            return type;
        }
    }

    public static class Interval {
        public int start, end;

        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}
