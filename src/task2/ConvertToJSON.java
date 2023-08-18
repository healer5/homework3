package task2;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ConvertToJSON {
    public static void main(String[] args) throws IOException {
        String inputFile = "src/task2/file.txt";
        String outputFile = "src/task2/user.json";
        List<User> userList = readUserList(inputFile);
        writeUserList(userList,outputFile);

    }


    private static List<User> readUserList(String inputFile) throws IOException {
        List<User> userList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(inputFile))){
            br.readLine();
            String line;
            while ((line = br.readLine()) != null){
                String[] tokens = line.split(" ");
                if(tokens.length == 2){
                    userList.add(new User(tokens[0], Integer.parseInt((tokens[1]))));
                }
            }
        }
        return userList;
    }

    private static void writeUserList(List<User> userList, String outputFile) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(userList);
        try (FileWriter fileWriter = new FileWriter(outputFile)) {
            fileWriter.write(json);
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    private static class User{
        private String name;
        private int age;

        public User(String name,int age){
            this.name = name;
            this.age = age;
        }
    }
}