package com.ovtijden;

public class RijksdriehoeksConverter {
    // Base RD coordinates Amersfoort
    public static final int BASE_RD_X = 155000;
    public static final int BASE_RD_Y = 463000;

    // Same base, but as wgs84 coordinates
    public static final double BASE_WGS_LAT = 52.15517440;
    public static final double BASE_WGS_LON = 5.38720621;

    /**
     * Converts RD coordinates to WGS84 latitude/longitude.
     * @param rdX Input RD X coordinate.
     * @param rdY Input RD Y coordinate.
     * @return WGS84 latitude/longitude.
     */
    public static double[] convertToLatLon(double rdX, double rdY) {
        // Coefficients
        double[][] Kpq = new double[5][4];
        Kpq[0][1] = 3235.65389;
        Kpq[2][0] = -32.58297;
        Kpq[0][2] = -0.24750;
        Kpq[2][1] = -0.84978;
        Kpq[0][3] = -0.06550;
        Kpq[2][2] = -0.01709;
        Kpq[1][0] = -0.00738;
        Kpq[4][0] = 0.00530;
        Kpq[2][3] = -0.00039;
        Kpq[4][1] = 0.00033;
        Kpq[1][1] = -0.00012;

        double[][] Lpq = new double[6][5];
        Lpq[1][0] = 5260.52916;
        Lpq[1][1] = 105.94684;
        Lpq[1][2] = 2.45656;
        Lpq[3][0] = -0.81885;
        Lpq[1][3] = 0.05594;
        Lpq[3][1] = -0.05607;
        Lpq[0][1] = 0.01199;
        Lpq[3][2] = -0.00256;
        Lpq[1][4] = 0.00128;
        Lpq[0][2] = 0.00022;
        Lpq[2][0] = -0.00022;
        Lpq[5][0] = 0.00026;

        double dLat = (rdX - BASE_RD_X) * 0.00001; // dX = (X - X0) 10^5
        double dLon = (rdY - BASE_RD_Y) * 0.00001; // dY = (Y - Y0) 10^5

        double calcLat = 0.00;
        double calcLon = 0.00;

        for (int p = 0; p < 5; p++) {
            for (int q = 0; q < 4; q++) {
                calcLat += Kpq[p][q] * (Math.pow(dLat, p)) * (Math.pow(dLon, q)); // φ = (Σp Σq Kpq dX^p dY^q)
                calcLon += Lpq[p][q] * (Math.pow(dLat, p)) * (Math.pow(dLon, q)); // λ = (Σp Σq Lpq dX^p dY^q)
            }
        }

        double latitude = BASE_WGS_LAT + (calcLat / 3600);  // φ = φ0 + (Σp Σq Kpq dX^p dY^q)/3600
        double longitude = BASE_WGS_LON + (calcLon / 3600); // λ = λ0 + (Σp Σq Lpq dX^p dY^q)/3600

        return new double[]{latitude, longitude};
    }

    /**
     * Converts WGS84 latitude/longitude to RD coordinates.
     * @param latitude Input WGS84 latitude.
     * @param longitude Input WGS84 longitude.
     * @return Rijksdriehoeksstelsel (RD) coordinates.
     */
    public static int[] convertToXY(double latitude, double longitude) {
        // Coefficients
        double[][] Rpq = new double[4][4];
        Rpq[0][1] = 190094.945;
        Rpq[1][1] = -11832.228;
        Rpq[2][1] = -114.221;
        Rpq[0][3] = -32.391;
        Rpq[1][0] = -0.705;
        Rpq[3][1] = -2.340;
        Rpq[1][3] = -0.608;
        Rpq[0][2] = -0.008;
        Rpq[2][3] = 0.148;

        double[][] Spq = new double[4][5];
        Spq[1][0] = 309056.544;
        Spq[0][2] = 3638.893;
        Spq[2][0] = 73.077;
        Spq[1][2] = -157.984;
        Spq[3][0] = 59.788;
        Spq[0][1] = 0.433;
        Spq[2][2] = -6.439;
        Spq[1][1] = -0.032;
        Spq[0][4] = 0.092;
        Spq[1][4] = -0.054;

        double dLat = (0.36 * (latitude - BASE_WGS_LAT));  // dφ = 0.36(φ - φ0)
        double dLon = (0.36 * (longitude - BASE_WGS_LON)); // dλ = 0.36(λ - λ0)

        int calcX = 0;
        int calcY = 0;

        for (int p = 0; p < 3; p++) {
            for (int q = 0; q < 4; q++) {
                calcX += Rpq[p][q] * (Math.pow(dLat, p)) * (Math.pow(dLon, q)); // Σp Σq Rpq dφ^p dλ^q
                calcY += Spq[p][q] * (Math.pow(dLat, p)) * (Math.pow(dLon, q)); // Σp Σq Spq dφ^p dλ^q
            }
        }

        int rdX = BASE_RD_X + calcX; // X = X0 + (Σp Σq Rpq dφ^p dλ^q)
        int rdY = BASE_RD_Y + calcY; // Y = Y0 + (Σp Σq Spq dφ^p dλ^q)

        return new int[]{ rdX, rdY };
    }
}
