trait Generator[+T]{
  self =>

  def generate: T

  def map[S](f: T => S): Generator[S] = new Generator[S] {
    def generate: S = f(self.generate) // call method generate of object T
  }
  def flatMap[S](f: T => Generator[S]): Generator[S] = new Generator[S] {
    def generate: S = f(self.generate).generate // call method generate of object T
  }
}

val integers = new Generator[Int] {
  val rand = new java.util.Random
  def generate = rand.nextInt()
}

/**
 *val booleans = new Generator[Boolean] {
 * def generate = integers.generate > 0
}*/

val booleans = for (x <- integers) yield x > 0

/**
  * val pairs = new Generator[(Int,Int)] {
  * def generate = (integers.generate, integers.generate)
  * }
  */
val pairs = for {
  x <- integers
  y <- integers
} yield (x,y)

def single[T](x: T): Generator[T] = new Generator[T] {
  def generate: T = x
}

def choose(lo: Int, hi: Int): Generator[Int] =
  for(x <- integers) yield lo + x % (hi - lo)

def oneOf[T](xs: T*): Generator[T]=
  for(idx <- choose(0, xs.length)) yield xs(idx)

// Generator for List[Int]
def lists: Generator[List[Int]] = for {
  isEmpty <- booleans
  list <- if(isEmpty) emptyLists else nonEmptyLists
} yield list

def emptyLists = single(Nil)

def nonEmptyLists = for {
  head <- integers
  tail <- lists

} yield head :: tail

// Generator for Tree

trait Tree

case class Inner(left: Tree, right: Tree) extends Tree
case class Leaf(x: Int) extends Tree

def trees: Generator[Tree] = for {
  isLeaf <- booleans
  tree <- if(isLeaf) leafs else innerNodes
} yield tree

def leafs = for{
  x <- integers
} yield Leaf(x)

def innerNodes = for {
  left <- trees
  right <- trees
} yield Inner(left, right)

trees.generate


