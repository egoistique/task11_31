package ru.vsu.cs.course1;

import org.apache.commons.cli.CommandLine;
import ru.vsu.cs.util.ArrayUtils;
import ru.vsu.cs.util.SwingUtils;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;


public class Program {

    public static class CmdParams {
        public String inputFile;
        public String outputFile;
        public boolean reverseRows;
        public boolean reverseColumns;
        public boolean error;
        public boolean help;
        public boolean window;
    }

    public static CmdParams parseArgs(String[] args) {
        CmdParams params = new CmdParams();
        int[] pos = {0, 0};
        if (args.length > 0) {
            if (args[0].equals("--help")) {
                params.help = true;
                return params;
            }
            if (args[0].equals("--window")) {
                params.window = true;
                return params;
            }
            if (!args[0].equals("-i") && !args[0].equals("-o")) {
                params.error = true;
                params.help = true;
                return params;
            }
            if (args.length < 2) {
                params.help = true;
                params.error = true;
                return params;
            }

            for (int i = 0; i < args.length; i++) {
                if (args[i].equals("-i")) {
                    pos[0] = i + 1;
                }
                if (args[i].equals("-o")) {
                    pos[1] = i + 1;
                }
            }
            params.inputFile = args[pos[0]];
            if (args.length > 2) {
                params.outputFile = args[pos[1]];
            }
        }
        return params;
    }

    public static void winMain() throws Exception {
        //SwingUtils.setLookAndFeelByName("Windows");
        Locale.setDefault(Locale.ROOT);
        //UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        SwingUtils.setDefaultFont("Microsoft Sans Serif", 18);

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrameMain().setVisible(true);
            }
        });
    }

    public static String readFile(String fileName) throws FileNotFoundException {
        String fileData;
        try (Scanner scanner = new Scanner(new File(fileName), "UTF-8")) {
            fileData = new Scanner(new File(fileName)).useDelimiter("\\Z").next();
        }
        // обязательно, чтобы закрыть открытый файл
        return fileData;
    }

    public static void main(String[] args) throws Exception {
        CmdParams params = parseArgs(args);
        if (params.help) {
            PrintStream out = params.error ? System.err : System.out;
            out.println("Usage:");
            out.println("  <cmd> args <input-file> (<output-file>)");
            out.println("  <cmd> --help");
            out.println("  <cmd> --window  // show window");
            System.exit(params.error ? 1 : 0);
        }
        if (params.window) {
            winMain();
        } else {
            String[] text = ArrayUtils.readLinesFromFile(params.inputFile);
            String res = Task.arrayToString(text);
            if (text == null) {
                System.err.printf("Can't read array from \"%s\"%n", params.inputFile);
                System.exit(2);
            }
            PrintStream out = (params.outputFile != null) ? new PrintStream(params.outputFile) : System.out;
            out.println(Task.longestSentence(res));
            out.close();
        }
    }
}
