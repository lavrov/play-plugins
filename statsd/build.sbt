import play.core.PlayVersion

name := "play-statsd"
    
organization := "com.typesafe.play.plugins"

version := "2.4.0"

scalaVersion in ThisBuild := "2.11.8"

resolvers += "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/"

libraryDependencies ++= Seq(
  "com.typesafe.play" %% "play" % PlayVersion.current % "provided",
  "com.typesafe.play"  %% "play-test" % PlayVersion.current % "test",
  specs2 % "test"
)

resolvers += "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases"

parallelExecution in Test := false

testOptions += Tests.Argument(TestFrameworks.JUnit, "-q", "-v")

publishTo <<= version { version: String =>
  val nexus = "https://private-repo.typesafe.com/typesafe/"
  if (version.trim.endsWith("SNAPSHOT")) Some("snapshots" at nexus + "maven-snapshots/") 
  else                                   Some("releases"  at nexus + "maven-releases/")
}
 
lazy val root: Project = project in file(".") aggregate sample

lazy val sample = project in file("sample/sample-statsd") dependsOn (LocalRootProject % "test->test;compile->compile")
