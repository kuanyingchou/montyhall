//2011.11.3
//a small test for verifying the monty hall problem
//
class MontyHall {
   public static int REPEAT=1000;

   private static int pick(int range) {
      return (int)(Math.random()*range);
   } private static boolean isInArray(int target, int[] arr) {
      for(int i=0; i<arr.length; i++) {
         if(target==arr[i]) return true;
      }
      return false;
   }
   private static int pickExcept(int range, int ... exceptions) {
      int pseudoIndex=pick(range-exceptions.length);
      int index=0;
      while(isInArray(index, exceptions)) {
         index++;
      }
      for(int i=0; i<pseudoIndex; i++) {
         //[ if index is an exception, increase index
         while(isInArray(index, exceptions)) {
            index++;
            //System.err.println("index++"+index);
         }
         index++;
      }
      return index;
   }
   private static void testStubborn(int numOfDoors) {
      int winCount=0;
      int rep=0;
      for(; rep<REPEAT; rep++) {
         int prize=pick(numOfDoors);
         int firstGuess=pick(numOfDoors);
         if(prize==firstGuess) winCount++;
      }
      System.out.println((double)winCount/rep);
   }
   private static void testSmart(int numOfDoors) {
      int winCount=0;
      int rep=0;
      for(; rep<REPEAT; rep++) {
         int prize=pick(numOfDoors);
         int firstGuess=pick(numOfDoors);
         int revelation=pickExcept(numOfDoors, prize, firstGuess);
         int secondGuess=pickExcept(numOfDoors, firstGuess, revelation);
         /*
         System.out.printf(
               "prize: %d, firstGuess: %d, revelation: %d, secondGuess: %d%n", 
               prize, firstGuess, revelation, secondGuess);
         */

         if(prize==secondGuess) winCount++;
      }
      System.out.println((double)winCount/rep);
   }
   public static void main(String[] args) {
      //testStubborn(3);
      testSmart(3);
      //pickExcept(3, 2);

   }
}
