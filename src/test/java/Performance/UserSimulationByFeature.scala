package Performance

import com.intuit.karate.gatling.PreDef._
import io.gatling.core.Predef._
import scala.concurrent.duration._
import scala.language.postfixOps


class UserSimulationByFeature  extends Simulation {


  val getAllUsers = scenario("Get Customer Details").exec(karateFeature("classpath:Api/customers/customer.feature"))
  val CreateUsers = scenario("Create customer").exec(karateFeature("classpath:Api/customers/createCustomers.feature"))

  setUp(

    getAllUsers.inject(rampUsers(10) during (10 seconds)),
    CreateUsers.inject(rampUsers(5) during (10 seconds))

  )

}