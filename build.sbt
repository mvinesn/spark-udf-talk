resolvers += "Spark Packages Repo" at "http://dl.bintray.com/spark-packages/maven"

name := "spark-udf-talk"

version := "0.0.1"

scalaVersion := "2.11.12"

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-sql" % "2.4.4" % "provided",
  "org.scalatest" %% "scalatest" % "3.0.1" % "test"
)

// test suite settings
fork in Test := true
javaOptions ++= Seq("-Xms512M", "-Xmx2048M", "-XX:MaxPermSize=2048M", "-XX:+CMSClassUnloadingEnabled")

// Show runtime of tests
testOptions in Test += Tests.Argument(TestFrameworks.ScalaTest, "-oD")