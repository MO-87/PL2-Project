package Course_Management_System;

import java.io.*;
import java.util.*;
public class FileHandler {
    private String path;
    private FileWriter fw;
    private Scanner fr;

    public FileHandler() {
    }

    public FileHandler(String path) {
        this.path = path;
    }

    public void createFile() {
        try {
            File file = new File(path);
            file.createNewFile();
        } catch (IOException e) {
            System.out.println("An error occurred." + e.getMessage());
        }
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
        return "Nothing";
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
    public boolean update(String line, String updatedRaw) {
        try {
            StringBuilder content = new StringBuilder();
            String temp;
            this.fr = new Scanner(new File(path));
            while(fr.hasNextLine()) {
                temp = fr.nextLine();
                if(!temp.equals(line)){
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

    public String readLastLine() {
        String content = "";
        try {
            this.fr = new Scanner(new File(path));
            while(fr.hasNextLine()) {
                content = fr.nextLine();
            }
            fr.close();
            return content;
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred." + e.getMessage());
        }
        return content;
    }

    public boolean append(String line)  {
        try {
            this.fw = new FileWriter(path, true);
            fw.append(line).append("");
            fw.close();
        } catch (IOException e) {
            System.out.println("An error occurred." + e.getMessage());
            return false ;
        }
        return true;
    }

    public String readFile(){
        StringBuilder content = new StringBuilder();
        try {
            this.fr = new Scanner(new File(path));
            while(fr.hasNextLine()) {
                content.append("\n").append(fr.nextLine());
            }
            fr.close();
        } catch (FileNotFoundException e) {
            //System.out.println("An error occurred." + e.getMessage());
            return "error";
        }
        return content.toString();
    }
}
