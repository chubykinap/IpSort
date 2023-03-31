package alex.tasks;

public class Main {
    public static void main(String[] args) {
        try {
            String fileName = parseArguments(args);
            long start = System.currentTimeMillis();

            long count = new FileUniqueIpSorter().countUniqueIp(fileName);

            long end = System.currentTimeMillis();
            System.out.printf("Found %d unique ip values in file %s in %d seconds",
                    count,
                    fileName,
                    (end - start) / 1000);
        } catch (Exception e) {
            System.out.printf("Exception occurred while running the program: %s\nCause:\n%s",
                    e.getMessage(),
                    e.getCause());
        }
    }

    private static String parseArguments(String[] args) throws Exception {
        if (args.length == 2 && args[0].equals("-file"))
            return args[1];
        else
            throw new Exception("Wrong arguments\nSpecify argument '-file file_name' to access file.");
    }
}
