fun main() {
   val answer = minFlips(8, 3, 5)
   println(answer)
}

 fun minFlips(a: Int, b: Int, c: Int): Int {
     var mutableA = a
     var mutableB = b
     var mutableC = c
     
     var counter = 0
     
     do {
         var modA = mutableA % 2
         var modB = mutableB % 2
         var modC = mutableC % 2
         
         if (modC == 0) {
             counter += modA
             counter += modB
         }
         
         if (modC == 1 && modA == 0 && modB == 0) { 
            counter += 1
         }
         //println(modA)
         //println(modB)
         
         mutableA /= 2
         mutableB /= 2
         mutableC /= 2
     } while (mutableC / 2 > 0 || mutableC == 1)
     
     do  {
         counter += mutableA % 2
         mutableA /= 2
     } while (mutableA / 2 > 0 || mutableA == 1)
     
     do {
         counter += mutableB % 2
         mutableB /= 2
     } while (mutableB / 2 > 0 || mutableB == 1)
     
     return counter
 }

class Solution {

    fun twoSum(nums: IntArray, target: Int): IntArray {
        val arrSize = nums.size
        for ((index, value) in nums.withIndex()) {
    		val subarrLeft = nums.slice(0..index - 1)

        	if (subarrLeft.contains(value)) {
                continue
            }

            val subarrRight = nums.slice(index + 1 .. arrSize - 1)

            val indexOfFirstMatched = subarrRight.indexOfFirst({it + value == target})

            if (!(indexOfFirstMatched == -1)) {
                return intArrayOf(index, indexOfFirstMatched + index + 1)
            }
		}
        return intArrayOf()
    }
}