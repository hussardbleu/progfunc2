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
