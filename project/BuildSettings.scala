import sbt._
import Keys._
import com.github.siasia.PluginKeys

object BuildSettings {
  val VERSION = "0.1"
    
  lazy val basicSettings = Seq(
    version               := VERSION,
    homepage              := Some(new URL("https://github.com/yangbajing/Lift-learning-the-Road")),
    organization          := "Yangbajing Studio",
    organizationHomepage  := Some(new URL("http://my.oschina.net/yangbajing")),
    startYear             := Some(2012),
    licenses              := Seq("Apache 2" -> new URL("http://www.apache.org/licenses/LICENSE-2.0.txt")),
    scalaVersion          := "2.10.0",
    scalacOptions         := Seq(
      "-encoding", "utf8",
      "-target:jvm-1.7",
      "-unchecked",
      "-feature",
//      "-language:postfixOps",
//      "-language:implicitConversions",
//      "-Xlog-reflective-calls",
      "-Ywarn-adapted-args",
      "-deprecation"
    ),
    javacOptions          := Seq(
      "-encoding", "utf8",
      "-Xlint:unchecked",
      "-Xlint:deprecation"
    ),
    PluginKeys.scanDirectories in Compile := Nil, // 使用JRebel需要
    offline               := true
  )

  lazy val noPublishing = Seq(
    publish := (),
    publishLocal := ()
  )

}

