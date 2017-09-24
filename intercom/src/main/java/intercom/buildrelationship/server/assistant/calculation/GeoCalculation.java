package intercom.buildrelationship.server.assistant.calculation;

/**
 * Calculates the distance between two point on Geo sphere.
 * Created by ritesh on 9/23/17.
 */
public class GeoCalculation {

    /**
     * Calculates the distance between two points on a sphere.
     * @param officeLatitude The latitude of the intercom office (must be positive or zero).
     * @param officeLongitude The longitude of the intercom office (must be positive or zero).
     * @param customerLatitude The
     * @param customerLongitude
     * @return
     */
    public static int calculateDistance(final double officeLatitude, final double officeLongitude, final double customerLatitude, final double customerLongitude) {

        double theDistance = (Math.sin(Math.toRadians(officeLatitude)) *
                Math.sin(Math.toRadians(customerLatitude)) +
                Math.cos(Math.toRadians(officeLatitude)) *
                        Math.cos(Math.toRadians(customerLatitude)) *
                        Math.cos(Math.toRadians(officeLongitude - customerLongitude)));

        return new Double((Math.toDegrees(Math.acos(theDistance))) * 69.09).intValue();
    }
}
