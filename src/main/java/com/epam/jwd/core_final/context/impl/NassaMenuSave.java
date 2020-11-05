package com.epam.jwd.core_final.context.impl;

import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.factory.impl.MissionFactory;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import static com.epam.jwd.core_final.domain.ApplicationProperties.APP_PROPERTIES;

public class NassaMenuSave {
    static void nassaSave() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("FOR SAVE LIST MISSIONS PRINT FILE NAME FOR SAVE ");
        String fileName = null;
                try {
            fileName = scanner.next();
                    }
                catch (InputMismatchException e) {
                    System.out.println("BAD INPUT. TRY AGAIN..");
                    scanner.next();
                }
                JsonWriter.JSON_WRITER.jsonWriter(fileName);
    }
}
