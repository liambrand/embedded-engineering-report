// Method to check readings for erronous values
  private ArrayList<Double> checker(ArrayList<Double> checkerReadings) {
    int errReading = -1;
    double avg = 0;

    for(int i = 0; i < checkerReadings.size(); i++) {
      // Create a list of readings, minus whatever reading is being checked
      ArrayList<Double> toCompare = checkerReadings;
      double toCheck = checkerReadings.get(i);
      toCompare.remove(i);
        
      // Get avg of all other readings
      for(Double reading: toCompare) {
        avg += reading;
      }
      avg = avg/toCompare.size();

      // If checked value has a diff of more than 2*sensor noise, flag it's location in array
      if(toCheck > avg + (2*sensorErr) || toCheck < avg - (2*sensorErr)) {
        errReading = i;
      }
	    avg = 0;
    }

    // Remove erronous reading if one has been flagged
    if(errReading != -1) {
      checkerReadings.remove(errReading);
    }

    return checkerReadings;
  }
