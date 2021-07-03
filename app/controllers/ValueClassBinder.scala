package controllers

import model.AgeRange
import play.api.libs.json._
import play.api.mvc.{PathBindable, QueryStringBindable}

import java.time.LocalDateTime
import scala.reflect.runtime.universe.{typeOf, TypeTag}

object ValueClassBinder {

  def valueClassBinder[A: Reads](fromAtoString: A => String)(implicit stringBinder: PathBindable[String]): PathBindable[A] = {

      def parseString(str: String): Either[String, A] =
        JsString(str).validate[A] match {
          case JsSuccess(a, _) => Right(a)
          case JsError(error)  => Left(s"No valid value in path: $str. Error: $error")
        }

    new PathBindable[A] {
      override def bind(key: String, value: String): Either[String, A] =
        stringBinder.bind(key, value).flatMap(parseString)

      override def unbind(key: String, a: A): String =
        stringBinder.unbind(key, fromAtoString(a))
    }
  }

  def bindableA[A: TypeTag: Reads](fromAtoString: A => String): QueryStringBindable[A] = new QueryStringBindable.Parsing[A](
    parse = JsString(_).as[A],
    fromAtoString,
    {
      case (key: String, e: JsResultException) => s"Cannot parse param $key as ${typeOf[A].typeSymbol.name.toString}. ${e.errors.headOption.flatMap(_._2.headOption.map(_.message)).getOrElse("")}"
      case (key: String, e)                    => s"Cannot parse param $key as ${typeOf[A].typeSymbol.name.toString}. ${e.toString}"
    }
  )

  implicit val localDateTimeBinder: QueryStringBindable[LocalDateTime] = bindableA[LocalDateTime](_.toString)

  implicit def ageQueryStringBindable(implicit intBinder: QueryStringBindable[Int]): QueryStringBindable[AgeRange] = new QueryStringBindable[AgeRange] {
    override def bind(key: String, params: Map[String, Seq[String]]): Option[Either[String, AgeRange]] = {
      for {
        from <- intBinder.bind("from", params)
        to <- intBinder.bind("to", params)
      } yield {
        (from, to) match {
          case (Right(from), Right(to)) => Right(AgeRange(from, to))
          case _                        => Left("Unable to bind an AgeRange")
        }
      }
    }

    override def unbind(key: String, ageRange: AgeRange): String = {
      intBinder.unbind("from", ageRange.from) + "&" + intBinder.unbind("to", ageRange.to)
    }

  }
}
