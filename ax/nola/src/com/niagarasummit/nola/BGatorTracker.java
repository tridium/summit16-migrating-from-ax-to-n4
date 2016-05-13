package com.niagarasummit.nola;

import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.baja.alarm.BAckState;
import javax.baja.alarm.BAlarmRecord;
import javax.baja.alarm.BAlarmService;
import javax.baja.alarm.BIAlarmSource;
import javax.baja.collection.BICollection;
import javax.baja.collection.BITable;
import javax.baja.control.BNumericPoint;
import javax.baja.file.BIFile;
import javax.baja.history.ext.BHistoryExt;
import javax.baja.history.ext.BNumericCovHistoryExt;
import javax.baja.log.Log;
import javax.baja.naming.BOrd;
import javax.baja.naming.BOrdList;
import javax.baja.status.BStatus;
import javax.baja.status.BStatusNumeric;
import javax.baja.status.BStatusValue;
import javax.baja.sys.Action;
import javax.baja.sys.BBoolean;
import javax.baja.sys.BComponent;
import javax.baja.sys.BDouble;
import javax.baja.sys.BFacets;
import javax.baja.sys.BString;
import javax.baja.sys.BValue;
import javax.baja.sys.Clock;
import javax.baja.sys.Context;
import javax.baja.sys.Cursor;
import javax.baja.sys.Flags;
import javax.baja.sys.Property;
import javax.baja.sys.Sys;
import javax.baja.sys.Topic;
import javax.baja.sys.Type;
import javax.baja.units.BUnit;
import javax.baja.util.Lexicon;

/**
 * Demonstration Class for Migrating a Niagara AX module to Niagara 4.
 * Used for Niagara Summit 2016 Developer Day.
 *
 */
