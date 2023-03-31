package alex.tasks;

public interface UniqueIpSorter {
    long MAX_VALUE_OF_IP = 256L * 256 * 256 * 256;

    long countUniqueIp(String fileName);

    static long transformValue(String strIp) {
        long result = 0;

        String[] ipSplit = strIp.split("\\.");

        for (int i = 3; i >= 0; i--) {
            long ip = Long.parseLong(ipSplit[3 - i]);
            result |= ip << (i * 8);
        }

        return result;
    }
}
