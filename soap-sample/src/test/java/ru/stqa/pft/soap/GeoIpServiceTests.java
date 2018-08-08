package ru.stqa.pft.soap;

import com.lavasoft.GeoIPService;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class GeoIpServiceTests {

   @Test
   public void testMyIp() {
      String ipLocation = new GeoIPService().getGeoIPServiceSoap12().getIpLocation20("109.122.60.140");
      assertTrue(ipLocation.contains("<Country>UA</Country>"));
   }

   @Test
   public void testInvalidIp() {
      String ipLocation = new GeoIPService().getGeoIPServiceSoap12().getIpLocation20("109.122.60.xxx");
      assertTrue(ipLocation == null);
   }
}
