package lab_5_graph_search_iterator

class OnFootIterator(towns: List<Town>): CountryIterator {

    private val sortedTowns: List<Town> = towns.sortedBy {
        it.name
    }

    private var currentTown = 0

    override fun hasNext(): Boolean =
        currentTown != sortedTowns.size

    override fun next(): Town {
        return sortedTowns[currentTown++]
    }
}