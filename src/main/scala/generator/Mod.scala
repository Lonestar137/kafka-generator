package org.goldteam.generator;
import scala.util.Random;
import java.util.Date;
import scala.collection.mutable.MutableList;
import scala.annotation.varargs

class Mod(var name : String = "", var price : Float = 1.0f, var probability : Float = 0.1f) {
  
  def this(aName : String) { this(aName, 1.0f, 0.1f); }
  def this(aName : String, aPrice : Float) { this(aName, aPrice, 0.1f); }

  var useCount : Int = 0;

  var originalPrice : Float = 1.0f;
  var priceTrendPeak : Float = 2.0f;
  
  var originalProbability : Float = 0.1f;
  var probabilityTrendPeak : Float = 0.2f;
  
  var trendIncreasing : Boolean = false;
  var trendActive : Boolean = false;
  var trendAlpha : Float = 0f;
  var trendGrowOnly : Boolean = false;
  var trendUncapped : Boolean = false;

  var trendStartdate : Date = null;
  var trendMaxDuration : Long = 86400l * 1000l; // 1 day

  var substrings : MutableList[String] = null;

  def randomSubstring() : String = {
    if (this.substrings == null || this.substrings.isEmpty) { return ""; } 
    else { return this.substrings(Random.nextInt(this.substrings.size)); }
  }

  // Start a trend that will grow to full intensity and then return to its original values.
  def startTrend() : Unit = {
    this.trendIncreasing = true;
    this.trendActive = true;
  }

  // Start a trend that will grow to full intensity but will not return to its original values.
  def startTrendGrowOnly() : Unit = {
    this.trendGrowOnly = true;
    this.startTrend();
  }
  // Start a trend that will grow without an upper bound and will not return to its original values.
  def startTrendUncapped() : Unit = {
    this.trendUncapped = true;
    this.startTrend();
  }

  def setTrendInfo(basePrice : Float, maxPrice : Float, baseProb : Float, maxProb : Float) : Unit = {
    this.setPriceTrendInfo(basePrice, maxPrice);
    this.setProbabilityTrendInfo(baseProb, maxProb);
  }

  def setPriceTrendInfo(basePrice : Float, maxPrice : Float) : Unit = {
    this.price = basePrice; 
    this.originalPrice = basePrice; 
    this.priceTrendPeak = maxPrice;
  }
  def setProbabilityTrendInfo(baseProb : Float, maxProb : Float) : Unit = {
    this.probability = baseProb; 
    this.originalProbability = baseProb; 
    this.probabilityTrendPeak = maxProb;
  }

  def useName() : String = { 
    this.useCount += 1; 
    return this.name; 
  }

  def lerp(a : Float, b : Float, f : Float) : Float = {
    return a + f * (b - a);
  }

  def progressTrend() : Unit = {
    if (this.trendActive) {
      if (this.trendIncreasing) {
        if (this.trendAlpha < 1.0f || this.trendUncapped) {
          this.trendAlpha += Random.nextFloat() * 0.02f;
        }
        else {
          this.trendAlpha = 1.0f;
          this.trendIncreasing = false;
          if (this.trendGrowOnly) { 
            this.trendActive = false; 
          }
        }
      }
      else {
        if (this.trendAlpha > 0f) {
          this.trendAlpha -= Random.nextFloat() * 0.02f;
        }
        else {
          this.trendAlpha = 0f;
          this.trendActive = false;
          this.trendIncreasing = true;
        }
      }
    }
  }

  def getPrice() : Float = {
    progressTrend();
    return lerp(this.originalPrice, this.priceTrendPeak, this.trendAlpha);
  }
  
  def getProbability() : Float = {
    progressTrend();
    return lerp(this.originalProbability, this.probabilityTrendPeak, this.trendAlpha);
  }

}

object Mod {
    def apply(name: String, price: Float = 1f): Mod = {
        return new Mod(name, price);
    }
}
