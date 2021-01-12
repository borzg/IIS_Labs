package lab_6_composite_proxy

interface Ruler {
    fun issueADecree(decree: String)
}

interface Subject {
    fun executeAnOrder(order: String)
}

class Leader(
    val title: String,
    private val subjects: List<Subject>
): Subject {
    override fun executeAnOrder(order: String) {
        println("$title приступает к исполнению приказа")
        subjects.forEach {
            it.executeAnOrder(order)
        }
    }

}

class Worker: Subject {
    override fun executeAnOrder(order: String) {
        println("Рабочий: выполняю приказ сверху: $order")
    }

}

class King(private val subjects: List<Subject>): Ruler {
    override fun issueADecree(decree: String) {
        println("Король издает указ: $decree")
        subjects.forEach {
            it.executeAnOrder(decree)
        }
    }

}

class Oracle(private val confidingKing: King): Ruler {
    override fun issueADecree(decree: String) {
        println("Оракул: гадает по погоде")
        println("Оракул: строит козни и оказывает влияние на короля, чтобы тот издал указ: $decree")
        confidingKing.issueADecree(decree)
    }
}

fun main() {

    val king = King(
        listOf(
            Leader(
                "Герцог 1",
                listOf(
                    Leader(
                        "Граф",
                        listOf(
                            Worker(),
                            Worker(),
                            Worker()
                        )
                    ),
                    Worker(),
                    Worker()
                )
            ),
            Leader(
                "Герцог 2",
                listOf(
                    Worker(),
                    Worker(),
                    Worker()
                )
            )
        )
    )

    val oracle = Oracle(king)

    oracle.issueADecree("Всем носить ботинки с закругленными носами")
}