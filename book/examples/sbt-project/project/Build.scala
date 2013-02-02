import sbt._
import Keys._

object Build extends Build {
  lazy val root = Project(
    id = "sbt-project", 
    base = file("."),
    settings = Project.defaultSettings ++ basicSettings)

  lazy val basicSettings = Seq(
    version := "0.1",
    scalaVersion := "2.10.0",
    homepage := Some(new URL("https://github.com/yangbajing")),
    organization := "Yangbajing Studio",
    organizationHomepage := Some(new URL("http:/my.oschina.net/yangbajing")),
    startYear := Some(2013),
    licenses := Seq("Apache 2" -> new URL("http://www.apache.org/licenses/LICENSE-2.0.txt")),
    scalacOptions := Seq(
      "encoding", "utf8",
      "-target:jvm-1.7",
      "-unchecked",
      "-deprecation"),
    javacOptions := Seq(
      "-encoding", "utf8",
      "-Xlint:unchecked",
      "-Xlint:deprecation"),
    offline := true)

}

