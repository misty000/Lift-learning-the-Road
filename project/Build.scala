import sbt._
import Keys._

object Build extends Build {
  import BuildSettings._
  import Dependencies._

  override lazy val settings = super.settings :+ {
    shellPrompt := (s => Project.extract(s).currentProject.id + " > ")
  }
  
  ///////////////////////////////////////////////////////////////
  // root project
  ///////////////////////////////////////////////////////////////
  lazy val root = Project("root", file("."))
    .aggregate(
         learnLiftBlank,
         yangbajingCommon, yangbajingUtil, yangbajingPersistence)
       .settings(basicSettings: _*)
       .settings(noPublishing: _*)

  ///////////////////////////////////////////////////////////////
  // projects
  ///////////////////////////////////////////////////////////////
  lazy val learnLiftBlank = webProject("learn-lift_blank", file("learn/lift_blank"), 38081)
    .settings(
      name := "learn-lift_blank",
      description := "Lift 2.5官方模板")

  ///////////////////////////////////////////////////////////////
  // yangbajing projects
  ///////////////////////////////////////////////////////////////
  lazy val yangbajingPersistence = yangbajingProject("yangbajing-persistence", file("core/persistence"))
    .dependsOn(yangbajingUtil)
    .settings(
      description := "羊八井平台实用工具库",
      libraryDependencies ++= (
        compile(_salat) ++
        compile(_postgresql) ++
        compile(_squeryl)))
     
  lazy val yangbajingUtil = yangbajingProject("yangbajing-util", file("core/util"))
    .dependsOn(yangbajingCommon)
    .settings(
      description := "羊八井平台实用工具库",
      libraryDependencies ++= (
        //compile(_guava) ++
	//compile(_apachePoi) ++
        //compile(_sfreechart) ++
        //compile(_jacksonScalaModule) ++
	compile(_liftwebCommon)))
     
  lazy val yangbajingCommon = yangbajingProject("yangbajing-common", file("core/common"))
    .settings(
      description := "羊八井平台公共库",
      libraryDependencies ++= (
        compile(_bson) ++
	compile(_jodaTime) ++
        //compile(_nscalaTime) ++
	compile(_bouncycastle) ++
	//compile(_googleHashMap) ++
	compile(_netty) ++
	//compile(_scalaIo) ++
        compile(_javaxJta) ++
	compile(_akkaActor) ++
	//compile(_akkaRemote) ++
	compile(_slf4j) ++
	compile(_logback)))

  private def webProject(id: String, base: File, iPort: Int): Project = {
    import com.github.siasia.{ WebPlugin, PluginKeys }
    yangbajingProject(id, base)
      .settings(WebPlugin.webSettings: _*)
      .settings(
	PluginKeys.port in WebPlugin.container.Configuration := iPort,
	libraryDependencies ++= (
          dependentRange(_servlet30, "container,test") ++ 
          dependentRange(_jetty, "container,test") ++ 
          dependentRange(_liftweb, "compile,test")))
  }
   
  private def yangbajingProject(id: String, base: File): Project =
      Project(id, base)
      .settings(basicSettings: _*)
      .settings(
        resolvers ++= Seq(
          "release" at "http://mvn-adamgent.googlecode.com/svn/maven/release",
          "snapshots" at "http://oss.sonatype.org/content/repositories/snapshots",
          "releases"  at "http://oss.sonatype.org/content/repositories/releases",
          "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/",
          "Typesafe Snapshots" at "http://repo.typesafe.com/typesafe/snapshots/"),
        libraryDependencies ++= (test(_scalatest)))

}

