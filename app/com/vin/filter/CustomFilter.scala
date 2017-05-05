package com.vin.filter

import javax.inject.Inject

import play.api.http.HttpFilters
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.libs.iteratee.{Enumeratee, Iteratee}
import play.api.mvc._
import play.libs.Json

import scala.concurrent.Future

/**
  * Created by vpandiyan001
  */
class CustomFilter @Inject() (loggerFilter: LoggerFilter) extends HttpFilters {
  val filters = Seq(loggerFilter)
}

/**
  * Play provides more powerful and a lower level filter API called EssentialFilter
  * which gives you full access to the body of the request and can be add or remove
  * any parameters in the body content/headers. This API allows you to wrap EssentialAction
  * with another action, and when we invoke next, we get back an Accumulator. You could compose
  * this with an Akka Streams Flow using the through method.
  *
  * Created by vpandiyan001
  */
class LoggerFilter extends EssentialFilter {

  val logger = play.api.Logger(this.getClass)

  def apply(nextFilter: EssentialAction): EssentialAction = new EssentialAction { request =>
    def apply(requestHeader: RequestHeader) = {
      val startTime = System.currentTimeMillis

      nextFilter(requestHeader).map { result =>
        val endTime = System.currentTimeMillis
        val requestTime = endTime - startTime

        if (result.header.headers.get("Content-Type").getOrElse("").contains("application/json")) {
          val bytesToString: Enumeratee[Array[Byte], String] = Enumeratee.map[Array[Byte]] {
            bytes => new String(bytes)
          }
          val consume: Iteratee[String, String] = Iteratee.consume[String]()
          val resultBody: Future[String] = result.body |>>> bytesToString &>> consume
          resultBody.map {
            body =>
              val jsonBody = Json.parse(body)
              val responseBody = Json.prettyPrint(jsonBody)
              logger.debug(s"RequestTime: ${requestTime.toString}  ResponseBody: $responseBody")
          }
        }
        result.withHeaders("Request-Time" -> requestTime.toString)
        result
      }
    }
  }

}
