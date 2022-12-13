package day03

import readDayInput

const val ITEMS_PATTERN = "a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z,A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z"
fun main() {
    fun getAlphabetParsed(): List<String> {
        return ITEMS_PATTERN.split(",")
    }

    fun checkIfAlphabetIsCorrect(): Boolean {
        return getAlphabetParsed().size == 52
    }

    fun findPriorityOfThisItem(item: String): Int = getAlphabetParsed().indexOf(item) + 1

    fun partOne(input: List<String>): Int {
        var result = 0
        var numberOfAnomaly = 0
        // step 1 : loop the main list
        input.forEach { rucksack ->
            // step 2 : split the list in two list
            val listOfItems = rucksack.split("")
            val splitListNumber = listOfItems.size / 2
            val listCompartmentOne = listOfItems.subList(0, splitListNumber)
            println("Compartment one contain : $listCompartmentOne")
            val listCompartmentTwo = listOfItems.subList(splitListNumber, listOfItems.lastIndex)
            println("Compartment two contain : $listCompartmentTwo")
            // step 3 : compare list 1 content with list 2 content
            var anomalyFound = false
            listCompartmentOne.forEach { item ->
                if (listCompartmentTwo.contains(item) && !anomalyFound) {
                    anomalyFound = true
                    val priority = findPriorityOfThisItem(item)
                    println("I found a anomaly! $item with priority $priority")
                    result += priority
                    numberOfAnomaly++
                }
            }
        }
        println("All rucksacks has been checked. I've found $numberOfAnomaly anomalies.")
        return result
    }

    fun getTheBadgeOfThisGroup(listRucksackOfTheGroup: MutableList<String>): String {
        // Search in all rucksack the common item
        println("Search for the badge of this group...")
        var result = ""
        getAlphabetParsed().forEach { letter ->
            if (listRucksackOfTheGroup[0].contains(letter) &&
                listRucksackOfTheGroup[1].contains(letter) &&
                listRucksackOfTheGroup[2].contains(letter)
            ) {
                println("It's badge $letter!")
                result = letter
            }
        }
        return result
    }

    fun partTwo(input: List<String>): Int {
        var result = 0
        var groupCounter = 0
        var listRucksackOfTheGroup = mutableListOf<String>()
        var nbGroupOfElves = 0
        // Split group
        input.forEach { elf ->
            groupCounter++
            listRucksackOfTheGroup.add(elf)
            if (groupCounter == 3) {
                nbGroupOfElves++
                println("Group of 3 elves created!")
                // The group is complete, proceed to the badge checker
                result += findPriorityOfThisItem(getTheBadgeOfThisGroup(listRucksackOfTheGroup))
                listRucksackOfTheGroup = mutableListOf()
                groupCounter = 0
            }
        }
        println("$nbGroupOfElves group of elves has been created.")
        return result
    }

    val inputRead = readDayInput(3)

    if (checkIfAlphabetIsCorrect() && partOne(readDayInput(3, true)) == 157) {
        // Test has been passed so we can get the result of part 1
        println("What is the sum of the priorities of those item types?\n${partOne(inputRead)}")
    }

    if (checkIfAlphabetIsCorrect() && partTwo(readDayInput(3, true)) == 70) {
        // Test has been passed so we can get the result of part 2
        println("What is the sum of the priorities of those item types?|\n${partTwo(inputRead)}")
    }
}
