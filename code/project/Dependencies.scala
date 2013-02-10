import sbt._
import Keys._

object Dependencies {
  def dependentRange(dep: ModuleID, range: String): Seq[ModuleID] = dependentRange(Seq(dep), range)
  def dependentRange(deps: Seq[ModuleID], range: String): Seq[ModuleID] = deps map (_ % range)

  def compile(dep: ModuleID): Seq[ModuleID] = compile(Seq(dep))
  def compile(deps: Seq[ModuleID]): Seq[ModuleID] = deps map (_ % "compile")

  def provided(dep: ModuleID): Seq[ModuleID] = provided(Seq(dep))
  def provided(deps: Seq[ModuleID]): Seq[ModuleID] = deps map (_ % "provided")

  def test(dep: ModuleID): Seq[ModuleID] = test(Seq(dep))
  def test(deps: Seq[ModuleID]): Seq[ModuleID] = deps map (_ % "test")

  def runtime(dep: ModuleID): Seq[ModuleID] = runtime(Seq(dep))
  def runtime(deps: Seq[ModuleID]): Seq[ModuleID] = deps map (_ % "runtime")

  def container(dep: ModuleID): Seq[ModuleID] = container(Seq(dep))
  def container(deps: Seq[ModuleID]): Seq[ModuleID] = deps map (_ % "container")

  val liftVersion = "2.5-M4"

  val _liftwebCommon = "net.liftweb" %% "lift-webkit" % liftVersion

  val _liftweb = Seq(
      "net.liftmodules" %% "lift-jquery-module" % (liftVersion + "-2.1"),
      "net.liftmodules" %% "textile" % (liftVersion + "-1.3"),
      "net.liftweb" %% "lift-webkit" % liftVersion)

  val akkaVersion = "2.1.0"

  val _akkaActor = "com.typesafe.akka" %% "akka-actor" % akkaVersion
  val _akkaRemote = "com.typesafe.akka" %% "akka-remote" % akkaVersion
  val _akkaSlf4j = "com.typesafe.akka" %% "akka-slf4j" % akkaVersion

  val scalatestVersion = "1.9.1"

  val _scalatest = "org.scalatest" %% "scalatest" % scalatestVersion
//    "org.scalamock" %% "scalamock-scalatest-support" % "2.4")

  val _scalaIo = Seq(
    "com.github.scala-incubator.io" %% "scala-io-core" % "0.4.1",
    "com.github.scala-incubator.io" %% "scala-io-file" % "0.4.1")

  val _squeryl = "org.squeryl" %% "squeryl" % "0.9.5-6"

  val salatVersion = "1.9.2-SNAPSHOT"
  val _salat = Seq(
    "com.novus" %% "salat-core" % salatVersion,
    "com.novus" %% "salat-util" % salatVersion)

  val _nscalaTime = "com.github.nscala-time" %% "nscala-time" % "0.2.0"
    
  val _casbah = "org.mongodb" %% "casbah" % "2.5.0"

  val _sfreechart = "com.github.wookietreiber.sfreechart" %% "sfreechart" % "latest.integration"
 
  val _jacksonScalaModule = "com.fasterxml.jackson.module" %% "jackson-module-scala" % "2.1.3"

  val _markwrap = "org.clapper" %% "markwrap" % "1.0.0"

  val _jetty = {
    val jettyVersion = "8.1.8.v20121106"
    "org.eclipse.jetty" % "jetty-webapp" % jettyVersion
  }

  val _servlet30 = "org.eclipse.jetty.orbit" % "javax.servlet" % "3.0.0.v201112011016" artifacts Artifact("javax.servlet", "jar", "jar")

  val _bson = "org.mongodb" % "bson" % "2.9.3"

  val _netty = "io.netty" % "netty" % "3.5.8.Final"

  val _ehcache = "net.sf.ehcache" % "ehcache-core" % "2.6.2"

  val _slf4j = "org.slf4j" % "slf4j-api" % "1.7.1"

  val _logback = "ch.qos.logback" % "logback-classic" % "1.0.9"

  val _rhino = "org.mozilla" % "rhino" % "1.7R4"

  val _googleHashMap = "com.googlecode.concurrentlinkedhashmap" % "concurrentlinkedhashmap-lru" % "1.3.1"

  val _guava = "com.google.guava" % "guava" % "14.0-rc2"

  val _pegdown = "org.pegdown" % "pegdown" % "1.2.1"

  val _bouncycastle = "org.bouncycastle" % "bcprov-ext-jdk15on" % "1.47"

  val _jodaTime = Seq(  
    "joda-time" % "joda-time" % "2.1",
    "org.joda" % "joda-convert" % "1.2")

  val _quartz = "org.quartz-scheduler" % "quartz" % "2.1.6"

  val _javaxMail = "javax.mail" % "mail" % "1.4.5"

  val _javaxActivation = "javax.activation" % "activation" % "1.1.1"

  val _javaxJta = "javax.transaction" % "jta" % "1.1"

  val _commonsEmail = "org.apache.commons" % "commons-email" % "1.2"

  val _apachePoi = Seq(
    "org.apache.poi" % "poi" % "3.8",
    "org.apache.poi" % "poi-scratchpad" % "3.8",
    "org.apache.poi" % "poi-ooxml" % "3.8",
    "org.apache.poi" % "poi-ooxml-schemas" % "3.8")

  val _commonsFileupload = "commons-fileupload" % "commons-fileupload" % "1.2.2"

  val _h2 = "com.h2database" % "h2" % "1.3.168"

  val _mysql = "mysql" % "mysql-connector-java" % "5.1.21"

  //val _postgresql = "postgresql" % "postgresql" % "9.1-901.jdbc4"
  val _postgresql = "postgresql" % "postgresql" % "9.2-1002.jdbc4"

}
