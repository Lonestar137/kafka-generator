package org.goldteam.generator;
import scala.util.Random;
import java.util.Date;
import scala.collection.mutable.MutableList;

class ModSet {
	
	var name : String = "";
	var mods : MutableList[Mod] = MutableList();

	def this(modSetName : String, modList : MutableList[Mod]) {
		this(); 
		this.name = modSetName; 
		this.mods = modList;
	}

	def this(modSetName : String, modList : Mod*) {
		this();
		this.name = modSetName;
		this.mods ++= modList;
		var baseProbability = 1f / this.mods.size.toFloat;
		for (mod <- this.mods) { 
			mod.probability = baseProbability;
			mod.originalProbability = baseProbability;
			mod.probabilityTrendPeak = 2f * baseProbability;
		}
	}

	def apply(name: String): Mod = {
		if (name == null || name.isBlank) { return null; }
		for (mod <- this.mods) { if (mod.name == name) { return mod; } }
        return null;
    }

	def addMod(mod : Mod) : Unit = { this.mods += mod; }

	def getMod() : Mod = {
		
		if (mods == null || mods.isEmpty) { 
			throw new Exception("Unable to select a random mod. No mods present in ModSet.");
		}

		var modIndex : Int = 0; var attempts : Int = 0;
		while (attempts < 100000) { // max of 100,000 attempts
			// select first mod whose probability hits
			modIndex = Random.nextInt(mods.size);
			if (Random.nextFloat() < mods(modIndex).getProbability()) { 
				return mods(modIndex);
			}
			attempts += 1;
		}

		throw new Exception("Unable to select a random mod. No probabilities were hit after 100,000 attempts.");

	}

	def setModProbabilities(baseProb : Float) : Unit = {
		for(mod <- mods) { 
			mod.originalProbability = baseProb;
			mod.probabilityTrendPeak = baseProb;
			mod.probability = baseProb;
		}
	}

	def setPriceTrendInfo(modName: String, basePrice : Float, maxPrice : Float) : Unit = {
		for (mod <- mods) {
			if (mod.name == modName) {
				mod.setPriceTrendInfo(basePrice, maxPrice);
				return;
			}
		}
		throw new Exception(s"Mod $modName not found. Unable to set trend info.");
	}
	def setProbabilityTrendInfo(modName: String, baseProb : Float, maxProb : Float) : Unit = {
		for (mod <- mods) {
			if (mod.name == modName) {
				mod.setProbabilityTrendInfo(baseProb, maxProb);
				return;
			}
		}
		throw new Exception(s"Mod $modName not found. Unable to set trend info.");
	}

	def getDistributionString(order : Boolean = false) : String = {
		
		// record usage, calculate total
		var modSetUsage : Int = 0;
		for(mod <- mods) { modSetUsage += mod.useCount; }

		// optional sort
		var finalModList : MutableList[Mod] = mods;
		if (order) { finalModList = mods.sortWith((a, b) => a.useCount > b.useCount); }
		
		// output variables
		var out : String = s"[ModSet] $name ($modSetUsage uses):\n";		
		var currentPercent : Float = 0f;
		
		// generate output string line by line
		var textRow : String = "";
		var alignTo : Int = 10 + mods.sortWith((a, b) => a.name.length > b.name.length).head.name.length;
		for(mod <- finalModList) {
			currentPercent = (mod.useCount / modSetUsage.toFloat) * 100f;
			textRow = s"[${f"$currentPercent%05.2f"}%] ${mod.name} ";
			out += tabAlign(alignTo, textRow, s"(${f"${mod.useCount}%04d"} / $modSetUsage)") + "\n";
		}

		// return output string minus final line break
		return out.take(out.length - 1);

	}

	def tabAlign(to : Int, text : String, alignText : String) : String = {
		var out = text;
		for (i <- 0 until ((to / 8) - (text.length / 8) + 1)) { out += "\t"; }
		return out + alignText;
	}

}
