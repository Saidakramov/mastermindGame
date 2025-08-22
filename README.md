# mastermindGame
### During this project, my objective was to create a game where the user needs to guess the secret 4-digit code within 10 attempts. The code consists of numbers between 0 and 7, and duplicate digits are allowed.

## Table of Contents
- [Requirements](#requirements)
- [Usage](#usage)
- [How it Works](#how-it_works)
- [Features](#features)
- [Screenshots](#screenshots)

## Requirements
- [Git](https://git-scm.com/downloads)
- [Java 17](https://www.oracle.com/th/java/technologies/downloads/) or Higher
- [IntelliJ IDEA](https://www.jetbrains.com/idea/download/)
- [Random API](https://api.random.org/api-keys)

## Usage
1. Clone the repo:
``` 
    git clone https://github.com/Saidakramov/mastermindGame.git
```
2. Launch Project in IntelliJ IDEA:
    - Click Open and select project directory.
3. Generating the secret code:
    - Option 1: Random.org API
        - Go to [Random.org API Keys page](https://api.random.org/api-keys).
        - Sign up for a free account and generate your API key.
        - Copy the key.
        - In your project, create a file named `config.properties` and add:
          - `api-key=your-key`
          - `api.url=https://api.random.org`
        - Run the program. The Game will fetch truly random numbers from Random.org.
    - Option 2: Local Random Generator(Offline mode)
      - If you don't want to set up an API key, you can add this helper method to your project(inside `RandomOrgSecretNumbers.java`)
      ```
      private static int[] generateLocalRandomNumbers(int num, int min, int max) {
      Random random = new Random();
      int[] numbers = new int[num];
      for (int i = 0; i < num; i++) {
      numbers[i] = random.nextInt(max - min + 1) + min;
      }
      System.out.println("Using local random numbers.");
      return numbers;
      }
      ```
      - Then update your constructor to use the offline version:
      ```
      this.secret = RandomOrgSecretNumbers.generateSecretNumbers(numDigits, minDigits, maxDigits);

      if (this.secret == null) {
      // Local random numbers
      this.secret = generateLocalRandomNumbers(numDigits, minDigits, maxDigits);
      }
      ```


4. Run the Application
    - Open `Main.java` file inside `src/main/java/com/linkedin` directory.
    - Right-click on the `Main` class and select `Run Main.main()`
5. Interact with the CLI:
    - Follow the instructions displayed in the terminal.
   
## How it Works
- The game will ask the user to choose a game level on the first screen from the following options
   - Easy level where users need to guess four digits from 0-7.
   - Medium level where users need to guess four digits from 0-9.
   - Hard level where users need to guess five digits from 0-9.

- Once the user picks a level, the instructions message will display and will let the user know about the number of attempts and prompts them to enter their guess based on the rules.