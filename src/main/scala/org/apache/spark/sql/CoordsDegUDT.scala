package org.apache.spark.sql

import com.github.mvinesn.Climate.CoordsDeg
import org.apache.spark.sql.catalyst.InternalRow
import org.apache.spark.sql.types._
import org.apache.spark.unsafe.types.UTF8String

// This is only necessary if using the com.github.mvinesn.Climate.degCoordsWithUDT
class CoordsDegUDT extends UserDefinedType[CoordsDeg] {
      override def sqlType: DataType = StructType(Seq(
        StructField("degrees", IntegerType, nullable = false),
        StructField("minutes", IntegerType, nullable = false),
        StructField("seconds", IntegerType, nullable = false),
        StructField("hemisphere", StringType, nullable = false)
      ))

      override def userClass: Class[CoordsDeg] = classOf[CoordsDeg]

      override def serialize(obj: CoordsDeg): InternalRow = {
        InternalRow(obj.degrees, obj.minutes, obj.seconds, UTF8String.fromString(obj.hemisphere.toString))
      }

      override def deserialize(datum: Any): CoordsDeg = {
        datum match {
          case row: InternalRow =>
            val degrees = row.getInt(0)
            val minutes = row.getInt(1)
            val seconds = row.getInt(2)
            val hemisphere = row.getString(3)
            CoordsDeg(degrees, minutes, seconds, hemisphere.charAt(0))
        }
      }
}
