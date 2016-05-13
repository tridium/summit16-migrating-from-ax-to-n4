package test.com.niagarasummit.nola;

import javax.baja.nre.annotations.NiagaraType;
import javax.baja.sys.BComponent;
import javax.baja.sys.BStation;
import javax.baja.sys.BString;
import javax.baja.sys.BValue;
import javax.baja.sys.Sys;
import javax.baja.sys.Type;
import javax.baja.test.BTestNg;
import com.niagarasummit.nola.BGator;
import com.niagarasummit.nola.BGatorTracker;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * BTrackGatorTest
 * Unit tests to make sure the Gator Tracker behaves correctly.
 */
@NiagaraType
public class BTrackGatorTest
  extends BTestNg
{
/*+ ------------ BEGIN BAJA AUTO GENERATED CODE ------------ +*/
/*@ $test.com.niagarasummit.nola.BTrackGatorTest(2979906276)1.0$ @*/
/* Generated Tue May 10 15:18:05 EDT 2016 by Slot-o-Matic (c) Tridium, Inc. 2012 */

////////////////////////////////////////////////////////////////
// Type
////////////////////////////////////////////////////////////////
  
  @Override
  public Type getType() { return TYPE; }
  public static final Type TYPE = Sys.loadType(BTrackGatorTest.class);

/*+ ------------ END BAJA AUTO GENERATED CODE -------------- +*/
  private BGatorTracker gatorTracker;

  /**
   * Before the tests, we need to start a station with the gator tracker in it to ensure
   * that the tracker is running in the proper environment.
   */
  @BeforeTest
  @Override
  public void setup() throws Exception
  {
    // Create a test station handler
    if (stationHandler == null)
    {
      stationHandler = createTestStation();
    }

    // Configure it with the necessary services.
    BStation station = stationHandler.getStation();
    configureTestStation(station, testStationName);

    // Start the test station
    stationHandler.startStation();

    // Add the gator tracker.
    // Ensure no alarms or history records are generated for this test.
    gatorTracker = new BGatorTracker();
    gatorTracker.setRecordLength(100);
    gatorTracker.getGatorLogger().setEnabled(false);
    station.add(null, gatorTracker);

  }

  /**
   * Make sure to clean up the station resources we started.
   */
  @AfterTest
  @Override
  public void cleanup()
  {
    // Clean up the handler
    if (stationHandler != null)
    {
      // First, stop the test station
      stationHandler.stopStation();

      // Then release its resources
      stationHandler.releaseStation();
      stationHandler = null;
    }

    // Also clear the gator tracker reference
    gatorTracker = null;
  }

  @Test
  /**
   * Ensure that the new gator addition works.
   */
  public void addGator()
  {
    BGator[] gators = gatorTracker.getChildren(BGator.class);
    int beforeLen = gators.length;

    gatorTracker.doTrackGator(BString.make("Allie"));

    gators = gatorTracker.getChildren(BGator.class);
    int afterLen = gators.length;

    // Should be one more gator than before
    Assert.assertEquals(afterLen, beforeLen + 1, "after should have one more gator than before");

    // Should be able to find a child named "Allie" of Type BGator
    BValue gator = gatorTracker.get("Allie");
    Assert.assertNotNull(gator, "'Allie' property of gatorTracker should not be null");
    Assert.assertTrue(gator instanceof BGator, "gator should be an instance of BGator");
  }


////////////////////////////////////////////////////////////////
// Support
////////////////////////////////////////////////////////////////

  /**
   * Here we need to add any services the GatorTracker expects to interact with.
   * @param station reference to station root
   * @param stationName station name
   * @throws Exception
   */
  private void configureTestStation(BStation station, String stationName)
    throws Exception
  {
    BComponent services = station.getServices();
    services.add(ALARM_SERVICE, newInstance("alarm:AlarmService"));

    station.setStationName(stationName);
  }

  /**
   * Convenience method for adding new instance of a specified {@link Type}.
   * @param type typespec to be created
   * @return a new instance of that Type
   * @throws Exception
   */
  public BComponent newInstance(String type) throws Exception
  {
    return (BComponent) Sys.getType(type).getInstance();
  }


////////////////////////////////////////////////////////////////
// Attributes
////////////////////////////////////////////////////////////////

  protected static final String ALARM_SERVICE = "AlarmService";
  protected static final String HISTORY_SERVICE = "HistoryService";
  protected static String testStationName = "nolaTest";

  private TestStationHandler stationHandler;
}
