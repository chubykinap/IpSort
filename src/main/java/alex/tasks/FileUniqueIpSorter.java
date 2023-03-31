package alex.tasks;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.BitSet;
import java.util.stream.Stream;

public class FileUniqueIpSorter implements UniqueIpSorter {
    private final BitSet first_half = new BitSet(Integer.MAX_VALUE);
    private final BitSet second_half = new BitSet(Integer.MAX_VALUE);
    private long counter = 0;

    @Override
    public long countUniqueIp(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(getFilePath(fileName)))) {
            String line;
            while ((line = br.readLine()) != null && counter <= MAX_VALUE_OF_IP) {
                registerIpValue(UniqueIpSorter.transformValue(line));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return counter;
    }

    private void registerIpValue(long val) {
        BitSet list;
        if (val > Integer.MAX_VALUE) {
            list = first_half;
            val -= Integer.MAX_VALUE;
        } else
            list = second_half;

        int tmp = (int) val;
        if (!list.get(tmp)) {
            list.set(tmp);
            counter++;
        }
    }

    private String getFilePath(String fileName) {
        try (Stream<Path> s = Files.find(Paths.get(
                System.getProperty("user.dir")),
                3,
                (path, attr) -> path.getFileName().toString().equals(fileName))) {
            Path path = s.findFirst().orElse(null);
            if (path == null)
                throw new FileNotFoundException("File " + fileName + " not found.");
            return path.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
