package made.hw
import breeze.linalg._

class LinearRegression {
  var coef = DenseVector[Double]()

  def costFunction(X : DenseMatrix[Double], y : DenseVector[Double], B : DenseVector[Double]) : Double = {
    var e = X * B - y
    e :*= e
    e :/= 2 * (y.length : Double)
    sum(e)
  }

  def fit(X : DenseMatrix[Double], y : DenseVector[Double], alpha : Double, iterations : Int) {
    this.coef = DenseVector.zeros[Double](X.cols)
    for (_ <- 1 to iterations) {
      val h = X * this.coef
      val loss = h - y
      val gradient = X.t * loss / (y.length : Double)
      this.coef := this.coef - alpha * gradient
    }
  }

  def predict(X : DenseMatrix[Double]): DenseVector[Double] = {
    X * this.coef
  }
}
