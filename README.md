### Step
- Step 1: Compile all java files, e.g. javac *.java
- Step 2: Run the Main.java file according to the tutorial, set the parameter
- Step 3: The result will be saved in csv files

### Codes description
- `Daisy.java`: 
  - `getAge()`: Obtain the age of daisy
  - `daisysetAge()`: Change the age of daisy
  - `getAlbedoRate()`: Obtain the albedo of the current patch based on whether there is a daisy and the daisy type if have
    
- `Patch.java`:
  - `getDaisy()`: Obtain the daisy from the current patch, if there is no daisy in the patch, then it will return null
  - `getTemperature()`: Obtain the temperature of the current patch
  - `setTemperature()`: Set the temperature of the current patch equal to the value of input parameter
  - `setDaisy()`: Put a daisy to the current patch. This is mainly invoked when initializing the virtual world or daisy’s self-reproduction
  - `goDieDaisy()`: Remove the daisy from the current patch. This is mainly used when the daisy reaches the Max age(Natural death)
    
- `World.java`:
  - `public World`: Initialize the world. Set its size(row * col),the initial coverage of daisy etc. For a more detailed global variable’s description, please refer to ABM model’s part
  - `patchCalTemperature()`: Update all the temperatures of each patch by invoking the function `calTemperature()` from class Patch
  - `diffuseTemperature()`: Diffuse half of the current patch’s temperature to the surrounding lattice. Each surrounding lattice will get 1/16 current patch temperature. If the amount of surrounding patches is less than 8(This means  the diffused temperature will not be used out. This can occur when the lattice is on the edge or corner  of the world), then the rest of unused temperature will go back to the current patch
  - `meanTemperature()`: Calculate the global(average) temperature by summing all the patches’ temperature and divide the number of total patches
  - `startWhiteRandomly()`: Randomly put the white daisy to the patches until the coverage of white daisy reaches the point. This is invoked when initializing the world
  - `startBlackRandomly()`: Similar to the previous function startWhiteRandomly(), it just changes the white daisy to black daisy
  - `bornNewDaisy()`: Randomly born a baby daisy(age 0) in the surrounding empty patch based on the color of current daisy and local temperature. (only when temperature among 5~40 degree can the daisy sow seed)
  - `produce()`: This is invoked by `bornNewDaisy()`
  - `updateGlobalTemperature()`: Update the global temperature when the world uodate
  - `copyTheWorld()`: When we update the world, we use this function to copy the current world to a sample and use the data from this sample to update the current world
  - `updateDaisyAge()`: Make all the existing daisies’ age plus one(seems like celebrating birthday)
  - `updateWorld()`: Invoke the updating function, This function is invoked when the world is updated