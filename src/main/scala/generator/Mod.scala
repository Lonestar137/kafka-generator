package org.goldteam.generator;
import scala.util.Random;
import java.util.Date;
import scala.collection.mutable.MutableList;
import scala.annotation.varargs

class Mod(var name : String = "", var basePrice : Float = 1.0f) {
  
  // def this(aName : String) { this(aName, 1.0f); }
  // def this(aName : String, aPrice : Float) { this(aName, aPrice); }

  var useCount : Int = 0;

  var maxPrice : Float = 2.0f;
  
  var baseProbability : Float = 0.1f;
  var maxProbability : Float = 0.2f;
  
  var trendIncreasing : Boolean = false;
  var trendActive : Boolean = false;
  var trendAlpha : Float = 0f;
  var trendGrowOnly : Boolean = false;
  var trendUncapped : Boolean = false;
  var trendComplete : Boolean = false;

  var trendGrowthFactor : Float = 0.02f;

  var trendStartdate : Date = null;
  var trendMaxDuration : Long = 86400l * 1000l; // 1 day

  var substrings : MutableList[String] = null;

  def randomSubstring() : String = {
    if (this.substrings == null || this.substrings.isEmpty) { return ""; } 
    else { return this.substrings(Random.nextInt(this.substrings.size)); }

  }

  // Start a trend that will grow to full intensity and then return to its original values.
  def startTrend() : Unit = {
    if (this.trendActive) { return; }
    this.trendIncreasing = true;
    this.trendActive = true;
  }

  // Start a trend that will grow to full intensity but will not return to its original values.
  def startTrendGrowOnly() : Unit = {
    if (this.trendComplete || this.trendActive) { return; }
    this.trendGrowOnly = true;
    this.startTrend();
  }
  // Start a trend that will grow without an upper bound and will not return to its original values.
  def startTrendUncapped() : Unit = {
    if (this.trendActive) { return; }
    this.trendUncapped = true;
    this.startTrend();
  }

  def setTrendInfo(basePrice : Float, maxPrice : Float, baseProb : Float, maxProb : Float) : Unit = {
    this.setPriceTrendInfo(basePrice, maxPrice);
    this.setProbabilityTrendInfo(baseProb, maxProb);

  }

  def setPriceTrendInfo(basePrice : Float, maxPrice : Float) : Unit = {
    this.basePrice = basePrice; 
    this.maxPrice = maxPrice;
  }
  def setPricePeakAsMultiplier(maxPriceMultiplier : Float) : Unit = {
    this.maxPrice = this.basePrice * maxPriceMultiplier;
  }
  def setProbabilityTrendInfo(baseProb : Float, maxProb : Float) : Unit = {
    this.baseProbability = baseProb; 
    this.maxProbability = maxProb;
  }
  def setProbabilityPeakAsMultiplier(maxProbabilityMultiplier : Float) : Unit = {
    this.maxProbability = this.baseProbability * maxProbabilityMultiplier;
  }

  def setTrendGrowthFactorAsMultiplier(trendGrowthMultiplier : Float) : Unit = {
    this.trendGrowthFactor = this.trendGrowthFactor * trendGrowthMultiplier;
  }

  def useName() : String = { 
    this.useCount += 1; 
    return this.name; 

  }

  def lerp(a : Float, b : Float, f : Float) : Float = { return a + f * (b - a); }

  def progressTrend() : Unit = {
    if (this.trendActive) {
      if (this.trendIncreasing) {
        if (this.trendAlpha < 1.0f || this.trendUncapped) {
          this.trendAlpha += Random.nextFloat() * this.trendGrowthFactor;
        }
        else {
          this.trendAlpha = 1.0f;
          this.trendIncreasing = false;
          if (this.trendGrowOnly) { 
            this.trendComplete = true;
            this.trendActive = false;
          }
        }
      }
      else {
        if (this.trendAlpha > 0f) {
          this.trendAlpha -= Random.nextFloat() * trendGrowthFactor;
        }
        else {
          this.trendAlpha = 0f;
          this.trendActive = false;
          this.trendIncreasing = true;
        }
      }
    }
  }
  
  def price() : Float = { return lerp(this.basePrice, this.maxPrice, this.trendAlpha); }
  def getPrice() : Float = { this.progressTrend(); return this.price(); }

  def probability() : Float = { return this.probability(this.trendAlpha); }
  def probability(alpha : Float) : Float = { return this.lerp(this.baseProbability, this.maxProbability, alpha); };
  def getProbability() : Float = { this.progressTrend(); return this.probability(); }

}

object Mod {
    def apply(name: String, price: Float = 1f): Mod = {
        return new Mod(name, price);
    }
}
