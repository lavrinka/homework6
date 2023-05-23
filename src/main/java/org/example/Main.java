package org.example;

import org.epam.service.SendServiceInConsole;
import org.epam.service.SendServiceInFile;

public class Main {

  public static void main(String[] args) {
    System.out.println("Try to send message");

    if (args.length == 2) {
      String input = args[0];
      String output = args[1];
      /*
        input = "src/test/resources/fileWithLatin.txt";
        output = "test.txt";
       */
      SendServiceInFile sendServiceInFile = new SendServiceInFile();
      sendServiceInFile.sendMessage(input, output);
    } else if (args.length == 0) {
      SendServiceInConsole sendServiceInConsole = new SendServiceInConsole();
      sendServiceInConsole.sendMessage();
    } else {
      System.out.println("Wrong number of arguments");
    }
  }
}