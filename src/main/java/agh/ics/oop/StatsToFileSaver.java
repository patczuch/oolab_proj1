package agh.ics.oop;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalTime;

public class StatsToFileSaver {
    private final String filename;
    private final SimulationEngine engine;

    static String width2(int number) {
        return String.format("%02d", number);
    }

    public StatsToFileSaver(SimulationEngine engine) throws IOException {
        this.engine = engine;

        String date = java.time.LocalDate.now().toString();
        LocalTime time = java.time.LocalTime.now();
        filename = "stats\\" + "stats_" + date + '_' + width2(time.getHour()) + '.' +
                width2(time.getMinute()) + '.' + width2(time.getSecond()) + ".csv";

        addHeaders();
    }

    private void writeToFile(String message) throws IOException {
        FileWriter fileWriter = new FileWriter(filename, true);
        fileWriter.write(message + "\n");
        fileWriter.close();
    }

    private void addHeaders() throws IOException {
        writeToFile("Day,LivingAnimals,Plants,FreeFields,PopularGenes,AverageEnergy,AverageLifeSpan,AllAnimals");
    }

    void appendStats() throws IOException {
        writeToFile(
                "" + engine.getCurrentDay() + ',' +
                engine.getLivingAnimalNumber() + ',' +
                engine.getPlantNumber() + ',' +
                engine.map.countFreeFields() + ',' +
                engine.getMostPopularGenes() + ',' +
                engine.averageEnergyLevel.getAverage() + ',' +
                engine.averageLifeSpan.getAverage() + ',' +
                engine.getAllAnimalNumber()
        );
    }
}
