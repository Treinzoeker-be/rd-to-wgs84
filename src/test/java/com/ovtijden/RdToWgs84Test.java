package com.ovtijden;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RdToWgs84Test {
    @Test
    void testConvertHilversumStation() {
        int[] rd = new int[]{ 140930, 470940 };
        double[] out = RijksdriehoeksConverter.convertToLatLon(rd[0], rd[1]);
        assertEquals(52.226358844791086, out[0]);
        assertEquals(5.181278713584198, out[1]);
    }

    @Test
    void testConvertVlisingenStation() {
        int[] rd = new int[]{ 30430, 385460 };
        double[] out = RijksdriehoeksConverter.convertToLatLon(rd[0], rd[1]);
        assertEquals(51.44445843527988, out[0]);
        assertEquals(3.595256054465616, out[1]);
    }

    @Test
    void testConvertEemshavenStation() {
        int[] rd = new int[]{ 250980, 608980 };
        double[] out = RijksdriehoeksConverter.convertToLatLon(rd[0], rd[1]);
        assertEquals(53.45836346039857, out[0]);
        assertEquals(6.832180602871414, out[1]);
    }

    @Test
    void testConvertMaastrichtStation() {
        int[] rd = new int[]{ 177420, 317840 };
        double[] out = RijksdriehoeksConverter.convertToLatLon(rd[0], rd[1]);
        assertEquals(50.849958980426955, out[0]);
        assertEquals(5.705547104453776, out[1]);
    }

    @Test
    void testConvertAirportCdgParis() {
        int[] rd = new int[]{ -51170, 116320 };
        double[] out = RijksdriehoeksConverter.convertToLatLon(rd[0], rd[1]);
        assertEquals(49.00397996294752, out[0]);
        assertEquals(2.5708892551875593, out[1]);
    }

    @Test
    void testPrahaStation() {
        int[] rd = new int[]{ 801630, 272570 };
        double[] out = RijksdriehoeksConverter.convertToLatLon(rd[0], rd[1]);
        assertEquals(50.08539997271968, out[0]);
        assertEquals(14.434870414884676, out[1]);
    }
}
