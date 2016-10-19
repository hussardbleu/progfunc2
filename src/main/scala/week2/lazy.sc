def expr = {
  val x = {print("x"); 1}
  lazy val y = {print("y"); 2}
  def z = {print("z"); 3}
  z + y + x + z + y + x
}

expr

val x = {print("x"); 1}
lazy val y = {print("y"); 2}
def z = {print("z"); 3}


def from(n: Int): Stream[Int] = n #:: from(n + 1)

def fromWrong(n: Int): List[Int] = n :: fromWrong(n + 1)

from(3)
//fromWrong(3)

val nats = from(0)
val m4s = nats map (_ * 4)
(m4s take 100).toList


/**
  * Crible d'Eratosthène
  * */

def sieve(s: Stream[Int]): Stream[Int] =
  s.head #:: sieve(s.tail filter(_ % s.head != 0))

val primes = sieve(from(2))
(primes take 100).toList


/**
  * Newton method for square root
  * */
def sqrtStream(x: Double): Stream[Double] = {
  def improve(guess: Double) = (guess + x/ guess) /2
  lazy val guesses: Stream[Double] = 1 #:: (guesses map improve)
  guesses
}

(sqrtStream(9) take 100) toList
