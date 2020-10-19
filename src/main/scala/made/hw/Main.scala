package made.hw
import java.io._

import breeze.linalg._


object Main {
  var FOLDS = 4
  var TRAIN_FRAC = 0.8

  var ALPHA = 0.0005
  var ITERATIONS = 10000

  def main(args: Array[String]): Unit = {
    val trainData = csvread(new File(args(0)),',', skipLines=1)
    var testData = csvread(new File(args(1)),',', skipLines=1)

    val X_train = trainData(0 until trainData.rows, 0 to trainData.cols - 2)
    val y_train = trainData(0 until trainData.rows, trainData.cols - 1)

    val X_test = testData

    val kfolds = KFoldCrossValidation.make(X_train.rows, FOLDS, TRAIN_FRAC)
    var i = 0
    for (fold <- kfolds) {
      i += 1
      val model = new LinearRegression()
      val X_fold_train = X_train(fold._1, ::).toDenseMatrix
      val y_fold_train = y_train(fold._1).toDenseVector
      model.fit(X_fold_train, y_fold_train, ALPHA, ITERATIONS)
      val mse_loss = model.costFunction(X_fold_train, y_fold_train, model.coef)
      println(f"Fold_$i MSE: $mse_loss%.6f")
    }

    val model = new LinearRegression()
    model.fit(X_train, y_train, ALPHA, ITERATIONS)
    val predicted = model.predict(X_test)
    csvwrite(new File(args(2)), predicted.toDenseMatrix.t)
  }
}
