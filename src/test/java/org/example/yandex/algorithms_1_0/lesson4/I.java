/*
   Copyright 2020 Russian Post
   <p>
   This source code is Russian Post Confidential Proprietary.
   This software is protected by copyright. All rights and titles are reserved.
   You shall not use, copy, distribute, modify, decompile, disassemble or reverse engineer the software.
   Otherwise this violation would be treated by law and would be subject to legal prosecution.
   Legal use of the software provides receipt of a license from the right holder only.
 */

package org.example.yandex.algorithms_1_0.lesson4;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class I {
    static TestHelper fs = new TestHelper();
    static Predicate<Integer> noUpperCase = (i) -> i == 0;
    static Predicate<Integer> manyUpperCases = (i) -> i > 1;

    public static void main(String[] args) throws Exception {
        int n = fs.nextInt();
        Map<String, Set<String>> dict = new HashMap<>();
        while (n-- > 0) {
            String s = fs.next();
            String key = s.toLowerCase();
            if (dict.computeIfPresent(key, (k, v) -> {
                v.add(s);
                return v;
            }) == null) {
                Set<String> set = new HashSet<>();
                set.add(s);
                dict.put(key, set);
            }
        }

        String[] text = fs.readStringAsStringArray();
        fs.write(getResult(dict, text));
        fs.close();
    }

    @DisplayName("{0}")
    @ParameterizedTest
    @MethodSource(value = "source")
    @Timeout(1)
    void test(Map<String, Set<String>> dict, String[] s, int out) throws Exception {
        assertEquals(out, getResult(dict, s));
    }

    private static Stream<Arguments> source() {
        return Stream.of(
                Arguments.of(getDict(Set.of("cAnnot", "cannOt", "fOund", "pAge")),
                        new String[]{"thE", "pAge", "cAnnot", "be", "found"}, 2),
                Arguments.of(getDict(Set.of("cAnnot", "cannOt", "fOund", "pAge")),
                        new String[]{"The", "PAGE", "cannot", "be", "found"}, 4),
                Arguments.of(getDict(Set.of("cAnnot", "cannOt", "fOund", "pAge")),
                        new String[]{""}, 0),
                Arguments.of(getDict(Set.of()),
                        new String[]{"The", "Pa", "canMot", "bE", "Found"}, 0),
                Arguments.of(getDict(Set.of()),
                        new String[]{"The", "Pa", "canMot", "bE", "Found", "M"}, 0),
                Arguments.of(getDict(Set.of()),
                        getArr("ciFqrfIxe LgvqquN zvdlhnXJ tizFPXtv JxqWqgnR CabaJ hFYoqbhH UyfiTXO YvAylvnc ymtHHfnqh bmTLsEnh hikroekt dtVSftFBz ofQrMfo jGTGofv dVRwfJw UaRfzE wbjjGsM xKcezhleq XskqyXtl pWCkyr JcuiiawHw hHBbOV pIfztqkuo PNbjXhtoi lvthZdUc oozdiZCq xDdnpRqsD HDfocjpl aziTbCh jsJMkTpaG voKfnjYb ADtpndbo gwOkCvJs hGnvtbM NkFodqOwy BOgxWv qzMsbelpO tlQnic QlxjhTzNj OZvzmoNX NlbLsjq OvkpysLzc BFCjEESh aiLyKJas zhJbcdu vqxrKhgke BsEikapC aCCoHAFR qNSYNouc TboUQd bOJWCAy NiNkHPmA wsGXhub xIvDhv qeGgpTBkd XPOkfsxz bbgcyuWq YHioAxh mngcZbpgh qgbfVztk JMusos ogpskgdN HUmedvmA GhhpYKH vduijcpv rfMZlB rvpodvq UogggJX yhnKvOpz cKQHazal xdXpPMCzn zaYdrbic FYDsrfaF kFpUcXtY jihQVZ nhpgJXoT rwJokNFH eGvoNulk LugdwQRbl xlRQdy mOrvmCmhG tJpEDolo zLhXCegyl Orrnefa sQqsjckkm BaadhPSXa vLmbthY mlwXEbpns oLctMjmM spQHUtA qnxUrLGo ScAndtRh apdwiTV odtrlufr zkrreoWHT wbOJLyIvn TDPtdjFp CJtnhbNr ohkLVpww vJLdOfp ctswuolD kuIdtiWLq pHQvvlN uzEgrQhpG ncHyqZzt gkntqtmx ZAvUjYgJ hbjSXnN URnTcdbRk wMdRlrQ ilpvgMpja oIjXxmG sdnIJTkk bFvhViK dpdtgpt CembyhyMM RcBIirR rWyYzJfc UNbTfQQ plxhCpee bUbkgdDF JTgQAQhvp onFayvlm rykjyldhw xAuEnxF phbhlXB xxDDxfSfM aPHXcXsag sTseOhrrY MMiaCH avfBbeIfl hrtuxe zJlubLs Wwwdjvc JBnqWcAl hDXOdeHZr Nelvhp ydmbzqym kzbCxuN rNQCDbeqB VRaax yykZbEzOc XomeWckS jBrCrHrx FzGrd YvfUzRryb YYPSRO wxBazrm EkezBXg aUiYcod aTtjEEpz WczSYbDx tlFeIKB LlSQDtyVa iMGfoQ WyXVU wakvcqSU loKOoSnzA pWywmptia RcEMjACB bfyrkwpLp iSofdn SacAoja uIkcgpWnM dkqErpG nkxTwHfh srejafR rjXxUCQ BCstjy LBdeArjn SbunWWcd FmTkfGwh nyGRjOi VlZjliwD lQhjguWx IkemfP VGfqFBuiN aYrbvKu iabtpq QnjzcKE sffhhZYY JuooLppkr LqyjiBC WySPUmsQb CXjftV TmtgreN cicNuaor YIgdgc vTFwiGktI TNFmWdHU DpfEtwtf zssykzEsf xcsCKfnaS OBEwUVkzg PgArpsz RmUcaQels JmJaqn IcXnrvhkg vgkOPzmv BezbGaMfb bnjTmLse TnkNLq CxfpAoD slhLtnoaP jnppioo STcoufpS XmRtQqd yDpbsld qrVIEXn VZhmozgvr zuELbtea WiOopcPg vRwLRoy TyJkPUvOq yZSsjoi AzlwLPf suwNmPrzF kuJeUivG EnTHwlj XpZkNZH xygjBcx bTytkWFW ygKis iwjgktgw DzFDkhb elWPfddxy TyvlSNVR OBqjNCtXI cSYxJTb DqedLuqN vxikfztEc AbcQgnxiS bdCmDnn VbLDUWsz eeuucmp osIYklu sLXmxRipG yswHjZayD bMihpnRs lmyVpf gtfkgpmbB buPjpqli yxFSIu Ggjzeqm dTqrvPtei XduXypxn BykKgHhqe ewcUDvM lxWoiXBT HeqhAZu ialinsqW lcNzJmK yCKPbQrz hOjxGWvPq HgenCiuw QnWaEtz lRvwixAj otnJuciJ qtNWZPtL tJYiHzUiG rNhaIjil Oggvgmdq nhylWth VhrHlcnJV ceHFAVnU udtpWoJ wiQAeN mUkmTUv BqnLMdeSy kiffoynx vsnzxZe QbyfijgM ecxxWebWo TFoLnXouN SldaeWjV fuXlPWzBg qNNShyw avvNUgmP lhblkew svZdutKT avlFkl Afntea NrGVToH Hlvkxnut NbNbQXnc ugqqBFWcq bphIBvYpz FuuAUB CnqFfWxy lNdVncXmk UBvozFuqz muVHqMpa vcyQgcXN lfzcMdN eMmz ehXopYXAg GjBapgXVY qimorSZZt YEyoXzclm eUlyvxi aFrOSW htxGkVuJ ydszmKyb zAvzlkXM oRQizsGBz gZodlbKg pwQSugvyq MOvfpsCs tGNJBf lZqEdtPD sHYmFjcH wkRltsoYz qkWmhhBJ wgATDnhc HnbkMwnRv oSolCjBj xyQfzgenj xBtaqrEj eoCCfSg xYgfXZ htSgaFlbt DPrfLUw bdhrUaqT IabsomJE cyVtEg SMntmDjx jwwkwJkRo HeFyGSaTh KiSTzh dBCcqt vnAunEzf kGzwbur qmkMBbGCw Tdkunrkh YrYVnrbC muJSHjn Unuwgjfj ObbZfLsWd UjmxpJN KNlbLhF jFlbrLf pyOglEK"),
                        296)

                );
    }

    private static String[] getArr(String s) {
        return s.split(" ");
    }

    private static Map<String, Set<String>> getDict(Set<String> set) {
        Map<String, Set<String>> dict = new HashMap<>();
        for (String s : set) {
            String key = s.toLowerCase();
            if (dict.computeIfPresent(key, (k, v) -> {
                v.add(s);
                return v;
            }) == null) {
                Set<String> set1 = new HashSet<>();
                set1.add(s);
                dict.put(key, set1);
            }
        }
        return dict;
    }

    private static int getResult(Map<String, Set<String>> dict, String[] s) {
        int errors = 0;
        for (String word : s) {
            if (!word.isEmpty() && isError(dict, word)) errors++;
        }
        return errors;
    }

    private static boolean isError(Map<String, Set<String>> dict, String word) {
        int numUppercases = 0;
        for (char sym : word.toCharArray()) {
            if (Character.isUpperCase(sym)) {
                numUppercases++;
            }
        }

        return noUpperCase.test(numUppercases)
                || manyUpperCases.test(numUppercases)
                || (dict.containsKey(word.toLowerCase())
                && !dict.get(word.toLowerCase()).contains(word));
    }

    private static class TestHelper {
        BufferedReader in;
        BufferedWriter out;

        TestHelper() {
            in = new BufferedReader(new InputStreamReader(System.in));
            out = new BufferedWriter(new OutputStreamWriter(System.out));
        }

        boolean isReady() throws Exception {
            return in.ready();
        }

        Stream<String> readFromFile() throws IOException {
            return Files.lines(Path.of("src/test/java/org/example/yandex/algorithms_1_0/lesson4/input.txt"));
//            return Files.lines(Path.of("input.txt"));
        }

        void write(String s) throws IOException {
            out.write(s);
        }

        void write(long l) throws IOException {
            out.write(String.valueOf(l));
        }

        void write(int i) throws IOException {
            out.write(String.valueOf(i));
        }

        void writeAll(long[] i) throws IOException {
            for (long el : i) {
                out.write(el + " ");
            }
        }

        void writeAll(int[] i) throws IOException {
            for (int el : i) {
                out.write(el + " ");
            }
        }

        void writeAll(Iterable<String> it) {
            it.forEach(o -> {
                try {
                    newLine();
                    write(o);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }

        void writeAll(String[] w) throws IOException {
            for (String b : w) {
                out.write(b + " ");
            }
        }

        void writeAllMultiline(String[] s) {
            Arrays.stream(s)
                    .filter(Objects::nonNull)
                    .forEach(System.out::println);
        }

        void writeOneByOne(char[][] in) throws IOException {
            for (int i = 0; i < in.length; i++) {
                for (int j = 0; j < in[0].length; j++) {
                    out.write(in[i][j] + " ");
                }
                out.write("\n");
            }
        }

        void newLine() throws IOException {
            out.newLine();
        }

        String next() {
            try {
                return in.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        int[] readStringAsIntArray() {
            try {
                return Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        String[] readStringAsStringArray() {
            try {
                return in.readLine().split(" ");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }

        void close() throws Exception {
            out.flush();
            in.close();
            out.close();
        }

        public static int[] generate(int size, int num) {
            int[] in = new int[size];
            Arrays.fill(in, num);
            return in;
        }

        public static String generate(int size) {
            return Stream.generate(() -> "Me")
                    .limit(size)
                    .collect(Collectors.joining());
        }

        public static int[] range(int from, int to) {
            return IntStream.range(from, to).toArray();
        }

        public static int[] generateRandom(int size) {
            //todo
            return new int[]{};
        }
    }
}
