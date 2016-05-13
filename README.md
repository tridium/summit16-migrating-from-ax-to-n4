# Module Migration from Niagara AX to Niagara 4


## Migration Steps

If you'd like to replicate the steps we did in the Niagara Summit demo, you can do the following to migrate the AX nola module to Niagara 4.

### Open the repository

Download this repository to your machine using your favorite tool.  You should see in the top level, the PowerPoint presentation, this README.md file, and two folders - _ax_ and _n4_.

### Run the New Module Wizard (Summit Demo)

These steps are for using the New Module Wizard as demonstrated at the Niagara Summit.  For reproducing this using 4.2 or earlier, see the modified steps below.

#### NMW Step 1

1. Open the Workbench and select Tools > New Module.
2. Select a folder under which to place the new module, by clicking the folder button and selecting the parent folder for the module, or by specifying it directly in the text field.  We will assume you will select the 'nolademo' folder underneath the _n4_ folder inside this module.
3. Set the module name, preferred symbol, version, description, and vendor name:
* module name = nola
* preferred symbol = nola
* version = 4.0
* description = New Orleans Module Migration demo
* vendor = niagarasummit 
4. Select RUNTIME and WB module parts to create.
5. Ensure the lexicon and palette boxes are unchecked.
6. Click Next.

#### NMW Step 2

1. Specify the dependencies for the N4 module.  Open the build.xml from the AX Module in a text editor to see what modules to select.
2. Click Add to select modules.
3. The nre and baja modules are preselected for you.  You will also need to select
* alarm-rt
* bajaui-wb
* bql-rt
* control-rt
* file-rt
* gx-wb (NOTE: this one is __incorrect__)
* history-rt
* workbench-wb
4. Click Next.

#### NMW Step 3

There is nothing to do here but notice the way the ui package was created in the __wb__ module part, whereas the regular nola package was created in the __rt__ module part.  Click Finish.

Close the Workbench.

### New Module Wizard (Using Niagara 4.2 or earlier)
To use the New Module Wizard in Niagara 4.2 or earlier, you have to do a few things differently.
#### Execute Wizard twice
 - You need to set the NIAGARA_HOME environment variable manually, as this is not done by the tool.  __NOTE:__ You'll need to do this through the system properties, not just by using the "set" command at the console prompt, so that IntelliJ will be able to recognize it.
 - You will need to run the New Module Wizard twice, once for each module part.
 - Make sure to choose the same parent folder both times
 - Select the following dependencies:
 - rt module part: alarm-rt, bql-rt, control-rt, file-rt, history-rt
 - wb module part: bajaui-wb, gx-wb (__incorrect__), workbench-wb
#### Copy gradle files
Copy the *.gradle files from the 'demo' section of the repository.  You will need:
- build.gradle
- environment.gradle
- settings.gradle
- vendor.gradle
#### Fix environment.gradle
Open environment.gradle in a text editor and make the following changes to suit your specific installation:

    gradle.ext.niagara_dev_home = "C:/ns2016/devmig/n4/demo"  // location of your nola project
    gradle.ext.niagara_home = "C:/niagara/niagara-4.2.36.16"  // Niagara System Home location
    gradle.ext.niagara_user_home = "C:/Users/Craig/Niagara4.2/tridium"  // Niagara Workbench User Home location
#### Copy and fix module part gradle build files
Copy n4/demo/nola-rt.gradle into the nola-rt folder, and n4/demo/nola-wb.gradle into the nola-wb folder.
__REMOVE__ the build.gradle from both nola-rt and nola-wb folders.

Now you can continue with the file copy and IDE import!

### Copy Files

Now as a starting point we need to copy the files over from the AX source location to the N4 source location.
1. Copy ax/nola/src/com/niagarasummit/nola/*
    into n4/nolademo/nola/nola-rt/src/com/niagarasummit/nola
2. Copy ax/nola/src/com/niagarasummit/nola/ui/*
    into n4/nolademo/nola/nola-wb/src/com/niagarasummit/nola/ui
3. Copy ax/nola/module.lexicon and module.palette
    into n4/nolademo/nola/nola-rt
4. Copy ax/nola/module-include.xml into __BOTH__
    n4/nolademo/nola/nola-rt __AND__ n4/nolademo/nola/nola-wb

### Import into IDE

You can do this using Eclipse or IntelliJ IDEA.  These instructions are for importing into IntelliJ IDEA 2016.1.2.

1. Open IntelliJ.  Select File > Open... to open the dialog for opening a project.
2. Navigate to the build.gradle created by the New Module Wizard.  It should be at n4/nolademo/build.gradle. Click OK.
3. Select to 'Use gradle wrapper task configuration'.  Click OK.
4. After a brief pause, you will have a dialog saying 'Gradle Project Data To Import'.  Ensure all three choices are selected and click OK.
5. After this, you should have the IntelliJ workbench open, with the nola-rt, nola-wb, and nolademo parts available in the Project view on the left.

### Migrate the Code

#### Migrate Slot Code

The slot tool migration serves two purposes in this demo.
1. We need to get the type definitions into the correct module part locations, as they must only be specified in a single module-include.xml.
2. It converts the old-style AX syntax to the new N4 annotation syntax - not strictly necessary, but strongly recommended.

Open the Gradle projects window, and execute the task
    niagara > niagara (root) > niagara > migrateSlotomatic.
This is equivalent to executing the console command
    gradlew slotomatic -Dslotomatic.migrateBeforeRecompile

#### Apply N4 Code Changes

Now you need to fix the code to use the new Niagara 4 APIs.  You can identify these by running the Gradle task from the Gradle projects window:
    niagara > niagara (root) > build > build
Or, from the command line:
    gradlew build
Or, you can open the files BGatorTracker and BGatorTrackerView and identify the items highlighted in red.

* BGatorTracker.java:
    - Remove the import of the (nonexistent) javax.baja.collection.BICollection
    - In doDisplayNamesQuery():
        + Remove or comment the Niagara AX code and uncomment the Niagara 4 code.  This is indicated with a line comment before the code in each place.  For example:
        ```java
        // Niagara AX
        BICollection result = (BICollection)BOrd.make("bql:select displayName").get(this);
        BITable table = result.toTable();
//      // Niagara 4
//      BITable<BObject> table = (BITable<BObject>)BOrd.make("bql:select displayName").get(this);
        ```
        + Make sure to add the necessary import for BObject - note, this will be highlighted for you as soon as it is uncommented, and you can use Alt+ENTER to automatically import it.
    - In getAlarmService() _and_ doFindRecordGators():
        + Remove or comment the Niagara AX code and uncomment the Niagara 4 code.
        + Remember to add the import for AlarmDbConnection.
* BGatorTrackerView.java:
    - Open this and observe BInsets is not found.
    - Open nola-wb.gradle.
    - In the dependencies section, change the dependency on gx-wb to gx-rt.
    - Click the refresh projects button in the Gradle projects window - it is the very first icon in the toolbar.
    - Observe that BInsets is now found.

### Build the module

Build the module using the gradle task.  Again, you can build it from the IDE or the command line.

## Station Migration
Remember to migrate your AX station as well.  You can use the `n4mig` executable to do this from the N4 console.
