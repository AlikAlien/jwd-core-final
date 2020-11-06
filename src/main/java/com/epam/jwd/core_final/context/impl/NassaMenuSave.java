package com.epam.jwd.core_final.context.impl;
import com.epam.jwd.core_final.domain.ApplicationProperties;
import java.util.InputMismatchException;
import java.util.Scanner;

public class NassaMenuSave {
    static void nassaSave() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("FOR SAVE LIST MISSIONS PRINT FILE NAME FOR SAVE ");
        String fileName =null;
            try {
                fileName = scanner.nextLine();
            }
            catch (InputMismatchException e) {
                System.out.println("BAD INPUT. TRY AGAIN..");
                scanner.next();
                }
            JsonWriter.JSON_WRITER.jsonWriter(ApplicationProperties.APP_PROPERTIES.getOutputRootDir()+fileName);
    }
}