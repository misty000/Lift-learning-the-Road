import sbt._
import Keys._
import com.github.siasia.{ WebPlugin, PluginKeys }

object Build extends Build {
  lazy val root = Project(id = "sbt-project", base = file("."))
    .settings(basicSettings: _*)
    .settings(resolvers ++=
      Seq( // 这里，我们可以添加一些maven资源库
        "snapshots" at "http://oss.sonatype.org/content/repositories/snapshots",
        "releases"  at "http://oss.sonatype.org/content/repositories/releases",
        "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/",
        "Typesafe Snapshots" at "http://repo.typesafe.com/typesafe/snapshots/"))
    .settings(WebPlugin.webSettings: _*) // 这里设置WebPlugin
    .settings(
      PluginKeys.port in WebPlugin.container.Configuration := 8081, // 自定义Jetty绑定端口
      libraryDependencies ++= dependencies)

  private lazy val basicSettings = Seq(
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

  private val jettyVersion = "8.1.8.v20121106"

  private lazy val dependencies = Seq(
    "org.eclipse.jetty.orbit" % "javax.servlet" % "3.0.0.v201112011016" % "container,provided,test" artifacts Artifact("javax.servlet", "jar", "jar"),
    "org.eclipse.jetty" % "jetty-webapp" % jettyVersion % "container,test")

}
