name := "play-scala-cookbook"

version := "0.1"

scalaVersion := "2.13.3"
lazy val root = (project in file(".")).enablePlugins(PlayScala)
libraryDependencies += guice
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "5.0.0" % Test
libraryDependencies += "org.reactivemongo" %% "play2-reactivemongo" % "0.20.11-play27"