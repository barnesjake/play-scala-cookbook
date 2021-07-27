package model

import play.api.mvc.QueryStringBindable
import controllers.ValueClassBinder.ageQueryStringBindable
import play.api.libs.json.{Format, Json}

case class AgeRange(from: Int, to: Int)

object AgeRange {
  implicit val format: Format[AgeRange] = Json.format[AgeRange]
  implicit val pathBindable: QueryStringBindable[AgeRange] = ageQueryStringBindable
}
