import kotlin.system.exitProcess

class MainActivity {
    private var money: Int = 0
    fun whenFunc(numb: Int = 0) {
        when (numb) {
            1 -> viewValue()
            2 -> deposit()
            3 -> withdraw()
            else -> println("Invalid option. Please enter a valid number.")
        }
    }

    fun atmBank() {
        println("--------------------------------------------------------")
        println("#### Bank #### \n")
        println("1) View Balance")
        println("2) Deposit")
        println("3) Withdraw")
        println("4) Exit \n")
        print("Enter option number: ")
        val chosenInput: String? = readLine()
        val chosen: Int? = chosenInput?.toIntOrNull()

        when (chosen) {
            in 1..3 -> chosen?.let { whenFunc(it) }
            4 -> exitProcess(0)
            else -> {
                println("Invalid input. Please enter a valid option.")
                atmBank()
            }
        }
        println("--------------------------------------------------------")
    }

    private fun viewValue() {
        println("--------------------------------------------------------")
        println("### View Balance ### \n")
        println("Your money: \u001B[32m\$$money\u001B[0m")
        println("1) Back \n")
        handleUserInput {
            when (it) {
                1 -> atmBank()
                else -> viewValue()
            }
        }
        println("--------------------------------------------------------")
    }

    private fun deposit() {
        println("--------------------------------------------------------")
        println("### Deposit ### \n")
        println("1) $100 Deposit")
        println("2) $500 Deposit ")
        println("3) $1000 Deposit")
        println("4) $1500 Deposit")
        println("5) Custom deposit")
        println("6) Back \n")
        handleUserInput {
            when (it) {
                in 1..4 -> addDeposit(listOf(100, 500, 1000, 1500)[it - 1])
                5 -> customDeposit()
                6 -> atmBank()
                else -> deposit()
            }
        }
        println("--------------------------------------------------------")
    }

    private fun addDeposit(amount: Int) {
        println("--------------------------------------------------------")
        money += amount
        println("\u001B[32m+$$amount\u001B[0m")
        println("Your money is $$money now")
        println("1) Back \n")
        handleUserInput {
            when (it) {
                1 -> deposit()
                else -> addDeposit(amount)
            }
        }
        println("--------------------------------------------------------")
    }

    private fun customDeposit() {
        println("--------------------------------------------------------")
        println("### Custom Deposit ### \n")
        print("Enter amount : ")
        val selectedValue: Int = readLine()?.toIntOrNull() ?: 0

        if (selectedValue <= 0) {
            withListCustom()
        } else {
            println("\u001B[32m+$$selectedValue\u001B[0m")
            money += selectedValue
            println("Your money now is $$money \n")
        }
        println("1) Back")
        println("Enter) Press enter to repeat \n")
        handleUserInput {
            when (it) {
                1 -> deposit()
                else -> customDeposit()
            }
        }
        println("--------------------------------------------------------")
    }

    private fun withdraw() {
        println("--------------------------------------------------------")
        println("### Withdraw ### \n")
        println("1) Withdraw $100")
        println("2) Withdraw $500")
        println("3) Withdraw $1000")
        println("4) Withdraw $1500")
        println("5) Custom withdraw")
        println("6) Back \n")
        handleUserInput {
            when (it) {
                in 1..4 -> withList(listOf(100, 500, 1000, 1500)[it - 1])
                5 -> withListCustom()
                6 -> atmBank()
                else -> withdraw()
            }
        }
        println("--------------------------------------------------------")
    }

    private fun withList(amount: Int) {
        println("--------------------------------------------------------")
        if (money >= amount) {
            money -= amount
            println("\u001B[31m-$$amount\u001B[0m")
            println("Your money is $$money now")
            println("1) Back \n")
            handleUserInput {
                when (it) {
                    1 -> withdraw()
                    else -> withList(amount)
                }
            }
        } else {
            println("\u001B[31mYou don't have enough money to withdraw please deposit \n \u001B[0m")
            println("1) Back \n")
            handleUserInput {
                when (it) {
                    1 -> withdraw()
                    else -> withList(amount)
                }
            }
        }
        println("--------------------------------------------------------")
    }

    private fun withListCustom() {
        println("--------------------------------------------------------")
        println("### Custom Withdraw ### \n")
        print("Enter amount : ")
        val selectedValue: Int = readLine()?.toIntOrNull() ?: 0

        if (selectedValue <= 0) {
            withListCustom()
        } else if (money == 0) {
            println("\u001B[31mYou don't have enough money to withdraw please deposit \n \u001B[0m")
        }
        else if (selectedValue > money) {
            println("\u001B[31mYour withdraw amount larger then what you have\u001B[0m")
            println("\u001B[31mYour amount is $$selectedValue your money $$money \n \u001B[0m")
        } else {
            money -= selectedValue
            println("\u001B[31m-$$selectedValue\u001B[0m")
            println("Your money now is $$money \n")
        }

        println("1) Back")
        handleUserInput {
            when (it) {
                1 -> withdraw()
                else -> withListCustom()
            }
        }
        println("--------------------------------------------------------")
    }

    private fun handleUserInput(action: (Int) -> Unit) {
        print("Enter option number: ")
        val chosenInput: String? = readLine()
        val chosen: Int? = chosenInput?.toIntOrNull()

        when {
            chosen != null -> action(chosen)
            else -> {
                println("Invalid input. Please enter a valid integer.")
                handleUserInput(action)
            }
        }
    }
}

fun main() {
    val mainActivity = MainActivity()
    mainActivity.atmBank()
}
