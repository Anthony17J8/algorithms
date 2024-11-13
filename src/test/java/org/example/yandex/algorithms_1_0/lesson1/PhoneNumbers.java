/*
   Copyright 2020 Russian Post
   <p>
   This source code is Russian Post Confidential Proprietary.
   This software is protected by copyright. All rights and titles are reserved.
   You shall not use, copy, distribute, modify, decompile, disassemble or reverse engineer the software.
   Otherwise this violation would be treated by law and would be subject to legal prosecution.
   Legal use of the software provides receipt of a license from the right holder only.
 */

package org.example.yandex.algorithms_1_0.lesson1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PhoneNumbers {

    static String yes = "YES", no = "NO";

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        String target = reader.readLine();
        String one = reader.readLine();
        String two = reader.readLine();
        String three = reader.readLine();
        writer.write(String.valueOf(getResult(target, one)));
        writer.newLine();
        writer.write(String.valueOf(getResult(target, two)));
        writer.newLine();
        writer.write(String.valueOf(getResult(target, three)));
        writer.flush();
        reader.close();
        writer.close();
    }

    @DisplayName("{0}")
    @ParameterizedTest
    @MethodSource(value = "source")
    void test(int num, String target, String exist, String result) {
        assertEquals(result, getResult(target, exist));
    }

    private static Stream<Arguments> source() {
        return Stream.of(
                Arguments.of(1, "8(495)430-23-97", "+7-4-9-5-43-023-97", yes),
                Arguments.of(2, "8(495)430-23-97", "8-495-430", no),
                Arguments.of(3, "8(495)430-23-97", "4-3-0-2-3-9-7", yes),
                Arguments.of(4, "86406361642", "83341994118", no),
                Arguments.of(5, "86406361642", "86406361642", yes),
                Arguments.of(6, "86406361642", "83341994118", no),
                Arguments.of(7, "+78047952807", "+78047952807", yes),
                Arguments.of(8, "+78047952807", "+76147514928", no),
                Arguments.of(9, "+78047952807", "88047952807", yes),
                Arguments.of(10, "7952807", "7952807", yes),
                Arguments.of(11, "", "7952807", no),
                Arguments.of(12, "7952807", "", no)
        );
    }

    private static String getResult(String target, String exist) {
        return format(target).equals(format(exist)) ? yes : no;
    }

    private static String format(String num) {
        num = num.replaceAll("[()\\-]", "");
        if (num.startsWith("+7")) {
            num = num.substring(2);
        }
        if (num.startsWith("8") && num.length() == 11) {
            num = num.substring(1);
        }
        if (num.length() == 7) {
            num = "495" + num;
        }
        return num;
    }
}
