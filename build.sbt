organization := ""

name := "LiftBasic"

version := "0.1"

scalaVersion := "2.9.1"

// scalacOptions += "-deprecation"

seq(webSettings :_*)

//Lift
libraryDependencies ++= {
  val liftVersion = "2.4"
  Seq(
    "net.liftweb" %% "lift-webkit" % liftVersion % "compile->default",
    "net.liftweb" %% "lift-mapper" % liftVersion % "compile->default"
  )
}

libraryDependencies ++= Seq(
  "javax.servlet" % "javax.servlet-api" % "3.0.1" % "compile->default",
  "ch.qos.logback" % "logback-classic" % "0.9.27" % "compile->default",
  "org.eclipse.jetty" % "jetty-webapp" % "7.3.0.v20110203" % "container"
)

// If using JRebel uncomment next line
// scanDirectories in Compile := Nil