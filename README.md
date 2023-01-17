## Amazing Numbers
This is an interactive training project from JetBrains Academy (Hyperskill),
which can display different properties of numbers.
___
### Objectives
The program works with command line and process the user requests, like:
```
1. Welcome users;
2. Display the instructions;
3. Ask for a request;
4. If a user enters an empty request, print the instructions;
5. If the user enters zero, terminate the program;
6. If numbers are not natural, print the error message;
7. If an incorrect property is specified, print the error message and the list of available properties;
8. For one number print the properties of the number;
9. For two numbers print the properties of all numbers in the list;
10. For two numbers and two properties, print the list of numbers that contain the specified properties;
11. If a property is preceded by a minus, this property should not be present in a number;
12. If the user specifies mutually exclusive properties, abort the request and warn the user.
13. Once the request is processed, continue execution from step 3.
```
___
### Instructions
```
Supported requests:
- enter a natural number to know its properties;
- enter two natural numbers to obtain the properties of the list:
  * the first parameter represents a starting number;
  * the second parameter shows how many consecutive numbers are to be printed;
- two natural numbers and properties to search for;
- a property preceded by minus must not be present in numbers;
- separate the parameters with one space;
- enter 0 to exit.
```
___
### Usage examples
#### *Example 1*
```
Welcome to Amazing Numbers!
Supported requests:
- enter a natural number to know its properties;
- enter two natural numbers to obtain the properties of the list:
  * the first parameter represents a starting number;
  * the second parameter shows how many consecutive numbers are to be processed;
- two natural numbers and properties to search for;
- a property preceded by minus must not be present in numbers;
- separate the parameters with one space;
- enter 0 to exit.
Enter a request: 1 10
               1 is odd, palindromic, spy, square, jumping, happy
               2 is even, palindromic, spy, jumping, sad
               3 is odd, palindromic, spy, sunny, jumping, sad
               4 is even, palindromic, spy, square, jumping, sad
               5 is odd, palindromic, spy, jumping, sad
               6 is even, palindromic, spy, jumping, sad
               7 is odd, buzz, palindromic, spy, jumping, happy
               8 is even, palindromic, spy, sunny, jumping, sad
               9 is odd, palindromic, spy, square, jumping, sad
              10 is even, duck, jumping, happy
Enter a request: 1 5 -odd
               2 is even, palindromic, spy, jumping, sad
               4 is even, palindromic, spy, square, jumping, sad
               6 is even, palindromic, spy, jumping, sad
               8 is even, palindromic, spy, sunny, jumping, sad
              10 is even, duck, jumping, happy
Enter a request: 1 5 -even
               1 is odd, palindromic, spy, square, jumping, happy
               3 is odd, palindromic, spy, sunny, jumping, sad
               5 is odd, palindromic, spy, jumping, sad
               7 is odd, buzz, palindromic, spy, jumping, happy
               9 is odd, palindromic, spy, square, jumping, sad
Enter a request: 1 5 -odd -even gapful
The request contains mutually exclusive properties: [-ODD, -EVEN]
There are no numbers with these properties.
Enter a request: 1 5 odd square -odd
The request contains mutually exclusive properties: [-ODD, ODD]
There are no numbers with these properties.
Enter a request: 1 5 sunny square
The request contains mutually exclusive properties: [SQUARE, SUNNY]
There are no numbers with these properties.
Enter a request: 1 5 -sunny -square
               2 is even, palindromic, spy, jumping, sad
               5 is odd, palindromic, spy, jumping, sad
               6 is even, palindromic, spy, jumping, sad
               7 is odd, buzz, palindromic, spy, jumping, happy
              10 is even, duck, jumping, happy
```         
#### *Example 2: Numbers that have one specified property*
```
Enter a request: > 2000 5 happy
           2,003 is odd, duck, happy
           2,008 is even, duck, happy
           2,019 is odd, duck, happy
           2,026 is even, duck, happy
           2,030 is even, buzz, duck, happy
```          
#### *Example 3: Numbers with all specified properties*
```
Enter a request: 1 5 even sunny happy -duck -gapful
           3,968 is even, sunny, happy
          34,224 is even, sunny, happy
          75,624 is even, sunny, happy
         134,688 is even, sunny, happy
         178,928 is even, sunny, happy
```
___
### Number's properties
Available properties: `even`, `odd`, `buzz`, `duck`, `palindromic`, `gapful`, `spy`, 
`square`, `sunny`, `jumping`, `happy`, `sad`.

+ **Buzz** number either divisible by 7 or end with 7. For example, the number 14 is a buzz
number, since it is divisible by 7 without a remainder; the number 17 ends with 7, so
it is also a buzz number. However, the number 75 is not a Buzz number, since it is
neither divisible by 7 nor does it end with 7. The number 7 is a Buzz number too.


+ **Duck** number is a positive number that contains zeroes. For example, 3210, 8050896,
70709 are Duck numbers. Note that a number with a leading 0 is not a Duck number.
So, numbers like 035 or 0212 are not Duck numbers. Although, 01203 is a Duck, since it
has a trailing 0.


+ **Palindromic** number is symmetrical, in other words, it stays the same regardless
of whether we read it from left or right. For example, 17371 is a palindromic number.
5 is also a palindromic number. 1234 is not. If read it from right, it becomes 4321.
Add this new property to our program.


+ **Gapful** numbers contains at least 3 digits and is divisible by the concatenation of
its first and last digit without a remainder. 12 is not a Gapful number, as it has only
two digits. 132 is a Gapful number, as 132 % 12 == 0. 7881 is another example of
a Gapful number, as 7881 % 71 == 0.


+ **Spy** number. Sum of all digits is equal to the product of all digits.


+ **Square** number or a perfect square is an integer that is the square of an integer;
in other words, it is the product of an integer with itself. For example, 9 is a square
number, since it can be written as 3×3.


+ N is **Sunny** number if N+1 is a perfect square number.


+ **Jumping** number has adjacent digits that differ by 1. The difference between 9
and 0 is not considered as 1. Single-digit numbers are considered Jumping numbers.
For example, 78987, and 4343456 are Jumping numbers, but 796 and 89098 are not.


+ **Happy** number is a number that reaches 1 after a sequence during which the number
is replaced by the sum of each digit squares. For example, 13 is a happy number,
as 1\*1 + 3\*3 = 10 which leads to 1\*1 + 0\*0 = 1. On the other hand, 4 is not a happy number
because the sequence starts with 4\*4 = 16, 1\*1 + 6\*6 = 37, and finally reaches 2\*2 + 0\*0 = 4.
This is the number that started the sequence, so the process goes on in an infinite
cycle. A number  is called  (or Unhappy).


+ **Sad** number - number that is not happy.