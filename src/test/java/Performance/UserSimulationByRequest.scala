package Performance

import io.gatling.core.Predef._
import io.gatling.http.Predef._

import scala.concurrent.duration._
import scala.language.postfixOps


class UserSimulationByRequest  extends Simulation {

  val sentHeaders = Map("Content-Type" -> "application/json")


  val getUerById = scenario("get user")
      .exec(http("GET /customer/0000017815f6270e-a673d71b48ca0001")
      .get("http://add9a025cb6354e6fba0b46fd2cf2dd4-1410882696.ap-south-1.elb.amazonaws.com/customer/00000178125387f2-a673d71b48ca0001")
      .check(status.is(200)))




  val CreateUsers = scenario("create")
    .pause(25 milliseconds)
    .exec(http("POST /customer")
      .post("http://add9a025cb6354e6fba0b46fd2cf2dd4-1410882696.ap-south-1.elb.amazonaws.com/customer")
      .headers(sentHeaders)
      .body(StringBody("""{ "firstName": "Thilina", "lastName": "Jayasinghe", "address": "Piliyandala", "email": "tjay@gmail.com"}"""))
      .check(status.is(500)))



  setUp(

    getUerById.inject(rampUsers(10) during (10 seconds)),
    CreateUsers.inject(rampUsers(5) during (10 seconds))

  )

}