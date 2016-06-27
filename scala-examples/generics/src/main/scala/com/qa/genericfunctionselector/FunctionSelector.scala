package com.qa.genericfunctionselector

/**
 * @author jthompson
 */
object FunctionSelector {
	executeOptionString { selectOption(1) }
	executeOptionInt { selectOption(2) }
	executeOption { selectOption(1) }

	def selectOption[T](selection: Int) = {
		selection match {
			case 1 =>
				(a: T) => println("Added " + a)
				case 2 =>
				(a: T) => println("Removed " + a)
				case _ =>
				(a: T) => println("Unknown Method")
		}
	}

	def executeOptionString(selection: (String) => Unit) {
		selection("Tea")
	}

	def executeOptionInt(selection: (Int) => Unit) {
		selection(2)
	}

	def executeOption(selection: (Any) => Unit) {
		selection(new Thing)
	}
}