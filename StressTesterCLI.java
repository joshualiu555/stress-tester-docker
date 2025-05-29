import java.io.*;
import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

public class StressTesterCLI {
    public static void main(String[] args) throws IOException {
        if (args.length < 3) {
            System.out.println("Please input 3 files - Generator, Solution, Brute Force");
            return;
        }

        String generatorPath = args[0];
        String solutionPath = args[1];
        String bruteForcePath = args[2];

        compileFile("Generator.java");
        compileFile("Solution.java");
        compileFile("BruteForce.java");

        int testCase = 1;
        while (true) {
            StringBuilder generatorOutput = runGenerator("Generator");
            StringBuilder solutionOutput = runCode("Solution", generatorOutput.toString());
            StringBuilder bruteForceOutput = runCode("BruteForce", generatorOutput.toString());

            if (solutionOutput.toString().trim().equals(bruteForceOutput.toString().trim())) {
                System.out.println("Test Case " + testCase + " passed");
            } else {
                System.out.println("Test Case " + testCase + " Failed");
                System.out.println("Your Solution:\n" + solutionOutput);
                System.out.println("Correct Solution:\n" + bruteForceOutput);
                break;
            }
            testCase++;
        }
    }

    private static void compileFile(String fileName) {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        compiler.run(null, null, null, fileName);
    }

    private static StringBuilder runGenerator(String className) throws IOException {
        ProcessBuilder pb = new ProcessBuilder("java", className);
        Process process = pb.start();
        return createOutput(process);
    }

    private static StringBuilder runCode(String className, String generatorOutput) throws IOException {
        ProcessBuilder processBuilder = new ProcessBuilder("java", className);
        Process process = processBuilder.start();
        OutputStream outputStream = process.getOutputStream();
        outputStream.write(generatorOutput.toString().getBytes());
        outputStream.close();
        return createOutput(process);
    }

    private static StringBuilder createOutput(Process process) throws IOException {
        InputStream inputStream = process.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder output = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            output.append(line).append("\n");
        }
        return output;
    }
}
