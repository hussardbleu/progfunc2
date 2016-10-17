import week1._

val data = JObj(Map(
  "firstName" -> JStr("John"),
  "lastName" -> JStr("Smith"),
  "adress" -> JObj(Map(
    "streetAdress" -> JStr("21 2nd Street"),
    "state" -> JStr("NY"),
    "postalCode" -> JNum(10021)
  )
),
  "phoneNumbers" -> JSeq(List(
    JObj(Map(
      "type" -> JStr("home"), "number" -> JStr("212 555-1234")

    )),
    JObj(Map(
      "type" -> JStr("fax"), "number" -> JStr("646 555-4567")

    ))
  )
)


))


def show(json: JSON): String = json match {
  case JSeq(elems) =>
    "[" + (elems map show mkString ", ") + "]"
  case JObj(bindings) =>
    val assocs = bindings map {
      case (key, value) => "\"" + key + "\": " + show(value)
    }
    "{" + (assocs mkString ", ") + "}"
  case JNum(num) => num.toString
  case JStr(str) => '\"' + str + '\"'
  case JBool(b) => b.toString
  case JNull => "null"
}


show(data)

val f: String => String = { case "ping" => "pong" }
f("ping")

val g: PartialFunction[String,String] = { case "ping" => "pong" }
g("ping")
//g("abc")
if (g.isDefinedAt("abc")) g("abc") else ""

val dataList: List[JSON] = List(data)
  for{
    JObj(bindings) <- dataList
    JSeq(phones) = bindings("phoneNumbers")
    JObj(phone) <- phones
    JStr(digits) = phone("number")
    if digits startsWith "212"
  } yield (show(bindings("firstName")), show(bindings("lastName")))

