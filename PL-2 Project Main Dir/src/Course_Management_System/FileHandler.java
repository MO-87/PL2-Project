package Course_Management_System;

import java.io.*;
import java.util.*;
public class FileHandler {
    private final String path;
    private FileWriter fw;
    private Scanner fr;

    public FileHandler(String path) {
        this.path = path;
    }

    public String readLine(int ID) throws IOException {
        String content;

        this.fr = new Scanner(new File(path));
        while(fr.hasNextLine()) {
            content = fr.nextLine();
            if(content.startsWith(Integer.toString(ID)))
                return content;
        }
        fr.close();
        return "Employee does not exist";
    }
    public boolean delete(String s) {
        try {
            StringBuilder content = new StringBuilder();
            String temp;
            this.fr = new Scanner(new File(path));
            while(fr.hasNextLine()) {
                temp = fr.nextLine();
                if(!temp.equals(s)){
                    content.append(temp).append("\n");
                }
            }
            fr.close();
            this.fw = new FileWriter(path, false);
            fw.write(content.toString());
            fw.close();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    public boolean update(String s, String updatedRaw) {
        try {
            StringBuilder content = new StringBuilder();
            String temp;
            this.fr = new Scanner(new File(path));
            while(fr.hasNextLine()) {
                temp = fr.nextLine();
                if(!temp.equals(s)){
                    content.append(temp).append("\n");
                }
                else {
                    content.append(updatedRaw).append("\n");
                }
            }
            fr.close();
            this.fw = new FileWriter(path, false);
            fw.write(content.toString());
            fw.close();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public String readFromFile() {
        StringBuilder fileContent = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                fileContent.append(line).append("\n");
            }
        } catch (IOException e) {
            System.err.println("Error reading from file: " + e.getMessage());
        }
        return fileContent.toString();
    }
}
