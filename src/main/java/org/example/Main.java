package org.example;

import org.epam.service.SendServiceInConsole;
import org.epam.service.SendServiceInFile;

public class Main {

  public static void main(String[] args) {
    System.out.println("Try to send message");

    SendServiceInConsole sendServiceInConsole = new SendServiceInConsole();
    sendServiceInConsole.sendMessage();

    SendServiceInFile sendServiceInFile = new SendServiceInFile();
    String input = "src/test/resources/fileWithLatin.txt";
    String output = "test.txt";
    sendServiceInFile.sendMessage(input, output);

  }
}