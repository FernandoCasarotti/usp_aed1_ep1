package com.company;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {

    /* method to create a simple linked list with 3 nodes*/
    public static void main(String[] args) throws IOException {
        String[] input = args;
        int MAX  = 1000000;

        File dir = new File(System.getProperty("user.dir") + "/com/entradas");
        File[] directoryListing = dir.listFiles();
        if (directoryListing != null) {
            // Execute First Method
            if(input[0].equals("PilhaIngenua")){
                int[] x_axis = new int[99];
                long[] y_axis = new long[99];

                // Loop through every file and execute the test
                int filesExecuted = 0;
                for(File child: directoryListing) {
                    long startTime = System.currentTimeMillis();
                    String fileName = child.getName();

                    PilhaIngenua entranceList = new PilhaIngenua();
                    Integer[] finalList = new Integer[MAX];

                    // Open the file
                    File file = new File(System.getProperty("user.dir") + "/com/entradas/"+fileName);
                    // Every time the reader has a next line
                    int totalLines = 0;
                    try(BufferedReader br = new BufferedReader(new FileReader(file))) {
                        for(String line; (line = br.readLine()) != null; ) {
                            if (line.trim().isEmpty())
                                finalList[totalLines] = entranceList.remove();
                            else
                                // If the line has a content, include in the top of the list
                                entranceList.add(Integer.parseInt(line));
                            totalLines = totalLines + 1;
                        }
                        //Close the input stream
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    long estimatedTime = System.currentTimeMillis() - startTime;

                    y_axis[0] = estimatedTime;
                    x_axis[0] = totalLines;

                    Path exitFile = Paths.get("saida" + Integer.toString(totalLines) + ".txt");
                    List<Integer> list = new ArrayList<Integer>(Arrays.asList(finalList));
                    list.removeAll(Collections.singleton(null));
                    finalList = list.toArray(new Integer[list.size()]);
                    int[] intArray = Arrays.stream(finalList).mapToInt(Integer::intValue).toArray();
                    String strArray[] = Arrays.stream(intArray)
                            .mapToObj(String::valueOf)
                            .toArray(String[]::new);
                    List<String> strFinalList = Arrays.asList(strArray);
                    Files.write(exitFile, strFinalList, StandardCharsets.UTF_8);

                    filesExecuted = filesExecuted + 1;
                    // Execute Second Method
                }
                System.out.println(x_axis);
                System.out.println(y_axis);
            }else if(input[0].equals("PilhaLigada")){
                int[] x_axis = new int[99];
                long[] y_axis = new long[99];

                // Loop through every file and execute the test
                int filesExecuted = 0;
                for(File child: directoryListing) {
                    long startTime = System.currentTimeMillis();
                    String fileName = child.getName();

                    /* Start with two empty list: the one handling the entrance, and another
                     * handling the one's already processed */
                    StackAsLinkedList entranceList = new StackAsLinkedList();
                    Integer[] finalList = new Integer[MAX];

                    // Open the file
                    File file = new File(System.getProperty("user.dir") + "/com/entradas/"+fileName);
                    // Every time the reader has a next line
                    int totalLines = 0;
                    try(BufferedReader br = new BufferedReader(new FileReader(file))) {
                        for(String line; (line = br.readLine()) != null; ) {
                            if (line.trim().isEmpty())
                                finalList[totalLines] = entranceList.pop();
                            else
                                // If the line has a content, include in the top of the list
                                entranceList.push(Integer.parseInt(line));
                            totalLines = totalLines + 1;
                        }
                        //Close the input stream
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    long estimatedTime = System.currentTimeMillis() - startTime;

                    y_axis[0] = estimatedTime;
                    x_axis[0] = totalLines;

                    Path exitFile = Paths.get("saida" + Integer.toString(totalLines) + ".txt");
                    List<Integer> list = new ArrayList<Integer>(Arrays.asList(finalList));
                    list.removeAll(Collections.singleton(null));
                    finalList = list.toArray(new Integer[list.size()]);
                    int[] intArray = Arrays.stream(finalList).mapToInt(Integer::intValue).toArray();
                    String strArray[] = Arrays.stream(intArray)
                            .mapToObj(String::valueOf)
                            .toArray(String[]::new);
                    List<String> strFinalList = Arrays.asList(strArray);
                    Files.write(exitFile, strFinalList, StandardCharsets.UTF_8);

                    filesExecuted = filesExecuted + 1;
                }
                System.out.println(x_axis);
                System.out.println(y_axis);
            }
        }else{
            System.out.println(System.getProperty("user.dir"));
        }
    }

}