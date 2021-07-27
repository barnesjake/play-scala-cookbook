package model

import controllers.ValueClassBinder.valueClassBinder
import play.api.libs.functional.syntax.toInvariantFunctorOps
import play.api.libs.json.Format
import play.api.mvc.PathBindable

case class Cat(name: String)

object Cat {
  implicit val format: Format[Cat] = implicitly[Format[String]].inmap(Cat(_), _.name)
  implicit val pathBindable: PathBindable[Cat] = valueClassBinder(_.name)
}
