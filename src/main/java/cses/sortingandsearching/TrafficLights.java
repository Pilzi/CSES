package cses.sortingandsearching;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.TreeSet;

public class TrafficLights {
    private static final InputStream in = System.in;
    private static final byte[] buf = new byte[1 << 16];
    private static int bufLen = 0, bufPtr = 0;

    public static void main(String[] args) throws IOException {
        int streetLength = nextInt();
        int n = nextInt();

        PriorityQueue<Integer> gaps = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> removed = new PriorityQueue<>(Collections.reverseOrder());
        gaps.add(streetLength);

        TreeSet<Integer> trafficLights = new TreeSet<>();
        trafficLights.add(0);
        trafficLights.add(streetLength);

        StringBuilder sb = new StringBuilder(n * 11);

        for (int i = 0; i < n; i++) {
            int token = nextInt();

            int lower = trafficLights.lower(token);
            int upper = trafficLights.higher(token);

            removed.add(upper - lower);
            gaps.add(token - lower);
            gaps.add(upper - token);

            trafficLights.add(token);

            while (!removed.isEmpty() && gaps.peek().equals(removed.peek())) {
                gaps.poll();
                removed.poll();
            }

            sb.append(gaps.peek()).append(' ');
        }

        System.out.println(sb);
    }

    private static int readByte() throws IOException {
        if (bufPtr == bufLen) {
            bufLen = in.read(buf, 0, buf.length);
            bufPtr = 0;
            if (bufLen <= 0) return -1;
        }
        return buf[bufPtr++];
    }

    private static int nextInt() throws IOException {
        int c = readByte();
        while (c < '0') c = readByte();
        int r = 0;
        while (c >= '0') {
            r = r * 10 + (c - '0');
            c = readByte();
        }
        return r;
    }
}