case class Book(title: String, authors: List[String])

val books: List[Book] = List(
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
for(b <- books; a <- b.authors if a startsWith "Bird,") yield b.title
// titles of books which have "Program" in the title
for(b <- books; if (b.title indexOf "Program") >= 0) yield b.title
// names of authors having written at least two books
( for{
  b1 <- books
  b2 <- books
  if b1.title < b2.title
    a1 <- b1.authors
    a2 <- b2.authors
      if a1 == a2
} yield a1
  ).distinct
