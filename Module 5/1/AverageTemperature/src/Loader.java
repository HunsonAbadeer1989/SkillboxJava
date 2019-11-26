import java.util.Random;

public class Loader
{
    public static void main(String[] args)
    {
        double[] tempeArray = new double[30];
        double healthyTempMin = 36.2;
        double healthyTempMax = 36.9;
        double averageTemp = 0;
        int healthyPatients = 0;
        int unHealthyPatients = 0;

        for (int i=0; i < tempeArray.length; i++)
        {
            double temperature = ( Math.random() * 8 ) + 32;
            tempeArray[i] = temperature;
            if( (tempeArray[i] <= healthyTempMax) && (tempeArray[i] >= healthyTempMin))
            {
                healthyPatients++;
            }
            else unHealthyPatients++;
        }

        for (double d: tempeArray){
            System.out.println(d);
        }
        System.out.println("Healthy patients: " + healthyPatients);
        System.out.println("Unhealthy patients: " + unHealthyPatients);
    }
}
