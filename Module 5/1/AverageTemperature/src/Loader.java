public class Loader {
    private static final double HEALTHY_TEMP_MIN = 36.2;
    private static final double HEALTHY_TEMP_MAX = 36.9;

    public static void main(String[] args) {
        double[] tempeArray = new double[30];
        double averageTemp = 0;
        int healthyPatients = 0;
        int unHealthyPatients = 0;
        double sum = 0.0;

        for (int i = 0; i < tempeArray.length; i++) {
            double temperature = (Math.random() * 8) + 32;
            tempeArray[i] = temperature;
            sum += temperature;
            averageTemp = sum / tempeArray.length;
            if ( (tempeArray[i] <= HEALTHY_TEMP_MAX) && (tempeArray[i] >= HEALTHY_TEMP_MIN) )
            {
                healthyPatients++;
            }
            else {
                unHealthyPatients++;
            }
        }

        System.out.println("Healthy patients: " + healthyPatients);
        System.out.println("Unhealthy patients: " + unHealthyPatients);
        System.out.printf("%s %.2f ", "Average temperature: ", averageTemp);
    }
}
