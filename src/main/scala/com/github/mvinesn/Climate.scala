package com.github.mvinesn

import org.apache.spark.sql.expressions.UserDefinedFunction
import org.apache.spark.sql.functions._
import org.apache.spark.sql.types._
import org.apache.spark.sql.{CoordsDegUDT, DataFrame, Row, SparkSession}


object Climate {

  lazy val session: SparkSession =
    SparkSession.builder().config("spark.master", "local").getOrCreate()

  def loadDataframe(): DataFrame =
    session.read
      .option("header", "true")
      .option("mode", "DROPMALFORMED")
      .csv("data/GlobalLandTemperaturesByCity.csv")


  def toFarenheit(celcius: Double): Double = (celcius * 9.0 / 5.0) + 32.0

  // Spark UDF
  def farenheit: UserDefinedFunction = udf((celcius: Double) => toFarenheit(celcius))

  @SQLUserDefinedType(udt = classOf[CoordsDegUDT])
  case class CoordsDeg(degrees: Int, minutes: Int, seconds: Int, hemisphere: Char)

  def toDegCoords(coordsDecmal: String): CoordsDeg = {
    // We can add conditions like max and min allowed values to make it safer
    val hem = coordsDecmal.last
    val coords: Seq[String] = coordsDecmal.dropRight(1).split('.')

    val deg = coords.head.toInt
    val coordsDec = coords.last.toInt

    val min: Int = coordsDec * 60 / 100
    val minRem: Int = (coordsDec * 60) % 100
    val sec: Int = minRem * 60 / 100

    CoordsDeg(deg, min, sec.toInt, hem)
  }

  val schema = StructType(Seq(
    StructField("degrees", IntegerType, nullable = false),
    StructField("minutes", IntegerType, nullable = false),
    StructField("seconds", IntegerType, nullable = false),
    StructField("hemisphere", StringType, nullable = false)
  ))

  // Spark UDF
  def degCoords: UserDefinedFunction = udf((decCoords: String) => {
    val coords = toDegCoords(decCoords)
    Row(coords.degrees, coords.minutes, coords.seconds, coords.hemisphere)
  }, schema)


  def degCoordsWithUDT: UserDefinedFunction = udf((decCoords: String) => toDegCoords(decCoords))

}
