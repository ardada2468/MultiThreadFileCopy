import java.io.*;
import java.util.Scanner;

public class ThreadReader {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String BLACK = "\u001B[30m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";

    private String FileName;
    private String data;

    public ThreadReader() {
        System.out.println("Enter File Name;");
        Scanner scanner = new Scanner(System.in);
        FileName = scanner.nextLine();
    }


    public void ReadData(){
        RunableThread WriterThread1 = new RunableThread(){
            @Override
            public void run() {
                System.out.println(CYAN + "Writing Data...");
                Scanner scanner = null;
                try {
                    scanner = new Scanner(new FileReader(FileName));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }finally {
                    System.out.println("Closing File");
                }

                scanner.useDelimiter(",");
                while (scanner.hasNext()){
                    data = data + scanner.next();
                }
                System.out.println(CYAN + "Completed Writing Data!");
            }
        };
        WriterThread1.run();
    }



    public void WriteData(){

        RunableThread WriterThread1 = new RunableThread(){
            @Override
            public void run() {
                System.out.println(PURPLE + "Please Enter a File name");
                Scanner scanner = new Scanner(System.in);
                String FileName2 = scanner.nextLine();
                FileWriter locFile = null;
                try {
                    locFile = new FileWriter(FileName2);
                    System.out.println("Writng Data");
                    locFile.write(data);
                } catch(IOException e) {
                    System.out.println("In catch block");
                    e.printStackTrace();
                } finally {
                    System.out.println("in finally block");
                    try {
                        if(locFile != null) {
                            System.out.println("Attempting to close " + FileName);
                            locFile.close();
                        }
                    } catch(IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        WriterThread1.run();


    }


    public void run(){
        ReadData();
        WriteData();
    }



}
