package com.niagarasummit.nola;

import javax.baja.sys.BAbsTime;
import javax.baja.sys.BFacets;
import javax.baja.sys.BStruct;
import javax.baja.sys.Flags;
import javax.baja.sys.Property;
import javax.baja.sys.Sys;
import javax.baja.sys.Type;
import javax.baja.units.BUnit;

/**
 * Simple class to represent vital statistics of an alligator tracked by the
 * GatorTracker application.
 */
public class BGator extends BStruct
{
  /*-
  class BGator
  {
    properties
    {
      spawnTime: BAbsTime
        -- Time of creation
        flags { readonly }
        default {[ BAbsTime.make() ]}
      length: int
      	-- Gator length
        flags { readonly }
        default {[ 0 ]}
        slotfacets {[ BFacets.make(BFacets.UNITS, BUnit.getUnit("foot")) ]}
      weight: int
      	-- Gator weight
        flags { readonly }
        default {[ 0 ]}
        slotfacets {[ BFacets.make(BFacets.UNITS, BUnit.getUnit("pound")) ]}
      isRecord: boolean
        -- Is this a record?
        flags { readonly }
        default {[ false ]}
    }
  }
  -*/
/*+ ------------ BEGIN BAJA AUTO GENERATED CODE ------------ +*/
/*@ $com.niagarasummit.nola.BGator(3543631432)1.0$ @*/
/* Generated Wed May 11 14:31:30 EDT 2016 by Slot-o-Matic 2000 (c) Tridium, Inc. 2000 */

////////////////////////////////////////////////////////////////
// Property "spawnTime"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the <code>spawnTime</code> property.
   * Time of creation
   * @see com.niagarasummit.nola.BGator#getSpawnTime
   * @see com.niagarasummit.nola.BGator#setSpawnTime
   */
  public static final Property spawnTime = newProperty(Flags.READONLY, BAbsTime.make(),null);
  
  /**
   * Get the <code>spawnTime</code> property.
   * Time of creation
   * @see com.niagarasummit.nola.BGator#spawnTime
   */
  public BAbsTime getSpawnTime() { return (BAbsTime)get(spawnTime); }
  
  /**
   * Set the <code>spawnTime</code> property.
   * Time of creation
   * @see com.niagarasummit.nola.BGator#spawnTime
   */
  public void setSpawnTime(BAbsTime v) { set(spawnTime,v,null); }

////////////////////////////////////////////////////////////////
// Property "length"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the <code>length</code> property.
   * Gator length
   * @see com.niagarasummit.nola.BGator#getLength
   * @see com.niagarasummit.nola.BGator#setLength
   */
  public static final Property length = newProperty(Flags.READONLY, 0,BFacets.make(BFacets.UNITS, BUnit.getUnit("foot")));
  
  /**
   * Get the <code>length</code> property.
   * Gator length
   * @see com.niagarasummit.nola.BGator#length
   */
  public int getLength() { return getInt(length); }
  
  /**
   * Set the <code>length</code> property.
   * Gator length
   * @see com.niagarasummit.nola.BGator#length
   */
  public void setLength(int v) { setInt(length,v,null); }

////////////////////////////////////////////////////////////////
// Property "weight"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the <code>weight</code> property.
   * Gator weight
   * @see com.niagarasummit.nola.BGator#getWeight
   * @see com.niagarasummit.nola.BGator#setWeight
   */
  public static final Property weight = newProperty(Flags.READONLY, 0,BFacets.make(BFacets.UNITS, BUnit.getUnit("pound")));
  
  /**
   * Get the <code>weight</code> property.
   * Gator weight
   * @see com.niagarasummit.nola.BGator#weight
   */
  public int getWeight() { return getInt(weight); }
  
  /**
   * Set the <code>weight</code> property.
   * Gator weight
   * @see com.niagarasummit.nola.BGator#weight
   */
  public void setWeight(int v) { setInt(weight,v,null); }

////////////////////////////////////////////////////////////////
// Property "isRecord"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the <code>isRecord</code> property.
   * Is this a record?
   * @see com.niagarasummit.nola.BGator#getIsRecord
   * @see com.niagarasummit.nola.BGator#setIsRecord
   */
  public static final Property isRecord = newProperty(Flags.READONLY, false,null);
  
  /**
   * Get the <code>isRecord</code> property.
   * Is this a record?
   * @see com.niagarasummit.nola.BGator#isRecord
   */
  public boolean getIsRecord() { return getBoolean(isRecord); }
  
  /**
   * Set the <code>isRecord</code> property.
   * Is this a record?
   * @see com.niagarasummit.nola.BGator#isRecord
   */
  public void setIsRecord(boolean v) { setBoolean(isRecord,v,null); }

////////////////////////////////////////////////////////////////
// Type
////////////////////////////////////////////////////////////////
  
  public Type getType() { return TYPE; }
  public static final Type TYPE = Sys.loadType(BGator.class);

/*+ ------------ END BAJA AUTO GENERATED CODE -------------- +*/
  
  public BGator() {}
  
  public BGator(int l, int w)
  {
    setLength(l);
    setWeight(w);
  }
}