public class BGatorTracker
  extends BNumericPoint
  implements BIAlarmSource
{

  /**
   * Make a history ext that's already enabled and ready to go.
   */
  private static BHistoryExt makeEnabledHistoryExt()
  {
	BHistoryExt ext = new BNumericCovHistoryExt();
	ext.setFlags(BHistoryExt.enabled, (ext.getFlags(BHistoryExt.enabled) & ~Flags.DEFAULT_ON_CLONE));
	ext.setEnabled(true);
	return ext;
  }
  /*-
  class BGatorTracker
  {
    properties
    {
      gatorName: String
        -- Name of most recently spawned alligator
        flags { summary }
        default {[ "Allie" ]}
        
      gatorLength: BStatusNumeric
        -- Length of most recently spawned alligator
        flags { summary }
        default {[ new BStatusNumeric(0, BStatus.nullStatus) ]}
        slotfacets {[ BFacets.make(BFacets.UNITS, BUnit.getUnit("foot")) ]}
        
      gatorWeight: BStatusNumeric
        -- Weight of most recently spawned alligator
        flags { summary }
        default {[ new BStatusNumeric(0, BStatus.nullStatus) ]}
        slotfacets {[ BFacets.make(BFacets.UNITS, BUnit.getUnit("pound")) ]}
      
      recordLength: double
        -- Current record for longest alligator
        default {[ 0 ]}
        slotfacets {[ BFacets.make(BFacets.UNITS, BUnit.getUnit("foot")) ]}
      
      gatorLogger: BHistoryExt
        -- Logs all the alligators we've tracked
        default {[ makeEnabledHistoryExt() ]}
    }
    actions
    {
      trackGator(gatorName: BString)
        -- Generate a new Gator and track it.
        default {[ BString.make("Allie") ]}
      
      findRecordGators() : BComponent
        -- Query the alarm database for the record-breaking gators.
      
      writeGatorList()
        -- Write the list of gators to a file.
        
      ackAlarm(ackRequest: BAlarmRecord) : BBoolean
        -- Acknowledge the alarm from this ack request
        flags { hidden }
        default {[ new BAlarmRecord() ]}
      
      displayNamesQuery()
      	-- Query for display names of child components
    }
    topics
    {
      newGator
    }
  }
  -*/
/*+ ------------ BEGIN BAJA AUTO GENERATED CODE ------------ +*/
/*@ $com.niagarasummit.nola.BGatorTracker(1222283827)1.0$ @*/
/* Generated Tue May 10 14:43:48 EDT 2016 by Slot-o-Matic 2000 (c) Tridium, Inc. 2000 */

////////////////////////////////////////////////////////////////
// Property "gatorName"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the <code>gatorName</code> property.
   * Name of most recently spawned alligator
   * @see com.niagarasummit.nola.BGatorTracker#getGatorName
   * @see com.niagarasummit.nola.BGatorTracker#setGatorName
   */
  public static final Property gatorName = newProperty(Flags.SUMMARY, "Allie",null);
  
  /**
   * Get the <code>gatorName</code> property.
   * Name of most recently spawned alligator
   * @see com.niagarasummit.nola.BGatorTracker#gatorName
   */
  public String getGatorName() { return getString(gatorName); }
  
  /**
   * Set the <code>gatorName</code> property.
   * Name of most recently spawned alligator
   * @see com.niagarasummit.nola.BGatorTracker#gatorName
   */
  public void setGatorName(String v) { setString(gatorName,v,null); }

////////////////////////////////////////////////////////////////
// Property "gatorLength"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the <code>gatorLength</code> property.
   * Length of most recently spawned alligator
   * @see com.niagarasummit.nola.BGatorTracker#getGatorLength
   * @see com.niagarasummit.nola.BGatorTracker#setGatorLength
   */
  public static final Property gatorLength = newProperty(Flags.SUMMARY, new BStatusNumeric(0, BStatus.nullStatus),BFacets.make(BFacets.UNITS, BUnit.getUnit("foot")));
  
  /**
   * Get the <code>gatorLength</code> property.
   * Length of most recently spawned alligator
   * @see com.niagarasummit.nola.BGatorTracker#gatorLength
   */
  public BStatusNumeric getGatorLength() { return (BStatusNumeric)get(gatorLength); }
  
  /**
   * Set the <code>gatorLength</code> property.
   * Length of most recently spawned alligator
   * @see com.niagarasummit.nola.BGatorTracker#gatorLength
   */
  public void setGatorLength(BStatusNumeric v) { set(gatorLength,v,null); }

////////////////////////////////////////////////////////////////
// Property "gatorWeight"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the <code>gatorWeight</code> property.
   * Weight of most recently spawned alligator
   * @see com.niagarasummit.nola.BGatorTracker#getGatorWeight
   * @see com.niagarasummit.nola.BGatorTracker#setGatorWeight
   */
  public static final Property gatorWeight = newProperty(Flags.SUMMARY, new BStatusNumeric(0, BStatus.nullStatus),BFacets.make(BFacets.UNITS, BUnit.getUnit("pound")));
  
  /**
   * Get the <code>gatorWeight</code> property.
   * Weight of most recently spawned alligator
   * @see com.niagarasummit.nola.BGatorTracker#gatorWeight
   */
  public BStatusNumeric getGatorWeight() { return (BStatusNumeric)get(gatorWeight); }
  
  /**
   * Set the <code>gatorWeight</code> property.
   * Weight of most recently spawned alligator
   * @see com.niagarasummit.nola.BGatorTracker#gatorWeight
   */
  public void setGatorWeight(BStatusNumeric v) { set(gatorWeight,v,null); }

////////////////////////////////////////////////////////////////
// Property "recordLength"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the <code>recordLength</code> property.
   * Current record for longest alligator
   * @see com.niagarasummit.nola.BGatorTracker#getRecordLength
   * @see com.niagarasummit.nola.BGatorTracker#setRecordLength
   */
  public static final Property recordLength = newProperty(0, 0,BFacets.make(BFacets.UNITS, BUnit.getUnit("foot")));
  
  /**
   * Get the <code>recordLength</code> property.
   * Current record for longest alligator
   * @see com.niagarasummit.nola.BGatorTracker#recordLength
   */
  public double getRecordLength() { return getDouble(recordLength); }
  
  /**
   * Set the <code>recordLength</code> property.
   * Current record for longest alligator
   * @see com.niagarasummit.nola.BGatorTracker#recordLength
   */
  public void setRecordLength(double v) { setDouble(recordLength,v,null); }

////////////////////////////////////////////////////////////////
// Property "gatorLogger"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the <code>gatorLogger</code> property.
   * Logs all the alligators we've tracked
   * @see com.niagarasummit.nola.BGatorTracker#getGatorLogger
   * @see com.niagarasummit.nola.BGatorTracker#setGatorLogger
   */
  public static final Property gatorLogger = newProperty(0, makeEnabledHistoryExt(),null);
  
  /**
   * Get the <code>gatorLogger</code> property.
   * Logs all the alligators we've tracked
   * @see com.niagarasummit.nola.BGatorTracker#gatorLogger
   */
  public BHistoryExt getGatorLogger() { return (BHistoryExt)get(gatorLogger); }
  
  /**
   * Set the <code>gatorLogger</code> property.
   * Logs all the alligators we've tracked
   * @see com.niagarasummit.nola.BGatorTracker#gatorLogger
   */
  public void setGatorLogger(BHistoryExt v) { set(gatorLogger,v,null); }

////////////////////////////////////////////////////////////////
// Action "trackGator"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the <code>trackGator</code> action.
   * Generate a new Gator and track it.
   * @see com.niagarasummit.nola.BGatorTracker#trackGator()
   */
  public static final Action trackGator = newAction(0,BString.make("Allie"),null);
  
  /**
   * Invoke the <code>trackGator</code> action.
   * Generate a new Gator and track it.
   * @see com.niagarasummit.nola.BGatorTracker#trackGator
   */
  public void trackGator(BString gatorName) { invoke(trackGator,gatorName,null); }

////////////////////////////////////////////////////////////////
// Action "findRecordGators"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the <code>findRecordGators</code> action.
   * Query the alarm database for the record-breaking gators.
   * @see com.niagarasummit.nola.BGatorTracker#findRecordGators()
   */
  public static final Action findRecordGators = newAction(0,null);
  
  /**
   * Invoke the <code>findRecordGators</code> action.
   * Query the alarm database for the record-breaking gators.
   * @see com.niagarasummit.nola.BGatorTracker#findRecordGators
   */
  public BComponent findRecordGators() { return (BComponent)invoke(findRecordGators,null,null); }

////////////////////////////////////////////////////////////////
// Action "writeGatorList"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the <code>writeGatorList</code> action.
   * Write the list of gators to a file.
   * @see com.niagarasummit.nola.BGatorTracker#writeGatorList()
   */
  public static final Action writeGatorList = newAction(0,null);
  
  /**
   * Invoke the <code>writeGatorList</code> action.
   * Write the list of gators to a file.
   * @see com.niagarasummit.nola.BGatorTracker#writeGatorList
   */
  public void writeGatorList() { invoke(writeGatorList,null,null); }

////////////////////////////////////////////////////////////////
// Action "ackAlarm"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the <code>ackAlarm</code> action.
   * Acknowledge the alarm from this ack request
   * @see com.niagarasummit.nola.BGatorTracker#ackAlarm()
   */
  public static final Action ackAlarm = newAction(Flags.HIDDEN,new BAlarmRecord(),null);
  
  /**
   * Invoke the <code>ackAlarm</code> action.
   * Acknowledge the alarm from this ack request
   * @see com.niagarasummit.nola.BGatorTracker#ackAlarm
   */
  public BBoolean ackAlarm(BAlarmRecord ackRequest) { return (BBoolean)invoke(ackAlarm,ackRequest,null); }

////////////////////////////////////////////////////////////////
// Action "displayNamesQuery"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the <code>displayNamesQuery</code> action.
   * Query for display names of child components
   * @see com.niagarasummit.nola.BGatorTracker#displayNamesQuery()
   */
  public static final Action displayNamesQuery = newAction(0,null);
  
  /**
   * Invoke the <code>displayNamesQuery</code> action.
   * Query for display names of child components
   * @see com.niagarasummit.nola.BGatorTracker#displayNamesQuery
   */
  public void displayNamesQuery() { invoke(displayNamesQuery,null,null); }

////////////////////////////////////////////////////////////////
// Topic "newGator"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the <code>newGator</code> topic.
   * @see com.niagarasummit.nola.BGatorTracker#fireNewGator
   */
  public static final Topic newGator = newTopic(0,null);
  
  /**
   * Fire an event for the <code>newGator</code> topic.
   * @see com.niagarasummit.nola.BGatorTracker#newGator
   */
  public void fireNewGator(BValue event) { fire(newGator, event, null); }

////////////////////////////////////////////////////////////////
// Type
////////////////////////////////////////////////////////////////
  
  public Type getType() { return TYPE; }
  public static final Type TYPE = Sys.loadType(BGatorTracker.class);

/*+ ------------ END BAJA AUTO GENERATED CODE -------------- +*/

  
////////////////////////////////////////////////////////////////
// BComponent Overrides
////////////////////////////////////////////////////////////////
  
  /**
   * Initialize the Gator Tracker.
   */
  public void started() throws Exception
  {
    super.started();
    random = new Random();
  }
  
  /**
   * Override of BNumericPoint execution.
   */
  public void onExecute(BStatusValue out, Context cx)
  {
    out.copyFrom(getGatorLength());
  }

  
////////////////////////////////////////////////////////////////
// Actions
////////////////////////////////////////////////////////////////
  
  /**
   * Track a new alligator.
   */
  public void doTrackGator(BString gatorName)
  {
    // Create a gator
    BGator gator = createGator();
    
    // Add it to the family
    add(gatorName.getString(), gator);
    BStatusNumeric sn = new BStatusNumeric(gator.getLength());
    setGatorLength(sn);
    sn = new BStatusNumeric(gator.getWeight());
    setGatorWeight(sn);
    setGatorName(gatorName.toString());

    // Check if it's a new record
    double gatorLen = gator.getLength();
    if (gatorLen > getRecordLength())
    {
      // Generate alarm
      Map alarmData = new HashMap();
      alarmData.put(GATOR_NAME, gatorName);
      alarmData.put(GATOR_LENGTH, getGatorLength().getValueValue());
      alarmData.put(GATOR_WEIGHT, getGatorWeight().getValueValue());
      alarmData.put(OLD_RECORD, (BDouble)get(recordLength));
      alarmData.put(NEW_RECORD, getGatorLength().getValueValue());
      BAlarmRecord rec = new BAlarmRecord();
      rec.setAlarmData(BFacets.make(alarmData));
      rec.setSource(BOrdList.make(getNavOrd()));
      getAlarmService().routeAlarm(rec);
      
      // Update the record
      setRecordLength(gatorLen);
      gator.setIsRecord(true);
    }
    
    // The frozen history extension automatically logs the new addition...
    execute();

    // Fire a topic so interested parties can take action
    fireNewGator(null);
  }

  
////////////////////////////////////////////////////////////////
// BIAlarmSource
////////////////////////////////////////////////////////////////

  public BBoolean doAckAlarm(BAlarmRecord rec)
  {
    if (!isRunning()) return BBoolean.FALSE;
    
    // Acknowledge the alarm
    rec.setAckTime(Clock.time());
    rec.setAckState(BAckState.acked);
    rec.setAckRequired(false);
    
    try
    {
      getAlarmService().routeAlarm(rec);
    }
    catch (Exception e)
    {
      log.error(MessageFormat.format(lex.getText("gatorTracker.couldNotAck"),
          new Object[] { rec }));
    }
    
    return BBoolean.TRUE;
  }

  
////////////////////////////////////////////////////////////////
// Alarm API Usage
////////////////////////////////////////////////////////////////

  /**
   * Find all the alarms (new gator length records)
   * See Developer Doc topic at module://docDeveloper/doc/alarm.html
   */
  public BComponent doFindRecordGators()
  {
    BComponent gatorList = new BComponent();
    try
    {
      log.message(lex.getText("gatorTracker.recordGators.msg"));
      BOrdList source = BOrdList.make(getNavOrd());
      BAlarmService alarmService = getAlarmService();

      // Niagara AX
      Cursor cursor = alarmService.getAlarmDb().getAlarmsForSource(source);
//      // Niagara 4
//      Cursor<BAlarmRecord> cursor;
//      try (AlarmDbConnection conn = alarmService.getAlarmDb().getDbConnection(null))
//      {
//        cursor = conn.getAlarmsForSource(source);
      int count = 0;
      while (cursor.next() & (++count < 10))
      {
        BAlarmRecord rec = (BAlarmRecord)cursor.get();
        BFacets alarmData = rec.getAlarmData();
        log.message(MessageFormat.format(
            lex.getText("gatorTracker.gatorStats"),
            new Object[] { alarmData.get(GATOR_LENGTH) }));
        gatorList.add(alarmData.gets(GATOR_NAME, null),
            new BGator(alarmData.geti(GATOR_LENGTH, 0), alarmData.geti(GATOR_WEIGHT, 0)));
      }
//      }
    }
    catch (Exception e)
    {
      log.error(MessageFormat.format(
          lex.getText("gatorTracker.couldNotQuery"),
          new Object[] { "record gators" }));
      
    }
    return gatorList;
  }

  
////////////////////////////////////////////////////////////////
// Collections Usage
////////////////////////////////////////////////////////////////
  
  /**
   * Query for display names: demonstration of Collections API changes
   * See Developer Doc topic at module://docDeveloper/doc/collections.html
   */
  public void doDisplayNamesQuery()
  {
    try
    {
      // Niagara AX
      BICollection result = (BICollection)BOrd.make("bql:select displayName").get(this);
      BITable table = result.toTable();
//      // Niagara 4
//      BITable<BObject> table = (BITable<BObject>)BOrd.make("bql:select displayName").get(this);

      StringBuffer sb = new StringBuffer();

      // Niagara AX
      for (int i=0; i<table.size(); i++)
//      // Niagara 4
//      Cursor<BObject> cursor = table.cursor();
//      while (cursor.next())

      {
        sb.append(MessageFormat.format(
          lex.getText("gatorTracker.displayNames.result.elem"),

          // Niagara AX
          new Object[] { table.get(i), table.get(i).getType() })).append('\n');
//          // Niagara 4
//          new Object[] { cursor.get(), cursor.get().getType() })).append('\n');
      }
      log.message(MessageFormat.format(
          lex.getText("gatorTracker.displayNames.result"),
          new Object[] { sb.toString() }));
    }
    catch (Exception e)
    {
      log.error(MessageFormat.format(
          lex.getText("gatorTracker.couldNotQuery"),
          new Object[] { "display names" }));
    }
  }

  
////////////////////////////////////////////////////////////////
// File write
////////////////////////////////////////////////////////////////

  /**
   * Query the alarm database for all of the record-breaking gators,
   * and write them to the gator list file.
   * This is a demonstration of the file writes.  Note that the caret (^)
   * symbol, referred to "Station Home" in Niagara AX as the folder with
   * the station's name.  In Niagara 4, it refers to "Station Home", but this
   * is now the folder called "shared" underneath of the folder with the station's
   * name.  The folder with the station's name is now called "Protected Station Home",
   * and is not generally accessible by user code.
   */
  public void doWriteGatorList()
  {
    try
    {
      BIFile file = (BIFile)BOrd.make("file:^gator/gatorList.txt").get();
      OutputStream out = file.getOutputStream();
      BufferedWriter w = new BufferedWriter(new OutputStreamWriter(out));
      w.write(lex.getText("gatorTracker.gatorList.title") + '\n');

      log.message(lex.getText("gatorTracker.recordGators.msg"));
      BOrdList source = BOrdList.make(getNavOrd());
      BAlarmService alarmService = getAlarmService();

      // Niagara AX
      Cursor cursor = alarmService.getAlarmDb().getAlarmsForSource(source);
//      // Niagara 4
//      Cursor<BAlarmRecord> cursor;
//      try (AlarmDbConnection conn = alarmService.getAlarmDb().getDbConnection(null))
//      {
//        cursor = conn.getAlarmsForSource(source);
      int count = 0;
      while (cursor.next() & (++count < 10))
      {
        BAlarmRecord rec = (BAlarmRecord)cursor.get();
        BFacets alarmData = rec.getAlarmData();
        w.write(MessageFormat.format(
            lex.getText("gatorTracker.gatorStats"),
            new Object[] { alarmData.get(GATOR_NAME), alarmData.get(GATOR_LENGTH), alarmData.get(GATOR_WEIGHT) }));
        w.write('\n');
      }
//    }

      w.flush();
      w.close();
    }
    catch (Exception e)
    {
      log.error(MessageFormat.format(
          lex.getText("gatorTracker.writeList.error"),
          new Object[] { e })); 
    }
  }
  
  
////////////////////////////////////////////////////////////////
// Private Utility
////////////////////////////////////////////////////////////////

  /**
   * Get a reference to the station's {@code BAlarmService}.
   * Only valid in a running station.
   * @return the alarm service.
   */
  private BAlarmService getAlarmService()
  {
    return (BAlarmService) BOrd.make("service:alarm:AlarmService").get();
  }
  
  
////////////////////////////////////////////////////////////////
// Gator Creation
////////////////////////////////////////////////////////////////

  private BGator createGator()
  {
    int gLen = random.nextInt(MAX_GATOR_LENGTH);
    int weightPerFoot = random.nextInt(GATOR_WEIGHT_RANGE) + BASE_GATOR_WEIGHT_PER_FOOT;
    int gWt = gLen * weightPerFoot;
    BGator g = new BGator(gLen, gWt);
    return g;
  }
  
  Random random;
  public static final int MAX_GATOR_LENGTH = 16;
  public static final int GATOR_WEIGHT_RANGE = 20;
  public static final int BASE_GATOR_WEIGHT_PER_FOOT = 60;
  
  
////////////////////////////////////////////////////////////////
// Constants and Instance Fields
////////////////////////////////////////////////////////////////
  
  private static Lexicon lex = Lexicon.make("nola");
  private static Log log = Log.getLog("nola");

  public static final String GATOR_NAME = "GatorName";
  public static final String GATOR_LENGTH = "GatorLength";
  public static final String GATOR_WEIGHT = "GatorWeight";
  public static final String OLD_RECORD = "OldRecord";
  public static final String NEW_RECORD = "NewRecord";
  
}
