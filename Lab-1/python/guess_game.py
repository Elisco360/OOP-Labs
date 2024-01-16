# function to simulate a guessing game by generating a random number
import random


def guessing_game():
    random.seed(4)
    target = random.randint(1, 100)
    number_of_tries = 0
    print(target)
    print("Welcome to the Guessing Game!")
    print("I'm thinking of a number between 1 and 100. Can you guess it?")
    user_entry = int(input("Guess: "))

    while user_entry != target:
        number_of_tries += 1
        if user_entry > target:
            print("Good try, but that's too high. Try again")
            user_entry = int(input("Guess: "))
        else:
            print("Good try, but that's too low. Try again")
            user_entry = int(input("Guess: "))
    else:
        number_of_tries += 1
        if number_of_tries == 1:
            print(f"Yes! You guessed correctly after {number_of_tries} try! Congratulations.")
        else:
            print(f"Yes! You guessed correctly after {number_of_tries} tries! Congratulations.")


guessing_game()
