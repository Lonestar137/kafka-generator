package logging

import java.util.Properties
import java.util.Calendar
import scala.collection.JavaConverters._

object OutputFunctions {

  def printRed(str: String, newLine: Boolean = true) = {
    if (newLine) {
      Console.println("\u001b[31m" + str + "\u001b[0m")
    } else {
      Console.print("\u001b[31m" + str + "\u001b[0m")
    }
  }
  def printYellow(str: String, newLine: Boolean = true) = {
    if (newLine){
      Console.println("\u001b[33m" + str + "\u001b[0m")
    } else {
      Console.print("\u001b[33m" + str + "\u001b[0m")
    }
  }
  def printGreen(str: String, newLine: Boolean = true) = {
    if (newLine){
      Console.println("\u001b[32m" + str + "\u001b[0m")
    } else {
      Console.print("\u001b[33m" + str + "\u001b[0m")
    }
  }
  def log(value : String) : Unit = {
    println("[" + Calendar.getInstance().getTime().toString() + "] " + value);
  }

}

