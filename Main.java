package com.company;

import java.io.*;
import java.util.*;


public class Codechef {


    public static void main(String[] args) throws IOException {


        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        OutputWriter out = new OutputWriter(outputStream);


        int T = in.readInt();

        for (int I = 1; I <= T; I++) {
            String str = in.readString();
            out.printLine(str);
        }
        
        out.flush();
        out.close();
    }






    static class InputReader {
        private InputStream stream;
        private byte[] buf = new byte[1024];
        private int curChar;
        private int numChars;
        private InputReader.SpaceCharFilter filter;

        public InputReader(InputStream stream) {
            this.stream = stream;
        }

        public int read() {
            if (numChars == -1) {
                throw new InputMismatchException();
            }
            if (curChar >= numChars) {
                curChar = 0;
                try {
                    numChars = stream.read(buf);
                } catch (IOException e) {
                    throw new InputMismatchException();
                }
                if (numChars <= 0) {
                    return -1;
                }
            }
            return buf[curChar++];
        }

        public int readInt() {
            int c = read();
            while (isSpaceChar(c)) {
                c = read();
            }
            int sgn = 1;
            if (c == '-') {
                sgn = -1;
                c = read();
            }
            int res = 0;
            do {
                if (c < '0' || c > '9') {
                    throw new InputMismatchException();
                }
                res *= 10;
                res += c - '0';
                c = read();
            } while (!isSpaceChar(c));
            return res * sgn;
        }

        public String readString() {
            int c = read();
            while (isSpaceChar(c)) {
                c = read();
            }
            StringBuilder res = new StringBuilder();
            do {
                if (Character.isValidCodePoint(c)) {
                    res.appendCodePoint(c);
                }
                c = read();
            } while (!isSpaceChar(c));
            return res.toString();
        }

        public long readLong() {
            int c = read();
            while (isSpaceChar(c)) {
                c = read();
            }
            int sgn = 1;
            if (c == '-') {
                sgn = -1;
                c = read();
            }
            long res = 0;
            do {
                if (c < '0' || c > '9') {
                    throw new InputMismatchException();
                }
                res *= 10;
                res += c - '0';
                c = read();
            } while (!isSpaceChar(c));
            return res * sgn;
        }

        public char readCharacter() {
            int c = read();
            while (isSpaceChar(c)) {
                c = read();
            }
            return (char) c;
        }

        public boolean isSpaceChar(int c) {
            if (filter != null) {
                return filter.isSpaceChar(c);
            }
            return isWhitespace(c);
        }

        public static boolean isWhitespace(int c) {
            return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
        }

        public interface SpaceCharFilter {
            public boolean isSpaceChar(int ch);

        }

    }

    static class MapUtils{

        public static <K, V> void printMap(Map<K, V> map) {
            for (Map.Entry<K, V> entry : map.entrySet()) {
                System.out.println("Key : " + entry.getKey()
                        + " Value : " + entry.getValue());
            }
        }

        public static HashMap<String, Integer> sortByValue(Map<String, Integer> unsortMap) {

            // 1. Convert Map to List of Map
            List<Map.Entry<String, Integer>> list =
                    new LinkedList<Map.Entry<String, Integer>>(unsortMap.entrySet());

            // 2. Sort list with Collections.sort(), provide a custom Comparator
            //    Try switch the o1 o2 position for a different order
            Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
                public int compare(Map.Entry<String, Integer> o1,
                                   Map.Entry<String, Integer> o2) {
                    return (o1.getValue()).compareTo(o2.getValue());
                }
            });

            // 3. Loop the sorted list and put it into a new insertion order Map LinkedHashMap
            Map<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
            for (Map.Entry<String, Integer> entry : list) {
                sortedMap.put(entry.getKey(), entry.getValue());
            }


            return (HashMap<String, Integer>) sortedMap;
        }

    }
    static class IOUtils {
        public static int[] readIntArray(InputReader in, int size) {
            int[] array = new int[size];
            for (int i = 0; i < size; i++) {
                array[i] = in.readInt();
            }
            return array;
        }

        public static String[] readStringArray(InputReader in, int size) {
            String[] array = new String[size];
            for (int i = 0; i < size; i++) {
                array[i] = in.readString();
            }
            return array;
        }

        public static char[] readCharArray(InputReader in, int size) {
            char[] array = new char[size];
            for (int i = 0; i < size; i++) {
                array[i] = in.readCharacter();
            }
            return array;
        }


    }

    static class OutputWriter {
        private final PrintWriter writer;

        public OutputWriter(OutputStream outputStream) {
            writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(outputStream)));
        }

        public OutputWriter(Writer writer) {
            this.writer = new PrintWriter(writer);
        }

        public void print(Object...objects) {
            for (int i = 0; i < objects.length; i++) {
                if (i != 0)
                    writer.print(' ');
                writer.print(objects[i]);
            }
        }

        public void printLine(Object...objects) {
            print(objects);
            writer.println();
        }

        public void close() {
            writer.close();
        }

        public void flush(){
            writer.flush();
        }
    }



    static class ArrayUtils {


        // helper method to print array elements
        public static void display(int[] newarr) {
            int len = newarr.length;
            for (int i = 0; i < len; i++) {
                System.out.println(newarr[i]);
            }
        }

        // helper method to print 2d array elements
        public static void display2d(int[][] newarr){
            int len = newarr.length;
            for(int i=0;i<len;i++){
                for(int j=0;j<len;j++){
                    System.out.print(newarr[i][j] + " ");
                }
                System.out.println("\n");
            }
        }

    }

    static class MiscUtils {
        // mod7
        // usage: out.printline(n % MiscUtils.MOD7)
        public static final int MOD7 = (int) (1e9 + 7);
    }

}
