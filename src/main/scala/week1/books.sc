case class Book(title: String, authors: List[String])

val books: Set[Book] = Set(
  Book(title = "Structure and Interpretation of Computer Programs",
       authors = List("Abelson, Harald", "Sussman, Gerald J.")),
  Book(title = "Programming in Scala",
       authors = List("Odersky, Martin", "Spoon, Lex", "Venners, Bill")),
  Book(title = "Effective Java",
       authors = List("Bloch, Joshua")),
  Book(title = "Effective Java 2",
    authors = List("Bloch, Joshua")),
  Book(title = "Java Puzzlers",
       authors = List("Bloch, Joshua", "Gafter, Neal")),
  Book(title = "Introduction to Functionnal Programming",
    authors = List("Bird, Richard", "Wadler, Phil"))
)

// titles of books whose author's name is "Bird"
val query1 = for(b <- books; a <- b.authors if a startsWith "Bird,") yield b.title
// translating For-expression into equivalent High-order function
//for (x <- e1; y <- e2 ; s ) yield e3 eq e1.flatMap (x => for( y <- e2; s) yield e3)
val query2 = books.flatMap(x => for(a <- x.authors if a startsWith "Bird,") yield x.title)
query1 == query2
//for (x <- e1 if f; s ) yield e2 eq for (x <- e1.withFilter(x => f); s ) yield e2
val query3 = books.flatMap(x => for(a <- x.authors.withFilter(y => y startsWith "Bird") ) yield x.title)
query1 == query3
// for (x <- e1 ) yield e2 eq e1.map(x => e2)
val query4 = books.flatMap(x => x.authors.withFilter(y => y startsWith "Bird").map(z => x.title))
query1 == query4


// titles of books which have "Program" in the title
for(b <- books; if (b.title indexOf "Program") >= 0) yield b.title
// names of authors having written at least two books
for{
  b1 <- books
  b2 <- books
  if b1.title < b2.title
    a1 <- b1.authors
    a2 <- b2.authors
      if a1 == a2
} yield a1

