package made.hw

import org.junit._
import Assert._

@Test
class LinearRegressionTest {

  @Test
  def testMessage(): Unit = {
    var reg = new LinearRegression()
    assertEquals("Hello World!", "Hello World!")
  }
}