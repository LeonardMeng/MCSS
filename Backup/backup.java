package com.group2;

public class backup {

//  public void patchCalTemperature(){
//
//    for(int i = 0; i < row; i++){
//      for(int j = 0; j< col; j++){
//        //lose 50% for distribution
//        copyWorld[i][j].setTemperature(copyWorld[i][j].getTemperature()/2);
//      }
//    }
//    double localHeating = 0;
//    for(int i = 0; i < row; i++){
//      for(int j = 0; j< col; j++){
//        //store the sum of current patch's temperature
//        double sumTemperature = 0;
//        //Calculate the left-top patch's temperature
//        if(i == 0 && j == 0){
//          //sum surronding distribution
//          for(int k = 0; k < i + 1; k ++){
//            for(int l = 0; l < j + 1; l++){
//              sumTemperature += copyWorld[k][l].getTemperature()/16;
//            }
//          }
//          sumTemperature -= copyWorld[i][j].getTemperature()/16;
//          //absorb energy from sun
////          if(copyWorld[i][j].getDaisy() != null){
////            sumTemperature += solarLuminosity * (1 - copyWorld[i][j].getDaisy().getAlbedoRate());
////          }
////          else{
////            sumTemperature += ((1 - albedoOfSurface) * solarLuminosity);
////          }
//          if(world[i][j].getDaisy() == null){
//            absorbLuminosity = 0;
//          }else{
//            absorbLuminosity = ((1 - albedoOfSurface) * solarLuminosity);
//          }
//          if(absorbLuminosity > 0){
//            localHeating = 72 * absorbLuminosity + 80;
//          }else{
//            localHeating = 80;
//          }
//
//          //return the temperature to the world
//          world[i][j].setTemperature((sumTemperature + localHeating)/2);
//
//
//        }
//        //Calculate the middle-top patches' temperature
//        if(i == 0 && j > 0 && j < col){
//          //lose 50% for distribution
//          sumTemperature -= copyWorld[i][j].getTemperature()/2;
//          //sum surronding distribution
//          for(int k = 0; k < i + 1; k ++){
//            for(int l = j - 1; l < j + 1; l++){
//              sumTemperature += copyWorld[k][l].getTemperature()/8;
//            }
//          }
//          sumTemperature -= copyWorld[i][j].getTemperature()/8;
//          //absorb energy from sun
//          if(copyWorld[i][j].getDaisy() != null){
//            sumTemperature += solarLuminosity * (1 - copyWorld[i][j].getDaisy().getAlbedoRate());
//          }
//          else{
//            sumTemperature += ((1 - albedoOfSurface) * solarLuminosity);
//          }
//          if(world[i][j].getDaisy() == null){
//            absorbLuminosity = 0;
//          }else{
//            absorbLuminosity = ((1 - albedoOfSurface) * solarLuminosity);
//          }
//          if(absorbLuminosity > 0){
//            localHeating = 72 * absorbLuminosity + 80;
//          }else{
//            localHeating = 80;
//          }
//
//          //return the temperature to the world
//          world[i][j].setTemperature((sumTemperature + localHeating)/2);
//
//
//        }
//
//        //Calculate the right-top patch's temperature
//        if(i == 0 && j == col){
//          //lose 50% for distribution
//          sumTemperature -= copyWorld[i][j].getTemperature()/2;
//          //sum surronding distribution
//          for(int k = 0; k < i + 1; k ++){
//            for(int l = j - 1; l < j ; l++){
//              sumTemperature += copyWorld[k][l].getTemperature()/8;
//            }
//          }
//          sumTemperature -= copyWorld[i][j].getTemperature()/8;
//          //absorb energy from sun
//          if(copyWorld[i][j].getDaisy() != null){
//            sumTemperature += solarLuminosity * (1 - copyWorld[i][j].getDaisy().getAlbedoRate());
//          }
//          else{
//            sumTemperature += ((1 - albedoOfSurface) * solarLuminosity);
//          }
//          if(world[i][j].getDaisy() == null){
//            absorbLuminosity = 0;
//          }else{
//            absorbLuminosity = ((1 - albedoOfSurface) * solarLuminosity);
//          }
//          if(absorbLuminosity > 0){
//            localHeating = 72 * absorbLuminosity + 80;
//          }else{
//            localHeating = 80;
//          }
//
//          //return the temperature to the world
//          world[i][j].setTemperature((sumTemperature + localHeating)/2);
//
//        }
//
//        //Calculate the left-bottom patch's temperature
//        if(i == row && j == 0){
//          //lose 50% for distribution
//          sumTemperature -= copyWorld[i][j].getTemperature()/2;
//          //sum surronding distribution
//          for(int k = i - 1; k < i; k ++){
//            for(int l = 0; l < j + 1; l++){
//              sumTemperature += copyWorld[k][l].getTemperature()/8;
//            }
//          }
//          sumTemperature -= copyWorld[i][j].getTemperature()/8;
//          //absorb energy from sun
//          if(copyWorld[i][j].getDaisy() != null){
//            sumTemperature += solarLuminosity * (1 - copyWorld[i][j].getDaisy().getAlbedoRate());
//          }
//          else{
//            sumTemperature += ((1 - albedoOfSurface) * solarLuminosity);
//          }
//          if(world[i][j].getDaisy() == null){
//            absorbLuminosity = 0;
//          }else{
//            absorbLuminosity = ((1 - albedoOfSurface) * solarLuminosity);
//          }
//          if(absorbLuminosity > 0){
//            localHeating = 72 * absorbLuminosity + 80;
//          }else{
//            localHeating = 80;
//          }
//
//          //return the temperature to the world
//          world[i][j].setTemperature((sumTemperature + localHeating)/2);
//
//        }
//        //Calculate the middle-bottom patches' temperature
//        if(i == row && j > 0 && j < col){
//          //lose 50% for distribution
//          sumTemperature -= copyWorld[i][j].getTemperature()/2;
//          //sum surronding distribution
//          for(int k = row - 1; k < i; k ++){
//            for(int l = j - 1; l < j + 1; l++){
//              sumTemperature += copyWorld[k][l].getTemperature()/8;
//            }
//          }
//          sumTemperature -= copyWorld[i][j].getTemperature()/8;
//          //absorb energy from sun
//          if(copyWorld[i][j].getDaisy() != null){
//            sumTemperature += solarLuminosity * (1 - copyWorld[i][j].getDaisy().getAlbedoRate());
//          }
//          else{
//            sumTemperature += ((1 - albedoOfSurface) * solarLuminosity);
//          }
//          if(world[i][j].getDaisy() == null){
//            absorbLuminosity = 0;
//          }else{
//            absorbLuminosity = ((1 - albedoOfSurface) * solarLuminosity);
//          }
//          if(absorbLuminosity > 0){
//            localHeating = 72 * absorbLuminosity + 80;
//          }else{
//            localHeating = 80;
//          }
//
//          //return the temperature to the world
//          world[i][j].setTemperature((sumTemperature + localHeating)/2);
//
//        }
//
//        //Calculate the right-bottom patch's temperature
//        if(i == row && j == col){
//          //lose 50% for distribution
//          sumTemperature -= copyWorld[i][j].getTemperature()/2;
//          //sum surronding distribution
//          for(int k = i - 1; k < i; k ++){
//            for(int l = j - 1; l < j; l++){
//              sumTemperature += copyWorld[k][l].getTemperature()/8;
//            }
//          }
//          sumTemperature -= copyWorld[i][j].getTemperature()/8;
//          //absorb energy from sun
//          if(copyWorld[i][j].getDaisy() != null){
//            sumTemperature += solarLuminosity * (1 - copyWorld[i][j].getDaisy().getAlbedoRate());
//          }
//          else{
//            sumTemperature += ((1 - albedoOfSurface) * solarLuminosity);
//          }
//          if(world[i][j].getDaisy() == null){
//            absorbLuminosity = 0;
//          }else{
//            absorbLuminosity = ((1 - albedoOfSurface) * solarLuminosity);
//          }
//          if(absorbLuminosity > 0){
//            localHeating = 72 * absorbLuminosity + 80;
//          }else{
//            localHeating = 80;
//          }
//
//          //return the temperature to the world
//          world[i][j].setTemperature((sumTemperature + localHeating)/2);
//
//        }
//
//        //Calculate the left-middle patch's temperature
//        if(i > 0 && i < row && j == 0){
//          //lose 50% for distribution
//          sumTemperature -= copyWorld[i][j].getTemperature()/2;
//          //sum surronding distribution
//          for(int k = i - 1; k < i + 1; k ++){
//            for(int l = j; l < j + 1; l++){
//              sumTemperature += copyWorld[k][l].getTemperature()/8;
//            }
//          }
//          sumTemperature -= copyWorld[i][j].getTemperature()/8;
//          //absorb energy from sun
//          if(copyWorld[i][j].getDaisy() != null){
//            sumTemperature += solarLuminosity * (1 - copyWorld[i][j].getDaisy().getAlbedoRate());
//          }
//          else{
//            sumTemperature += ((1 - albedoOfSurface) * solarLuminosity);
//          }
//          if(world[i][j].getDaisy() == null){
//            absorbLuminosity = 0;
//          }else{
//            absorbLuminosity = ((1 - albedoOfSurface) * solarLuminosity);
//          }
//          if(absorbLuminosity > 0){
//            localHeating = 72 * absorbLuminosity + 80;
//          }else{
//            localHeating = 80;
//          }
//
//          //return the temperature to the world
//          world[i][j].setTemperature((sumTemperature + localHeating)/2);
//
//        }
//        //Calculate the right-middle patch's temperature
//        if(i > 0 && i < row && j == col){
//        //lose 50% for distribution
//          sumTemperature -= copyWorld[i][j].getTemperature()/2;
//          //sum surronding distribution
//          for(int k = i - 1; k < i + 1; k ++){
//            for(int l = j - 1; l < j; l++){
//              sumTemperature += copyWorld[k][l].getTemperature();
//            }
//          }
//          sumTemperature -= copyWorld[i][j].getTemperature()/8;
//          //absorb energy from sun
//          if(copyWorld[i][j].getDaisy() != null){
//            sumTemperature += solarLuminosity * (1 - copyWorld[i][j].getDaisy().getAlbedoRate());
//          }
//          else{
//            sumTemperature += ((1 - albedoOfSurface) * solarLuminosity);
//          }
//          if(world[i][j].getDaisy() == null){
//            absorbLuminosity = 0;
//          }else{
//            absorbLuminosity = ((1 - albedoOfSurface) * solarLuminosity);
//          }
//          if(absorbLuminosity > 0){
//            localHeating = 72 * absorbLuminosity + 80;
//          }else{
//            localHeating = 80;
//          }
//
//          //return the temperature to the world
//          world[i][j].setTemperature((sumTemperature + localHeating)/2);
//        }
//
//        //Calculate all middle patches' temperature
//        if(i > 0 && i < row && j > 0 && j < col){
//          //lose 50% for distribution
//          sumTemperature -= copyWorld[i][j].getTemperature()/2;
//          //sum surronding distribution
//          for(int k = i - 1; k < i + 1; k ++){
//            for(int l = j - 1; l < j + 1; l++){
//              sumTemperature += copyWorld[k][l].getTemperature()/8;
//            }
//          }
//          sumTemperature -= copyWorld[i][j].getTemperature()/8;
//          //absorb energy from sun
//          if(copyWorld[i][j].getDaisy() != null){
//            sumTemperature += solarLuminosity * (1 - copyWorld[i][j].getDaisy().getAlbedoRate());
//          }
//          else{
//            sumTemperature += ((1 - albedoOfSurface) * solarLuminosity);
//          }
//          if(world[i][j].getDaisy() == null){
//            absorbLuminosity = 0;
//          }else{
//            absorbLuminosity = ((1 - albedoOfSurface) * solarLuminosity);
//          }
//          if(absorbLuminosity > 0){
//            localHeating = 72 * absorbLuminosity + 80;
//          }else{
//            localHeating = 80;
//          }
//
//          //return the temperature to the world
//          world[i][j].setTemperature((sumTemperature + localHeating)/2);
//
//        }
//
//      }
//    }
//
//  }




//  public void bornNewDaisy() {
//    for (int i = 0; i < row; i++) {
//      for (int j = 0; j < col; j++) {
//        //random-born right-top daisy
//        if (i == 0 && j == 0) {
//          for (int k = 0; k < i + 1; k++) {
//            for (int l = 0; l < j + 1; l++) {
//              if (world[k][l].getDaisy() == null && copyWorld[i][j].getDaisy() != null) {
//                seedThreshold = ((0.1457 * world[i][j].getTemperature()) -
//                    (0.0032 * (world[i][j].getTemperature() * world[i][j].getTemperature()))
//                    - 0.6443);
//                double randomdouble = Math.random();
//                if (randomdouble < seedThreshold && world[i][j].getDaisy() != null) {
//                  world[k][l]
//                      .generateDaisy(world[i][j].getDaisy().getDaisyType(), world[i][j].getDaisy());
//                  world[k][l].setDaisy(new Daisy(world[i][j].getDaisy().getDaisyType()));
//                  world[k][l].getDaisy().setAge(0);
//                }
//              }
//            }
//          }
//        }
//        //random-born the middle-top patches' daisy
//        if (i == 0 && j > 0 && j < col) {
//          for (int k = 0; k < i + 1; k++) {
//            for (int l = j - 1; l < j + 1; l++) {
//              if (world[k][l].getDaisy() == null && copyWorld[i][j].getDaisy() != null) {
//                seedThreshold = ((0.1457 * world[i][j].getTemperature()) -
//                    (0.0032 * (world[i][j].getTemperature() * world[i][j].getTemperature()))
//                    - 0.6443);
//                double randomdouble = Math.random();
//                if (randomdouble < seedThreshold && world[i][j].getDaisy() != null) {
//                  world[k][l]
//                      .generateDaisy(world[i][j].getDaisy().getDaisyType(), world[i][j].getDaisy());
//                }
//              }
//            }
//          }
//        }
//
//        //random-born the right-top patch's daisy
//        if (i == 0 && j == col) {
//          for (int k = 0; k < i + 1; k++) {
//            for (int l = j - 1; l < j; l++) {
//              if (world[k][l].getDaisy() == null && copyWorld[i][j].getDaisy() != null) {
//                seedThreshold = ((0.1457 * world[i][j].getTemperature()) -
//                    (0.0032 * (world[i][j].getTemperature() * world[i][j].getTemperature()))
//                    - 0.6443);
//                double randomdouble = Math.random();
//                if (randomdouble < seedThreshold && world[i][j].getDaisy() != null) {
//                  world[k][l]
//                      .generateDaisy(world[i][j].getDaisy().getDaisyType(), world[i][j].getDaisy());
//                }
//              }
//            }
//          }
//        }
//
//        //random-born the left-bottom patch's daisy
//        if (i == row && j == 0) {
//          for (int k = i - 1; k < i; k++) {
//            for (int l = 0; l < j + 1; l++) {
//              if (world[k][l].getDaisy() == null && copyWorld[i][j].getDaisy() != null) {
//                seedThreshold = ((0.1457 * world[i][j].getTemperature()) -
//                    (0.0032 * (world[i][j].getTemperature() * world[i][j].getTemperature()))
//                    - 0.6443);
//                double randomdouble = Math.random();
//                if (randomdouble < seedThreshold && world[i][j].getDaisy() != null) {
//                  world[k][l]
//                      .generateDaisy(world[i][j].getDaisy().getDaisyType(), world[i][j].getDaisy());
//                }
//              }
//            }
//          }
//        }
//        //random-born the middle-bottom patches' daisy
//        if (i == row && j > 0 && j < col) {
//          for (int k = row - 1; k < i; k++) {
//            for (int l = j - 1; l < j + 1; l++) {
//              if (world[k][l].getDaisy() == null && copyWorld[i][j].getDaisy() != null) {
//                seedThreshold = ((0.1457 * world[i][j].getTemperature()) -
//                    (0.0032 * (world[i][j].getTemperature() * world[i][j].getTemperature()))
//                    - 0.6443);
//                double randomdouble = Math.random();
//                if (randomdouble < seedThreshold && world[i][j].getDaisy() != null) {
//                  world[k][l]
//                      .generateDaisy(world[i][j].getDaisy().getDaisyType(), world[i][j].getDaisy());
//                }
//              }
//            }
//          }
//        }
//
//        //random-born the right-bottom patch's daisy
//        if (i == row && j == col) {
//          for (int k = i - 1; k < i; k++) {
//            for (int l = j - 1; l < j; l++) {
//              if (world[k][l].getDaisy() == null && copyWorld[i][j].getDaisy() != null) {
//                seedThreshold = ((0.1457 * world[i][j].getTemperature()) -
//                    (0.0032 * (world[i][j].getTemperature() * world[i][j].getTemperature()))
//                    - 0.6443);
//                double randomdouble = Math.random();
//                if (randomdouble < seedThreshold && world[i][j].getDaisy() != null) {
//                  world[k][l]
//                      .generateDaisy(world[i][j].getDaisy().getDaisyType(), world[i][j].getDaisy());
//                }
//              }
//            }
//          }
//        }
//
//        //random-born the left-middle patch's daisy
//        if (i > 0 && i < row && j == 0) {
//          for (int k = i - 1; k < i + 1; k++) {
//            for (int l = j; l < j + 1; l++) {
//              if (world[k][l].getDaisy() == null && copyWorld[i][j].getDaisy() != null) {
//                seedThreshold = ((0.1457 * world[i][j].getTemperature()) -
//                    (0.0032 * (world[i][j].getTemperature() * world[i][j].getTemperature()))
//                    - 0.6443);
//                double randomdouble = Math.random();
//                if (randomdouble < seedThreshold && world[i][j].getDaisy() != null) {
//                  world[k][l]
//                      .generateDaisy(world[i][j].getDaisy().getDaisyType(), world[i][j].getDaisy());
//                }
//              }
//            }
//          }
//        }
//        //random-born the right-middle patch's daisy
//        if (i > 0 && i < row && j == col) {
//          for (int k = i - 1; k < i + 1; k++) {
//            for (int l = j - 1; l < j; l++) {
//              if (world[k][l].getDaisy() == null && copyWorld[i][j].getDaisy() != null) {
//                seedThreshold = ((0.1457 * world[i][j].getTemperature()) -
//                    (0.0032 * (world[i][j].getTemperature() * world[i][j].getTemperature()))
//                    - 0.6443);
//                double randomdouble = Math.random();
//                if (randomdouble < seedThreshold && world[i][j].getDaisy() != null) {
//                  world[k][l]
//                      .generateDaisy(world[i][j].getDaisy().getDaisyType(), world[i][j].getDaisy());
//                }
//              }
//            }
//          }
//        }
//
//        //random-born all middle patches' daisy
//        if (i > 0 && i < row && j > 0 && j < col) {
//          for (int k = i - 1; k < i + 1; k++) {
//            for (int l = j - 1; l < j + 1; l++) {
//              if (world[k][l].getDaisy() == null && copyWorld[i][j].getDaisy() != null) {
//                seedThreshold = ((0.1457 * world[i][j].getTemperature()) -
//                    (0.0032 * (world[i][j].getTemperature() * world[i][j].getTemperature()))
//                    - 0.6443);
//                double randomdouble = Math.random();
//                if (randomdouble < seedThreshold && world[i][j].getDaisy() != null) {
//                  world[k][l]
//                      .generateDaisy(world[i][j].getDaisy().getDaisyType(), world[i][j].getDaisy());
//                }
//              }
//            }
//          }
//        }
//      }
//    }
//
//  }

}
