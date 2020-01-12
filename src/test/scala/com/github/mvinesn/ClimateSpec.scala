package com.github.mvinesn

import org.scalatest.FunSuite
import org.scalatest.MustMatchers
import Climate._

class ClimateSpec extends FunSuite with MustMatchers {

  test("Celcius to Farenheit conversion") {
    toFarenheit(0.0) mustBe 32.0
    toFarenheit(100.0) mustBe 212.0
    toFarenheit(-10.0) mustBe 14.0
  }

  test("Decimal to degree coordinates conversion") {
    toDegCoords("0.0N") mustBe CoordsDeg(0, 0, 0, 'N')
    toDegCoords("61.44N") mustBe CoordsDeg(61, 26, 24, 'N')
    toDegCoords("25.40E") mustBe CoordsDeg(25, 24, 0, 'E')
    toDegCoords("47.04S") mustBe CoordsDeg(47, 2, 24, 'S')
    toDegCoords("73.48W") mustBe CoordsDeg(73, 28, 48, 'W')
  }

}
