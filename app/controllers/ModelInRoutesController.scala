package controllers

import model.{AgeRange, Cat}

import javax.inject.Inject
import play.api.i18n.I18nSupport
import play.api.mvc.{AbstractController, Action, AnyContent, ControllerComponents}

class ModelInRoutesController @Inject() (val cc: ControllerComponents) extends AbstractController(cc) with I18nSupport {
  def cat(cat: Cat): Action[AnyContent] = Action {
    Ok("Path binder value: " + cat.name)
  }

  def age(age: AgeRange): Action[AnyContent] = Action {
    Ok(s"From: ${age.from.toString} , to: ${age.to.toString}")
  }
}
