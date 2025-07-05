
/*
   Copyright 2020 Russian Post
   <p>
   This source code is Russian Post Confidential Proprietary.
   This software is protected by copyright. All rights and titles are reserved.
   You shall not use, copy, distribute, modify, decompile, disassemble or reverse engineer the software.
   Otherwise this violation would be treated by law and would be subject to legal prosecution.
   Legal use of the software provides receipt of a license from the right holder only.
 */

package org.example.yandex.algorithms_2_0.A.lesson3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.provider.Arguments;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class E {
    static TestHelper fs = new TestHelper();
    static String yes = "Yes", no = "No";

    public static void main(String[] args) throws Exception {
        long n = fs.nextLong();
        long mod1 = 1_000_000_000 + 9;
        long mod2 = 1_000_000_000 + 11;
        long mod0 = 1_000_000_000 + 7;
        long[] mods = new long[]{mod0, mod1, mod2};
        Set[] modesH = new Set[3];
        modesH[0] = initHash(mods[0]);
        modesH[1] = initHash(mods[1]);
        modesH[2] = initHash(mods[2]);

        while (n-- > 0) {
            boolean[] res = new boolean[3];
            int idx = 0;
            String sNum = fs.next();
            for (long m : mods) {

                BigInteger modi = new BigInteger(sNum).mod(new BigInteger(String.valueOf(m)));
                res[idx] = modesH[idx].contains(modi.longValue());
                idx++;
            }
            fs.write(res[0] && res[1] && res[2] ? yes : no);
            fs.newLine();
        }

        fs.close();
    }

    private static Set<Long> initHash(long mod) {
        long num1 = 0;
        long num2 = 1;
        Set<Long> fibMods = new HashSet<>();
        for (int i = 1; i <= 40_000; i++) {
            long nmod = (num1 + num2) % mod;
            fibMods.add(nmod);
            num1 = num2;
            num2 = nmod;
        }
        return fibMods;
    }

    @DisplayName("{0}")
    @Test
    //@MethodSource(value = "source")
    @Timeout(2)
    void test() throws Exception {
        assertEquals(yes, getResult(
                "479852354820492075998991722488647240979841308887485932775965175033232600887334100326889781925858675886684373581243424954712916063386624874031280433193583757655364240610584574890235696336824465873423832152603163279713334271135945265445048249300971175606542851109986442134266320842394455560120701261958719997591686987216997560511591157574309997343641644109596739753549188178568037966066538602960473483868500791066954001012838990734891152384706097572800250595990061726914069247363329542451555778004133109665773364771459584916386792074757785368212969691860598812537957685522842431126173016706575452879441289124033992790629184019287508139252088152106715796406219817671100237596755897172632954681818789451690748089355729236810403189718292108068998937057713883802736869752746615352452993710205378915721079050965971956206593604752756484861988541415843644737221911658434692708005554481959072222534715040501757186217901024511310486550372173314116301165192643626894469635687416543509797501595218290656429811505399362311543466791450885362745506953061820648782732776657706128913663853448292465686815469004654165247370394555334937184579329414620908901800933859710466051554152024531263539478444453627554940192647949562916250433718583389151273901382758958884345105209856082930483112903190253959170334174114361869433806274042770674379857306167291946223882207181563660178674925253535382183815076206218922296542149636641120637542982542755586558026617035014659692607900197270754156676677026507242040788134730756482167141385505115211815380571559606001721433874601513601386097189460162324876577857712727258715833954260702373482549448273055578616828280961728468816439835732238466328528425342583788029959918892614062340966647309899935788663272579378159778397529711673183442275021284930835395630167891238351140270866172177601175547328544262926137900192145929"));
        assertEquals(no, getResult(
                "22018819490827940653766977961465402980456800093267077759962086936403091606943671056840978478357892870368084106571521816341790100129228141365057122482726456299503629050499850944311250947292158280557085149385746241302556368593361798910632543043207011617110771711607437857822365929780678446951652498859164066756658020197284010870732930261240000407153266540239087511949065245887093772808840535702504289158327308824859489181240622321376510723026623768831457313382086721921665438362526854962880603470936974971398054877726345530995093887868338701484273453358532570766217818610107981896145916608557968287845645297628959247314040340083523942282906730230435669692530587466981076670080718448497869588733699095775361236895483816144993845341662192451924761882352359099626649373978623173379922351299304268967767849215527789887815277226084633751285893666109673400174108876526000943078098945147735581114628601914463594024736835317735292996368392022335412197357784246705278854891262780106110768358804027979799478098176417601350252790453927981720333014258659237283247343341536342809492532143793106307289194970197294540826037005655796479695075854029647909847225145859183491320141861902269285916593949784227931901042094795464000229840538713255466572445480905209206338422022063249100103229788786070996985839592671199795983773862948511374995467947504924785504218514278247512706007830137510259174396383070251437101238112393425032471953823301302441773621637488763154057514430683828665413635495378751539921532834593711287134258260887568108137544990937484337510541465866718502831310109239434813640283755010560082971737844141723992041715377447201119590914587601273592636902246960523926145528831872897561242176913257329187423276063463979597132267444202227962649449618873181124832938921017819968160455372593537012365786278030784881131395478276056787743555440662728833404822259200836683936979183495443990523505334628775136749272103431083346181565627207152155362228427104363332820628189324705678698037866463386251343406721783177307020072278353134621595864247652877795464179610813368834458117804299061180182799779220125545751894867278390855402514473202391409589676491879232521026479376963370350538080670063711092920273272047590188053517577712407934521177614605677185218310208708799365564195170702242171765632202584906544469361543198925033725980940791342978865310412567214343486711757062164867974464371336676384349354355545860182954934691625703447984948342009074962329784587568009418947743110890648214157600696176734247437823427570930403298848987927420819777613360872550604678682667672681803064622336762351188129266130367852076838155300744281463366877780863185827680419546285500513871464287972310625287280105118833136946848545560025320559169699104974195440592500768729778544275354453782314151691724687861731049876320535841678286440652440110367689506697433275669022400500140996439420771366215236342101961077764189330245255076683898858856613993654694230429217187922380247193113612253442185304737506042108214840088340624199198628399297509050297936777703466177486440205574291369326688064215489878660512896957517769580176"));
    }


    private static Stream<Arguments> source() {
        return Stream.of(
//                Arguments.of(new int[]{0, 0, 0, 0, 0, 0}, new int[]{2, 3, 4, 5, 6}, 1),

        );
    }


    private static String getResult(String i) {
        return "";
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

        <T> void write(T obj) throws IOException {
            out.write(obj.toString());
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

        void writeAll(long[] i, char sep) throws IOException {
            for (long el : i) {
                out.write(el + "" + sep);
            }
        }

        void writeAll(int[] i) throws IOException {
            for (int el : i) {
                out.write(el + " ");
            }
        }

        void writeAll(int[] i, char sep) throws IOException {
            for (int el : i) {
                out.write(el + "" + sep);
            }
        }

        <T> void writeAll(Iterable<T> it) {
            it.forEach(o -> {
                try {
                    write(o);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }

        <T> void writeAll(Iterable<T> it, char sep) {
            it.forEach(o -> {
                try {
                    write(o + "" + sep);
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

        void writeAll(String[] w, char sep) throws IOException {
            for (String b : w) {
                out.write(b + sep);
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
                return Arrays.stream(in.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        long[] readStringAsLongArray() {
            try {
                return Arrays.stream(in.readLine().split(" ")).mapToLong(Long::parseLong).toArray();
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

        public static int[] generateInt(int size, int num) {
            int[] in = new int[size];
            Arrays.fill(in, num);
            return in;
        }

        public static String generate(int size) {
            return Stream.generate(() -> "a")
                    .limit(size)
                    .collect(Collectors.joining());
        }

        public static long[] generateLong(int size, int num) {
            long[] in = new long[size];
            Arrays.fill(in, num);
            return in;
        }

        public static long[] generateRandomLong(int size) {
            long[] in = new long[size];
            Random random = new Random(System.currentTimeMillis());
            Arrays.fill(in, random.nextLong());
            return in;
        }

        public static int[] range(int from, int to) {
            return IntStream.range(from, to).toArray();
        }

        public static int[] generateRandomSorted(int size, int seed) {
            Random random = new Random(seed);
            int[] array = random.ints(1, 20).limit(size).toArray();
            Arrays.sort(array);
            return array;
        }

        public static int[] generateRandom(int size, int seed) {
            Random random = new Random(seed);
            return random.ints(1, 10).limit(size).toArray();
        }

        public static int[][] generateTwoDimensionalRandom(int row, int col) {
            int[][] res = new int[row][col];
            for (int i = 0; i < row; i++) {
                res[i] = generateRandom(col, i);
            }
            return res;
        }
    }
}
