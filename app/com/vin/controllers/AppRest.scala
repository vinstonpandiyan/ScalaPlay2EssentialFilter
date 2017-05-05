package com.vin.controllers

import play.api.libs.json.Json
import play.api.mvc._

object AppRest extends Controller {

  def test = Action {
    Ok(Json.obj("id" -> "1", "name" -> "Vinston"))
  }

}
