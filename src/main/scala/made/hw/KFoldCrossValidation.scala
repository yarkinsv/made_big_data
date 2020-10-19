package made.hw

import scala.util.Random

object KFoldCrossValidation {
  def make(n : Int, folds : Int, trainFraction : Double): List[(List[Int], List[Int])] = {
    var result = List[(List[Int], List[Int])]()
    for (_ <- 1 to folds) {
      var train = Set[Int]()
      var validation = Range(0, n).toSet
      while (train.size < trainFraction * n) {
        val x = Random.nextInt(n)
        train += x
        validation -= x
      }
      result = result.appended((train.toList, validation.toList))
    }
    result
  }
}
