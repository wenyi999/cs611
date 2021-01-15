import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.stream.Stream;

public class GameTool {//useful tools for developing the project
    public Stream<String> FileImporter(String fileName){//load the file from a certain path, handle with the io exception
        Path path = Paths.get(fileName);
        Stream<String> lines;
        try {
            lines = Files.lines(path);
        } catch (IOException e) {
            System.out.println(LegendGame.ANSI_RED+"The file path is not correct. Please re-enter the file path."+LegendGame.ANSI_RESET);
            Scanner sc=new Scanner(System.in);
            String newFilePath=sc.next();
            lines=FileImporter(newFilePath);
        }
        return lines;
    }
}
