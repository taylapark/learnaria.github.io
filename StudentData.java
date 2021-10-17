/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author taylajadepark
 */
public class StudentData {
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Scanner;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class StudentDataReader {
        public static void main(String[] args) {
                try {
                        String URL = "https://hccs-advancejava.s3.amazonaws.com/student.json";
                        String output = getUrlContents(URL);
                        System.out.println(output);
                        
                        ObjectMapper objectMapper = new ObjectMapper();
                        List<Student> students = objectMapper.readValue(output, new TypeReference<List<Student>>(){});
                        System.out.println(students.size());
                        
                        Scanner scanner = new Scanner(System.in);
                        System.out.println("Enter a name to search for: ");
                        String searchName = scanner.nextLine();
                        for (Student student: students) {
                                if (searchName.equalsIgnoreCase(student.getFirst_name())) {
                                        System.out.println(student);
                                }
                        }
                } catch (Exception e) {
                        e.printStackTrace();
                }
        }

        private static String getUrlContents(String jsonUrl) {
                StringBuilder result = new StringBuilder();
                try {
                        URL url = new URL(jsonUrl); 
                        URLConnection connection = url.openConnection();

                        BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                        String line;
                        while ((line = br.readLine()) != null) {
                                result.append(line + "\n");
                        }
                        br.close();
                } catch (Exception e) {
                        e.printStackTrace();
                }
                return result.toString();
        }
}

