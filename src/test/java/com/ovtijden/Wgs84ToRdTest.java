package com.ovtijden;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Wgs84ToRdTest {
    @Test
    void testConvertAmsterdamCentraalStation() {
        double[] wgs84 = new double[]{ 52.37868843062727, 4.900504036982397 };
        int[] out = RijksdriehoeksConverter.convertToXY(wgs84[0], wgs84[1]);
        assertEquals(121863, out[0]);
        assertEquals(487977, out[1]);
    }

    @Test
    void testConvertDenHelderStation() {
        double[] wgs84 = new double[]{ 52.9568959680272, 4.760728065694023 };
        int[] out = RijksdriehoeksConverter.convertToXY(wgs84[0], wgs84[1]);
        assertEquals(112904, out[0]);
        assertEquals(552386, out[1]);
    }

    @Test
    void testConvertHeinoCentralStation() {
        double[] wgs84 = new double[]{ 52.427349629227116, 6.221430539185475 };
        int[] out = RijksdriehoeksConverter.convertToXY(wgs84[0], wgs84[1]);
        assertEquals(211735, out[0]);
        assertEquals(493606, out[1]);
    }

    @Test
    void testConvertValenceTgv() {
        double[] wgs84 = new double[]{ 44.99156059017286, 4.978527449051132 };
        int[] out = RijksdriehoeksConverter.convertToXY(wgs84[0], wgs84[1]);
        assertEquals(122659, out[0]);
        assertEquals(-333452, out[1]);
    }

    @Test
    void testConvertRzepin() {
        double[] wgs84 = new double[]{ 52.349864291055106, 14.805088702559294 };
        int[] out = RijksdriehoeksConverter.convertToXY(wgs84[0], wgs84[1]);
        assertEquals(795424, out[0]);
        assertEquals(526361, out[1]);
    }
}
