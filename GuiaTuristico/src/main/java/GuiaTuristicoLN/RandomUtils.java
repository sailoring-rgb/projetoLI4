package GuiaTuristicoLN;

import java.util.StringTokenizer;

public final class RandomUtils {

    public static double parseLatitude(String location){
        String delimitador = " ";
        StringTokenizer tokens = new StringTokenizer(location, delimitador);
        return Double.parseDouble(tokens.nextToken());
    }

    public static double parseLongitude(String location){
        String delimitador = " ";
        StringTokenizer tokens = new StringTokenizer(location, delimitador);
        tokens.nextToken();
        return Double.parseDouble(tokens.nextToken());
    }

    public static String convertLocation(double latitude, double longitude){
        StringBuilder sb = new StringBuilder();
        sb.append(latitude).append(" ").append(longitude);
        return sb.toString();
    }
}