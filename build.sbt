import com.typesafe.sbt.SbtScalariform.ScalariformKeys
import scalariform.formatter.preferences._
import sbt.{Def, Test, file, _}

name := "play-scala-cookbook"

version := "0.1"

scalaVersion := "2.13.3"
lazy val microservice = (project in file("."))
  .enablePlugins(PlayScala)
  .settings(
    scalariformSettings
  ).settings(
  libraryDependencies ++= Seq(
    guice,
    ws,
    "org.scalatestplus.play" %% "scalatestplus-play" % "5.0.0" % Test,
    "org.reactivemongo" %% "play2-reactivemongo" % "0.20.11-play27"
  ))
  .settings(
    routesGenerator := InjectedRoutesGenerator
  )
// import models to be used within app.routes
//  .settings(
//    routesImport ++= Seq(
//      "model.modelName"
//    ))
// change port using this
//  .settings(
//    PlayKeys.playDefaultPort := 9000,
//  )

//libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.3" % "test"


lazy val scalariformSettings: Def.SettingsDefinition = {
  // description of options found here -> https://github.com/scala-ide/scalariform
  ScalariformKeys.preferences := ScalariformKeys.preferences.value
    .setPreference(AlignArguments, true)
    .setPreference(AlignParameters, true)
    .setPreference(AlignSingleLineCaseStatements, true)
    .setPreference(AllowParamGroupsOnNewlines, true)
    .setPreference(CompactControlReadability, false)
    .setPreference(CompactStringConcatenation, false)
    .setPreference(DanglingCloseParenthesis, Force)
    .setPreference(DoubleIndentConstructorArguments, true)
    .setPreference(DoubleIndentMethodDeclaration, true)
    .setPreference(FirstArgumentOnNewline, Force)
    .setPreference(FirstParameterOnNewline, Force)
    .setPreference(FormatXml, true)
    .setPreference(IndentLocalDefs, true)
    .setPreference(IndentPackageBlocks, true)
    .setPreference(IndentSpaces, 2)
    .setPreference(IndentWithTabs, false)
    .setPreference(MultilineScaladocCommentsStartOnFirstLine, false)
    .setPreference(NewlineAtEndOfFile, true)
    .setPreference(PlaceScaladocAsterisksBeneathSecondAsterisk, false)
    .setPreference(PreserveSpaceBeforeArguments, true)
    .setPreference(RewriteArrowSymbols, false)
    .setPreference(SpaceBeforeColon, false)
    .setPreference(SpaceBeforeContextColon, false)
    .setPreference(SpaceInsideBrackets, false)
    .setPreference(SpaceInsideParentheses, false)
    .setPreference(SpacesAroundMultiImports, false)
    .setPreference(SpacesWithinPatternBinders, true)
}