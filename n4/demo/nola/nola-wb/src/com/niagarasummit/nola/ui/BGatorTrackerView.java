package com.niagarasummit.nola.ui;

import javax.baja.gx.BInsets;
import javax.baja.sys.BComponentEvent;
import javax.baja.sys.BObject;
import javax.baja.sys.BString;
import javax.baja.sys.Clock;
import javax.baja.sys.Context;
import javax.baja.sys.Sys;
import javax.baja.sys.Type;
import javax.baja.ui.BAbstractButton;
import javax.baja.ui.BDialog;
import javax.baja.ui.BWidget;
import javax.baja.ui.Command;
import javax.baja.ui.CommandArtifact;
import javax.baja.ui.pane.BBorderPane;
import javax.baja.ui.pane.BEdgePane;
import javax.baja.ui.pane.BGridPane;
import javax.baja.ui.table.BTable;
import javax.baja.ui.table.DefaultTableModel;
import javax.baja.util.Lexicon;
import javax.baja.workbench.view.BWbComponentView;

import com.niagarasummit.nola.BGator;
import com.niagarasummit.nola.BGatorTracker;
import javax.baja.nre.annotations.NiagaraType;
import javax.baja.nre.annotations.AgentOn;

/**
 * Simple view for listing the alligators that have been tracked.
 */
@NiagaraType(
  agent =   @AgentOn(
    types = "nola:GatorTracker",
    requiredPermissions = "r"
  )
)
public class BGatorTrackerView extends BWbComponentView
{
  

/*+ ------------ BEGIN BAJA AUTO GENERATED CODE ------------ +*/
/*@ $com.niagarasummit.nola.ui.BGatorTrackerView(1311275354)1.0$ @*/
/* Generated Wed May 11 16:11:28 EDT 2016 by Slot-o-Matic (c) Tridium, Inc. 2012 */

////////////////////////////////////////////////////////////////
// Type
////////////////////////////////////////////////////////////////
  
  @Override
  public Type getType() { return TYPE; }
  public static final Type TYPE = Sys.loadType(BGatorTrackerView.class);

/*+ ------------ END BAJA AUTO GENERATED CODE -------------- +*/
  
  /**
   * Constructor.
   * Build the table and buttons.
   */
  public BGatorTrackerView()
  {
    BEdgePane content = new BEdgePane();
    
    model = new DefaultTableModel(colNames);
    table = new BTable(model);
    
    BGridPane buttons = makeButtonPane();
    
    content.setCenter(table);
    content.setBottom(new BBorderPane(buttons, 5, 5, 5, 5));
    setContent(content);
  }

  /**
   * Initialization hook from BWbEditor.
   * Load the information into the ui widgets and set initial state of ui
   * activation components like buttons.
   */
  public void doLoadValue(BObject value, Context context)
  {
    tracker = (BGatorTracker)value;
    loadGators();
    enableButtons();
  }

  /**
   * Load the list of record-breaking alligators from the alarm db query.
   */
  private void loadGators()
  {
    BGator[] list = (BGator[])tracker.getChildren(BGator.class);
    for (int i=0; i<list.length; i++)
    {
      model.addRow(new Object[] { Clock.time(), list[i].getName(), list[i].get(BGator.length), list[i].get(BGator.weight) });
    }
  }
  
  /**
   * Set the enable status of the buttons.
   * Should be based on selection validation.
   */
  private void enableButtons()
  {
    addCmd.setEnabled(true);
    removeCmd.setEnabled(true);
  }

  /**
   * Update the table when a new record gator is tracked.
   */
  public void handleComponentEvent(BComponentEvent event)
  {
    if (event.getId() == BComponentEvent.TOPIC_FIRED)
    {
      enableButtons();
      model.addRow(new Object[] {
          Clock.time(), tracker.getGatorName(),
          tracker.getGatorLength(), tracker.getGatorWeight() });
    }
    
  }
  
  /**
   * Build the button pane
   * @return a BGridPane with the buttons
   */
  private BGridPane makeButtonPane()
  {
    BInsets insets = BInsets.make(5,5,5,5);
    BGridPane buttonPane = new BGridPane();
    addCmd = new Add(this);
    removeCmd = new Remove(this);
    bAdd = BAbstractButton.make(addCmd);
    bRemove = BAbstractButton.make(removeCmd);
    bAdd.setPadding(insets);
    bRemove.setPadding(insets);
    buttonPane.add("bAdd", bAdd);
    buttonPane.add("bRemove", bRemove);
    buttonPane.setColumnCount(2);
    return buttonPane;
  }
  

////////////////////////////////////////////////////////////////
// Add
////////////////////////////////////////////////////////////////

  class Add extends Command
  {
    Add(BWidget owner)
    {
      super(owner, lex, "gatorView.add");
    }
    public CommandArtifact doInvoke() throws Exception { return doAdd(); }
  }
  
  public CommandArtifact doAdd()
  {
    String gatorName = "";
    gatorName = BDialog.prompt(this, lex.getText("gatorView.name.title"), gatorName, 40);
    tracker.trackGator(BString.make(gatorName));
    model.updateTable();
    return null;
  }
  

////////////////////////////////////////////////////////////////
// Remove
////////////////////////////////////////////////////////////////
  
  class Remove extends Command
  {
    Remove(BWidget owner)
    {
      super(owner, lex, "gatorView.remove");
    }
    public CommandArtifact doInvoke() throws Exception { return doRemove(); }
  }
  
  public CommandArtifact doRemove()
  {
    int[] selRows = model.getSelection().getRows();
    for (int i=0; i < selRows.length; i++)
    {
      tracker.remove(model.getValueAt(selRows[i], GATOR_NAME_ROW_INDEX).toString());
    }
    model.updateTable();
    return null;
  }
  

////////////////////////////////////////////////////////////////
// Attributes
////////////////////////////////////////////////////////////////

  private BGatorTracker tracker;
  private BTable table;
  private DefaultTableModel model;
  private static Lexicon lex = Lexicon.make("nola");
  private static String[] colNames =
    {
      lex.getText("gators.col.time"),
      lex.getText("gators.col.name"),
      lex.getText("gators.col.length"),
      lex.getText("gators.col.weight")
    };
  private static final int GATOR_NAME_ROW_INDEX = 1;
  private Command addCmd;
  private Command removeCmd;
  
  private BAbstractButton bAdd;
  private BAbstractButton bRemove;
}
