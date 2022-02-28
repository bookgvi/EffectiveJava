package Algos.BackTracking;

import java.util.*;

/*
 * https://leetcode.com/problems/restore-ip-addresses/submissions/
 * */
public class RestoreIPAddresses {
    private static final char delimiter = '.';
    private static StringBuilder ip;
    private static List<Integer> positions;
    private static int dotCounter;

    public static void main(String[] args) {
//        String s = "10101010";
//        String s = "101023";
//        String s = "25525511135";
//        String s = "0000";
        String s = "240102";
//        String s = "1";
        List<String> addresses = restoreIpAddresses(s);
    }

    public static List<String> restoreIpAddresses(String s) {
        List<String> ipAddresses = new ArrayList<>();

        ip = new StringBuilder();
        positions = new ArrayList<>();
        dotCounter = 0;
        int pos = 0;
        String octet = "";
        backTrack(s, pos, ipAddresses);
        return ipAddresses;
    }

    private static void backTrack(String s, int pos, List<String> ipAddresses) {
        for (; pos < s.length() && pos >= 0; pos += 1) {
            if (isValid(s, pos)) {
                String octet = place(s, pos);
                if (dotCounter == 4) {
                    if (isIPValid(s, pos)) {
                        ip.deleteCharAt(ip.length() - 1); // delete most right dot
                        ipAddresses.add(ip.toString());
                        ip.append(delimiter); // for next iteration must be 4 dots
                    }
                } else
                    backTrack(s, pos + 1, ipAddresses);
                pos = remove(s, pos);
            } else break;
        }
    }

    private static boolean isValid(String s, int pos) {
        if (positions.isEmpty()) return true;

        String octet = getOctet() + s.charAt(pos);
        if (octet.length() > 1 && octet.charAt(0) == '0') return false;

        int prevPos = positions.get(positions.size() - 1);
        boolean ans = prevPos < pos;
        ans &= Integer.parseInt(octet, 10) <= 255;
        return ans;
    }

    private static boolean isIPValid(String s, int pos) {
        return pos + 1 >= s.length();
    }

    private static String place(String s, int pos) {
        ip.append(s.charAt(pos));
        String octet = getOctet();

        ip.append(delimiter);
        positions.add(pos);
        dotCounter += 1;
        return octet;
    }


    private static int remove(String s, int curPos) {
        if (ip.length() > 0 && ip.charAt(ip.length() - 1) == delimiter) {
            ip.deleteCharAt(ip.length() - 1);
            dotCounter -= 1;
        }
        String octet = getOctet();
        if (curPos + 1 < s.length()) octet += s.charAt(curPos + 1);
        if ((curPos + 1 < s.length() && Integer.parseInt(octet, 10) > 255)
                || (octet.length() >= 1 && octet.charAt(0) == '0')
                || curPos + 1 == s.length()) {
            while (ip.length() > 0 && ip.charAt(ip.length() - 1) != delimiter) {
                ip.deleteCharAt(ip.length() - 1);
                positions.remove(positions.size() - 1);
            }
            if (ip.length() > 0 && ip.charAt(ip.length() - 1) == delimiter) {
                ip.deleteCharAt(ip.length() - 1);
                dotCounter -= 1;
            }
        }
        return !positions.isEmpty() ? positions.get(positions.size() - 1) : Integer.MIN_VALUE;
    }

    private static String getOctet() {
        StringBuilder octet = new StringBuilder();
        int last = ip.length() - 1;
        while (last >= 0 && ip.charAt(last) != delimiter) last -= 1;
        last += 1;
        while (last < ip.length() && ip.charAt(last) != delimiter)
            octet.append(ip.charAt(last++));
        return octet.toString();
    }
}
