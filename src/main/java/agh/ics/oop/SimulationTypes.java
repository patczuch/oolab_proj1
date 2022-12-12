package agh.ics.oop;

public class SimulationTypes {
    public enum PlantGrowingType {
        TOXICDEAD("Toksyczne trupy"),
        FORESTYEQUATORS("Zalesione równiki");
        final String name;
        PlantGrowingType(String name) {
            this.name = name;
        }
        public String toString()
        {
            return this.name;
        }
    }
    public enum AnimalMutationType {
        FULLYRANDOM("Pełna losowość"),
        LESSRANDOM("Lekka korekta");
        final String name;
        AnimalMutationType(String name) {
            this.name = name;
        }
        public String toString()
        {
            return this.name;
        }
    }
    public enum AnimalBehaviourType {
        FULLYDETERMINED("Pełna predestynacja"),
        SLIGHTLYCRAZY("Nieco szaleństwa");
        final String name;
        AnimalBehaviourType(String name) {
            this.name = name;
        }
        public String toString()
        {
            return this.name;
        }
    }
    public enum MapType {
        EARTH("Kula ziemska"),
        HELLPORTAL("Piekielny portal");
        final String name;
        MapType(String name) {
            this.name = name;
        }
        public String toString()
        {
            return name;
        }
    }
}
