package model

import play.api.libs.functional.syntax.toInvariantFunctorOps
import play.api.libs.json.{Format, Json}

case class PostExample(
    exampleString:    String,
    exampleInt:       Int,
    someOtherExample: SomeOtherExample
)

object PostExample {
  implicit val format: Format[PostExample] = Json.format[PostExample]
}

case class SomeOtherExample(inlineExample: String)
object SomeOtherExample {
  implicit val format: Format[SomeOtherExample] = implicitly[Format[String]].inmap(SomeOtherExample(_), _.inlineExample)
}
